package me.parkprin.assignment.reviews;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

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
}
