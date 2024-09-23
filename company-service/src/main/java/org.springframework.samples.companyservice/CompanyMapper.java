package org.springframework.samples.companyservice;

import org.springframework.samples.companyservice.company.Company;
import org.springframework.samples.companyservice.company.CompanyDto;
import org.springframework.samples.companyservice.company.client.Review;

import java.util.List;

public interface CompanyMapper {
  public static CompanyDto toCompanyDto (final Company company, final List<Review> reviews) {
    return CompanyDto.builder()
      .id(company.getId())
      .name(company.getName())
      .description(company.getDescription())
      .reviews(reviews)
      .build();
  }
}
