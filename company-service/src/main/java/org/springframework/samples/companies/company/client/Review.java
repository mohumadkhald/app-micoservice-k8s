package org.springframework.samples.companies.company.client;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {

  private Long id;
  private String title;
  private String description;
  private Double rating;
}
