package me.parkprin.assignment.reviews;

import me.parkprin.assignment.products.ProductEntity;
import me.parkprin.assignment.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements BasicService<ReviewEntity> {

    @Autowired
    private ReviewJpaRepository reviewJpaRepository;

    public ReviewEntity save(ReviewEntity review){
        return reviewJpaRepository.save(review);
    }
    public Long tableLength(){
        return reviewJpaRepository.count();
    }
    public void deleteAll(){
        reviewJpaRepository.deleteAll();
    }
    public List<ReviewEntity> findAll(){
        return reviewJpaRepository.findAll();
    }
}
