package me.parkprin.assignment.reviews;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static me.parkprin.assignment.utils.ApiUtils.success;
import static me.parkprin.assignment.utils.ApiUtils.ApiResult;

@RestController
@RequestMapping("api/reviews")
public class ReviewController {

    private ReviewServiceImpl reviewService;

    public ReviewController(ReviewServiceImpl reviewService){
        this.reviewService = reviewService;
    }

    @GetMapping
    public ApiResult findAll(){
        return success(reviewService.findAll());
    }

}
