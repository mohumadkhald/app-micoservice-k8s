package org.springframework.samples.companyservice.company;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.samples.companyservice.company.client.Review;

import java.util.List;

@Setter
@Getter
@Builder
public class CompanyDto {
  private Long id;
  private String name;
  private String description;
  private double rating;
  List<Review> reviews;
}
