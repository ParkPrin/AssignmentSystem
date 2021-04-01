package me.parkprin.assignment.service;

import me.parkprin.assignment.orders.OrderEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BasicService<T> {

    public T save(T entity);
    public Long tableLength();
    public void deleteAll();
    public Page<T> findAll(Pageable pageable);
    public List<T> findAll();

}
