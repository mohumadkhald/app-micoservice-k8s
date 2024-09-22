package org.springframework.samples.first.review;

import lombok.RequiredArgsConstructor;
import org.springframework.samples.first.company.Company;
import org.springframework.samples.first.company.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

  private final ReviewRepository reviewRepository;
  private final CompanyRepository companyRepository;

  @Override
  public List<Review> getAllReviews(Long companyId) {
    Optional<Company> optionalCompany = companyRepository.findById(companyId);
    if (optionalCompany.isPresent()) {
      return reviewRepository.findByCompanyId(companyId);
    }
    return null;
  }

  @Override
  public Review createReview(Review review, Long companyId) {
    Optional<Company> optionalCompany = companyRepository.findById(companyId);
    if (optionalCompany.isPresent()) {
      Company company = optionalCompany.get();
      review.setCompany(company);
      return reviewRepository.save(review);
    }
    return null;
  }

  @Override
  public Review getReviewById(Long id, Long companyId) {
    List<Review> reviews = getAllReviews(companyId);
//    if (reviews != null) {
//      for (Review review : reviews) {
//        if (review.getId().equals(id)) {
//          return review;
//        }
//      }
//    }

    if (reviews != null) {
      return reviews.stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
    }
    return null;
  }

  @Override
  public Review updateReviewById(Long id, Long companyId, Review newReview) {
    List<Review> reviews = getAllReviews(companyId);
    if (reviews != null) {
      Review oldReview = reviews.stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
      if (oldReview != null) {
        newReview.setId(oldReview.getId());
        newReview.setCompany(oldReview.getCompany());
        return reviewRepository.save(newReview);
      }
    }
    return null;
  }

  @Override
  public boolean deleteReviewById(Long id, Long companyId) {
    List<Review> reviews = getAllReviews(companyId);
    Review review = reviews.stream().filter(r -> r.getId().equals(id)).findFirst().orElse(null);
      if (review != null) {
        reviewRepository.delete(review);
        return true;
      }
    return false;
  }
}
