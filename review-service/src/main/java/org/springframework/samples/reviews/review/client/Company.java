package org.springframework.samples.reviews.review.client;

import lombok.*;

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
