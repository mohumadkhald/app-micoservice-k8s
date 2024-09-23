package org.springframework.samples.reviewservice.review.client;

import lombok.*;
import org.springframework.samples.reviewservice.review.Review;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {
  private Long id;
  private String name;
  private String description;
}
