package me.parkprin.assignment.security;

import me.parkprin.assignment.errors.NotFoundException;
import me.parkprin.assignment.errors.UnauthorizedException;
import me.parkprin.assignment.role.RoleEntity;
import me.parkprin.assignment.role.RoleStatus;
import me.parkprin.assignment.userandrole.UserAndRoleEntity;
import me.parkprin.assignment.userandrole.UserAndRoleServiceImpl;
import me.parkprin.assignment.users.UserEntity;
import me.parkprin.assignment.users.UserServiceImpl;
import me.parkprin.assignment.utils.ValidationUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.apache.commons.lang3.ClassUtils.isAssignable;
import static org.springframework.security.core.authority.AuthorityUtils.createAuthorityList;

public class JwtAuthenticationProvider implements AuthenticationProvider {

    private final UserServiceImpl userService;
    private final UserAndRoleServiceImpl userAndRoleService;

    public JwtAuthenticationProvider(UserServiceImpl userService, UserAndRoleServiceImpl userAndRoleService) {
        this.userService = userService;
        this.userAndRoleService = userAndRoleService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken authenticationToken = (JwtAuthenticationToken) authentication;
        return processUserAuthentication(
                ValidationUtils.emailValidation(String.valueOf(authenticationToken.getPrincipal())),
                authenticationToken.getCredentials()
        );
    }

    private Authentication processUserAuthentication(String email, String password) {
        try {
            UserEntity user = userService.login(email, password);
            String[] roleStatusArray = userAndRoleService.findByUserSeq(user.getUserSeq()).
                    stream()
                    .map(UserAndRoleEntity::getRole)
                    .map(RoleEntity::getStatus)
                    .map(RoleStatus::value)
                    .toArray(String[]::new);

            JwtAuthenticationToken authenticated =
                    new JwtAuthenticationToken(
                            new JwtAuthentication(user.getUserSeq(), user.getName()),
                            null,
                            createAuthorityList(roleStatusArray)
                    );
            authenticated.setDetails(user);
            return authenticated;
        } catch (NotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException(e.getMessage());
        } catch (DataAccessException e) {
            throw new AuthenticationServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return isAssignable(JwtAuthenticationToken.class, authentication);
    }

}
