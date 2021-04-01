package me.parkprin.assignment.orders;

import me.parkprin.assignment.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements BasicService<OrderEntity> {

    @Autowired
    private OrderJpaRepository orderJpaRepository;

    @Transactional
    @Override
    public OrderEntity save(OrderEntity order){
        return orderJpaRepository.save(order);
    }

    @Transactional(readOnly = true)
    @Override
    public Long tableLength(){
        return orderJpaRepository.count();
    }

    @Transactional
    @Override
    public void deleteAll(){
        orderJpaRepository.deleteAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<OrderEntity> findAll(Pageable pageable) {
        return orderJpaRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrderEntity> findAll(){
        return orderJpaRepository.findAll();
    }
}
