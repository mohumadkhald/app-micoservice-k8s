package org.springframework.samples.companyservice.company;

import lombok.RequiredArgsConstructor;
import org.springframework.samples.companyservice.company.client.Review;
import org.springframework.samples.companyservice.company.client.ReviewClient;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

  private final CompanyRepository companyRepository;
  private final ReviewClient reviewClient;


  @Override
  public List<CompanyDto> findCompanies() {
    return companyRepository.findAll().stream()
      .map(comp -> {
        // 2. Fetch the company for each job
        List<Review> reviews = reviewClient.getReviewCompanies(comp.getId());

        // 3. Map the Job and Company to JobDto
        return CompanyMapper.toCompanyDto(comp, reviews);
      })
      .toList();
  }

  @Override
  public Company addCompany(Company company) {
    return companyRepository.save(company);
  }

  @Override
  public CompanyDto findCompany(long id) {
    Optional<Company> companyOptional = companyRepository.findById(id);
    if (companyOptional.isPresent()) {
      Company company = companyOptional.get();
      List<Review> reviews = reviewClient.getReviewCompanies(company.getId());
      return CompanyMapper.toCompanyDto(company, reviews);
    }
    return null;
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
