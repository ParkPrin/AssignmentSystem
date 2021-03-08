package me.parkprin.assignment.users;

import me.parkprin.assignment.utils.ApiUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static me.parkprin.assignment.utils.ApiUtils.success;

@RestController
@RequestMapping("api/users")
public class UserController {

    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService){
        this.userService = userService;
    }

    @GetMapping
    public ApiUtils.ApiResult findAll(){
        return success(userService.findAll());
    }
}
