package org.springframework.samples.reviewservice.review;


import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ReviewService {

  List<Review> getAllReviews(Long companyId);

  Review createReview(Review review, Long companyId);

  Review getReviewById(Long id);

  Review updateReviewById(Long id, Review newReview);

  boolean deleteReviewById(Long id);
}
