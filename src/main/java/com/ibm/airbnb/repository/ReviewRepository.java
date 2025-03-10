package com.ibm.airbnb.repository;

import com.ibm.airbnb.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}