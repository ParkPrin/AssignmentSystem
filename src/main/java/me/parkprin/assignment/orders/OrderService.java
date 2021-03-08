package me.parkprin.assignment.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderJpaRepository orderJpaRepository;

    public OrderEntity save(OrderEntity order){
        return orderJpaRepository.save(order);
    }
}
