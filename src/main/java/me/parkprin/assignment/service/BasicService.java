package me.parkprin.assignment.service;

import me.parkprin.assignment.orders.OrderEntity;

import java.util.List;

public interface BasicService<T> {

    public T save(T order);
    public Long tableLength();
    public void deleteAll();
    public List<T> findAll();

}
