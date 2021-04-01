package me.parkprin.assignment.orders;

import me.parkprin.assignment.security.JwtAuthentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;

import static me.parkprin.assignment.utils.ApiUtils.success;
import static me.parkprin.assignment.utils.ApiUtils.ApiResult;

@RestController
@RequestMapping("api/orders")
public class OrderController {

    private final OrderServiceImpl orderService;

    public OrderController (OrderServiceImpl orderService){
        this.orderService = orderService;
    }

    @GetMapping
    public ApiResult findAll(){
        return success(orderService.findAll());
    }

    @PatchMapping(path = "{id}/accept")
    public ApiResult accept(@PathVariable Long id, @AuthenticationPrincipal JwtAuthentication authentication){
        return null;
    }

    @PatchMapping(path = "{id}/reject")
    public ApiResult reject(@PathVariable Long id, @Nullable @RequestBody OrderRequest request,
                            @AuthenticationPrincipal JwtAuthentication authentication) {
        return null;

    }

    @PatchMapping(path = "{id}/shipping")
    public ApiResult shipping(@PathVariable Long id,
                              @AuthenticationPrincipal JwtAuthentication authentication){
        return null;
    }

    @PatchMapping(path = "{id}/complete")
    public ApiResult complete(@PathVariable Long id,
                              @AuthenticationPrincipal JwtAuthentication authentication){
        return null;
    }

}
