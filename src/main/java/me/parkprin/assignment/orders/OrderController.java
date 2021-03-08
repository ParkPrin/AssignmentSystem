package me.parkprin.assignment.orders;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
