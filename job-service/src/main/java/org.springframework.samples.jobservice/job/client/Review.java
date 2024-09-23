package org.springframework.samples.jobservice.job.client;

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
