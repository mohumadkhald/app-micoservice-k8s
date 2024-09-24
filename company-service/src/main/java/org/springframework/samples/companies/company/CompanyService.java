package org.springframework.samples.companies.company;


import org.springframework.samples.companies.messaging.ReviewMessage;

import java.util.List;

public interface CompanyService {
  List<CompanyDto> findCompanies();

  Company addCompany(Company company);

  CompanyDto findCompany(long id);

  boolean deleteCompany(long id);

  boolean updateCompany(long id, Company updatedCompany);

    void updateCompanyRating(ReviewMessage reviewMessage);
}
