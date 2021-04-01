package me.parkprin.assignment.products;

import me.parkprin.assignment.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Service
public class ProductServiceImpl implements BasicService<ProductEntity> {

    @Autowired
    private ProductJpaRepository productJpaRepository;

    @Transactional
    public ProductEntity save(ProductEntity product){
        return productJpaRepository.save(product);
    }

    @Transactional(readOnly = true)
    public Long tableLength(){
        return productJpaRepository.count();
    }

    @Transactional
    public void deleteAll(){
        productJpaRepository.deleteAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ProductEntity> findAll(Pageable pageable) {
        return productJpaRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<ProductEntity> findAll(){
        return productJpaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<ProductEntity> findAllDesc(){
        return productJpaRepository.findAllDesc();
    }

    @Transactional(readOnly = true)
    public Optional<ProductEntity> findById(Long productId){
        checkNotNull(productId, "productId must be provided");
        return productJpaRepository.findById(productId);
    }


}
