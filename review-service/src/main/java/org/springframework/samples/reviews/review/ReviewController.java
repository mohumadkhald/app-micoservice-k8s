package org.springframework.samples.reviews.review;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.reviews.messaging.ReviewMessageProducer;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/reviews")
@RequiredArgsConstructor
public class ReviewController {
  private final ReviewService reviewService;
  private final ReviewMessageProducer reviewMessageProducer;


  @GetMapping
  public ResponseEntity<List<Review>> getReviews(@RequestParam("compId") Long companyId) {
    List<Review> reviews = reviewService.getAllReviews(companyId);
   if (reviews == null)
   {
     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   } else if (reviews.isEmpty()) {
     reviews = Collections.emptyList();
     return new ResponseEntity<>(reviews, HttpStatus.OK);
   }
    return new ResponseEntity<>(reviews, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Review> createReview(@RequestBody Review newReview, @RequestParam("compId") Long companyId) {
    Review review = reviewService.createReview(newReview, companyId);
    if (review == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    reviewMessageProducer.sendMessage(review);
    return new ResponseEntity<>(review, HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Review> getReview(@PathVariable("id") Long id) {
    Review review = reviewService.getReviewById(id);
    if (review == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(review, HttpStatus.OK);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Review> updateReview(@PathVariable("id") Long id, @RequestBody Review newReview) {
    Review review = reviewService.updateReviewById(id, newReview);
    if (review == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(review, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteReview(@PathVariable("id") Long id) {
    boolean deleted = reviewService.deleteReviewById(id);
    if (deleted ) {
      return new ResponseEntity<>("the review deleted", HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @GetMapping("/avgRating")
  public Double getAverageRating(@RequestParam("compId") Long companyId) {
    List<Review> reviews = reviewService.getAllReviews(companyId);
    return reviews.stream().mapToDouble(Review::getRating).average().orElse(0.0);
  }

}
