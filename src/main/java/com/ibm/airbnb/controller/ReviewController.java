package com.ibm.airbnb.controller;

import com.ibm.airbnb.entity.Property;
import com.ibm.airbnb.entity.PropertyUser;
import com.ibm.airbnb.entity.Review;
import com.ibm.airbnb.repository.PropertyRepository;
import com.ibm.airbnb.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    
    private ReviewRepository reviewRepository;
    private PropertyRepository propertyRepository;;

    public ReviewController(ReviewRepository reviewRepository, PropertyRepository propertyRepository) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
    }

    @RequestMapping("/addReview")
    public ResponseEntity<String> addReview(@RequestParam long propertyId, @RequestBody Review review, @AuthenticationPrincipal PropertyUser propertyUser){
        Optional<Property> opProperty = propertyRepository.findById(propertyId);
        Property property = opProperty.get();
        review.setProperty(property);
        review.setPropertyUser(propertyUser);

        Review savedReview = reviewRepository.save(review);
        if(savedReview != null){
            return new ResponseEntity<>("Review added successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Some thing went wrong. try again", HttpStatus.CREATED);
    }
}
