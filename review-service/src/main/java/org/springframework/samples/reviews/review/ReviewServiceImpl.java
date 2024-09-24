package org.springframework.samples.reviews.review;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

  private final ReviewRepository reviewRepository;

  @Override
  public List<Review> getAllReviews(Long companyId) {

      return reviewRepository.findByCompanyId(companyId);
  }

  @Override
  public Review createReview(Review review, Long companyId) {
      review.setCompanyId(companyId);
      return reviewRepository.save(review);
  }

  @Override
  public Review getReviewById(Long id) {

    return reviewRepository.findById(id).orElse(null);
  }

  @Override
  public Review updateReviewById(Long id, Review newReview) {
    Review review = getReviewById(id);
      if (review != null) {
        newReview.setId(review.getId());
        newReview.setCompanyId(review.getCompanyId());
        return reviewRepository.save(newReview);
      }
    return null;
  }

  @Override
  public boolean deleteReviewById(Long id) {
    Review review = getReviewById(id);
      if (review != null) {
        reviewRepository.delete(review);
        return true;
      }
    return false;
  }
}
