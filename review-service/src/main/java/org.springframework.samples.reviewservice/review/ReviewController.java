package org.springframework.samples.reviewservice.review;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{comId}")
@RequiredArgsConstructor
public class ReviewController {
  private final ReviewService reviewService;

  @GetMapping("/reviews")
  public ResponseEntity<List<Review>> getReviews(@PathVariable("comId") Long companyId) {
    List<Review> reviews = reviewService.getAllReviews(companyId);
   if (reviews == null)
   {
     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   } else if (reviews.isEmpty()) {
     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }
    return new ResponseEntity<>(reviews, HttpStatus.OK);
  }

  @PostMapping("/reviews")
  public ResponseEntity<Review> createReview(@RequestBody Review newReview, @PathVariable("comId") Long companyId) {
    Review review = reviewService.createReview(newReview, companyId);
    if (review == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(review, HttpStatus.CREATED);
  }

  @GetMapping("/reviews/{id}")
  public ResponseEntity<Review> getReview(@PathVariable("comId") Long companyId, @PathVariable("id") Long id) {
    Review review = reviewService.getReviewById(id, companyId);
    if (review == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(review, HttpStatus.OK);
  }

  @PutMapping("/reviews/{id}")
  public ResponseEntity<Review> updateReview(@PathVariable("comId") Long companyId, @PathVariable("id") Long id, @RequestBody Review newReview) {
    Review review = reviewService.updateReviewById(id, companyId, newReview);
    if (review == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(review, HttpStatus.OK);
  }

  @DeleteMapping("/reviews/{id}")
  public ResponseEntity<String> deleteReview(@PathVariable("comId") Long companyId, @PathVariable("id") Long id) {
    boolean deleted = reviewService.deleteReviewById(id, companyId);
    if (deleted ) {
      return new ResponseEntity<>("the review deleted", HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

}
