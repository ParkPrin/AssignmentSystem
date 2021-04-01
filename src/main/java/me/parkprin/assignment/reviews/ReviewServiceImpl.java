package me.parkprin.assignment.reviews;

import me.parkprin.assignment.products.ProductEntity;
import me.parkprin.assignment.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewServiceImpl implements BasicService<ReviewEntity> {

    @Autowired
    private ReviewJpaRepository reviewJpaRepository;

    @Transactional
    public ReviewEntity save(ReviewEntity review){
        return reviewJpaRepository.save(review);
    }

    @Transactional(readOnly = true)
    public Long tableLength(){
        return reviewJpaRepository.count();
    }

    @Transactional
    public void deleteAll(){
        reviewJpaRepository.deleteAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ReviewEntity> findAll(Pageable pageable) {
        return reviewJpaRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public List<ReviewEntity> findAll(){
        return reviewJpaRepository.findAll();
    }
}
