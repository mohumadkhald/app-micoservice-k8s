package org.springframework.samples.jobservice.job.client;

import lombok.*;

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
  private double rating;
  List<Review> reviews;
}
