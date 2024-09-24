package org.springframework.samples.companyservice.company;

import org.springframework.samples.companyservice.company.client.Review;

import java.util.List;

public interface CompanyMapper {
  public static CompanyDto toCompanyDto (final Company company, final List<Review> reviews) {
    return CompanyDto.builder()
      .id(company.getId())
      .name(company.getName())
      .description(company.getDescription())
      .rating(company.getRating())
      .reviews(reviews)
      .build();
  }
}
