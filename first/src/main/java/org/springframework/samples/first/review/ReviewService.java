package org.springframework.samples.first.review;

import java.util.List;

public interface ReviewService {

  List<Review> getAllReviews(Long companyId);

  Review createReview(Review review, Long companyId);

  Review getReviewById(Long id, Long companyId);

  Review updateReviewById(Long id, Long companyId, Review newReview);

  boolean deleteReviewById(Long id, Long companyId);
}
