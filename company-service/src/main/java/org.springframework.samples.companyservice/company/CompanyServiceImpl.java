package org.springframework.samples.companyservice.company;

import lombok.RequiredArgsConstructor;
import org.springframework.samples.companyservice.company.Company;
import org.springframework.samples.companyservice.company.CompanyRepository;
import org.springframework.samples.companyservice.company.CompanyService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

  private final CompanyRepository companyRepository;


  @Override
  public List<Company> findCompanies() {
    return companyRepository.findAll();
  }

  @Override
  public Company addCompany(Company company) {
    return companyRepository.save(company);
  }

  @Override
  public Company findCompany(long id) {
    return companyRepository.findById(id).orElse(null);
  }

  @Override
  public boolean deleteCompany(long id) {
    Optional<Company> company = companyRepository.findById(id);
    if (company.isPresent()) {
      companyRepository.delete(company.get());
      return true;
    }
    return false;
  }

  @Override
  public boolean updateCompany(long id, Company updatedCompany) {
    Optional<Company> company = companyRepository.findById(id);
    if (company.isPresent()) {
      Company oldCompany = company.get();
      oldCompany.setName(updatedCompany.getName());
      oldCompany.setDescription(updatedCompany.getDescription());
      companyRepository.save(oldCompany);
      return true;
    }
    return false;
  }
}
