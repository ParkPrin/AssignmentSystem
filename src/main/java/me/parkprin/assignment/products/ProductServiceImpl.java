package me.parkprin.assignment.products;

import me.parkprin.assignment.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements BasicService<ProductEntity> {

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

    public List<ProductEntity> findAll(){
        return productJpaRepository.findAll();
    }
}
