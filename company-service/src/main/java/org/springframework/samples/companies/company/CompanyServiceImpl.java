package org.springframework.samples.companies.company;

import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.samples.companies.company.client.ReviewClient;
import org.springframework.samples.companies.messaging.ReviewMessage;
import org.springframework.samples.companies.company.client.Review;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

  private final CompanyRepository companyRepository;
  private final ReviewClient reviewClient;


  @Override
  public List<CompanyDto> findCompanies() {
    List<Company> companies =  companyRepository.findAll();
    List<CompanyDto> companiesDto = new ArrayList<>();
    return companies.stream().map(this::convertToDto).collect(Collectors.toList());
  }

  private CompanyDto convertToDto(Company company) {
    List<Review> reviews = reviewClient.getReviewCompanies(company.getId());

    return CompanyMapper.
      toCompanyDto(company,reviews);
  }

  @Override
  public Company addCompany(Company company) {
    company.setRating(0.0);
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

  @Override
  public void updateCompanyRating(ReviewMessage reviewMessage) {
    System.out.println(reviewMessage.getDescription());
    Company company = companyRepository.findById(reviewMessage.getCompanyId())
      .orElseThrow(() -> new NotFoundException("Company not found" + reviewMessage.getCompanyId()));
    double avgRating = reviewClient.getAverageRating(reviewMessage.getCompanyId());
    company.setRating(avgRating);
    companyRepository.save(company);
  }
}
