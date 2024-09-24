package org.springframework.samples.companyservice.company;


import org.springframework.samples.companyservice.messaging.ReviewMessage;

import java.util.List;

public interface CompanyService {
  List<CompanyDto> findCompanies();

  Company addCompany(Company company);

  CompanyDto findCompany(long id);

  boolean deleteCompany(long id);

  boolean updateCompany(long id, Company updatedCompany);

    void updateCompanyRating(ReviewMessage reviewMessage);
}
