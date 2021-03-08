package me.parkprin.assignment.orders;

import me.parkprin.assignment.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements BasicService<OrderEntity> {

    @Autowired
    private OrderJpaRepository orderJpaRepository;

    @Override
    public OrderEntity save(OrderEntity order){
        return orderJpaRepository.save(order);
    }

    @Override
    public Long tableLength(){
        return orderJpaRepository.count();
    }

    @Override
    public void deleteAll(){
        orderJpaRepository.deleteAll();
    }

    @Override
    public List<OrderEntity> findAll(){
        return orderJpaRepository.findAll();
    }
}
