package me.parkprin.assignment.users;

import me.parkprin.assignment.errors.NotFoundException;
import me.parkprin.assignment.errors.UnauthorizedException;
import me.parkprin.assignment.security.Jwt;
import me.parkprin.assignment.security.JwtAuthentication;
import me.parkprin.assignment.security.JwtAuthenticationToken;
import me.parkprin.assignment.security.JwtManager;
import me.parkprin.assignment.utils.ApiUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static me.parkprin.assignment.utils.ApiUtils.success;
import static me.parkprin.assignment.utils.ApiUtils.ApiResult;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserServiceImpl userService;

    private final AuthenticationManager authenticationManager;

    private final JwtManager jwtManager;

    private final Jwt jwt;

    public UserController(UserServiceImpl userService, AuthenticationManager authenticationManager, JwtManager jwtManager, Jwt jwt){
        this.authenticationManager =authenticationManager;
        this.userService = userService;
        this.jwtManager = jwtManager;
        this.jwt = jwt;
    }

    @GetMapping
    public ApiUtils.ApiResult findAll(){
        return success(userService.findAll());
    }

    @PostMapping(path = "login")
    public ApiResult<LoginResult> login(
            @Valid @RequestBody LoginRequest request
    ) throws UnauthorizedException {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new JwtAuthenticationToken(request.getPrincipal(), request.getCredentials())
            );
            final UserEntity user = (UserEntity) authentication.getDetails();
            final String token = jwtManager.newJwt(
                    jwt,
                    authentication.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .toArray(String[]::new),
                    user

            );
            return success(new LoginResult(token, user));
        } catch (AuthenticationException e) {
            throw new UnauthorizedException(e.getMessage(), e);
        }
    }

    @GetMapping(path = "me")
    public ApiResult<UserDto> me(
            @AuthenticationPrincipal JwtAuthentication authentication
    ) {
        if (authentication == null) throw new UnauthorizedException("Unauthorized");
        return success(
                userService.findById(authentication)
                        .map(UserDto::new)
                        .orElseThrow(() -> new NotFoundException("Could not found user"))
        );


    }
}
