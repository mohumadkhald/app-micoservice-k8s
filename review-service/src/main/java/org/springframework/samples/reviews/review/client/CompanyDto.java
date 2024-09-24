package org.springframework.samples.reviews.review.client;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.samples.reviews.review.Review;

import java.util.List;

@Setter
@Getter
@Builder
public class CompanyDto {
  private Long id;
  private String name;
  private String description;
  List<Review> reviews;
}
