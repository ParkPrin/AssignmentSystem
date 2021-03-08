package me.parkprin.assignment.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductJpaRepository productJpaRepository;

    public ProductEntity save(ProductEntity product){
        return productJpaRepository.save(product);
    }

    public Long tableLength(){
        return productJpaRepository.count();
    }

    public void deleteAll(){
        productJpaRepository.deleteAll();
    }
}
