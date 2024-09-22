package org.springframework.samples.companyservice.company;


import java.util.List;

public interface CompanyService {
  List<Company> findCompanies();

  Company addCompany(Company company);

  Company findCompany(long id);

  boolean deleteCompany(long id);

  boolean updateCompany(long id, Company updatedCompany);
}
