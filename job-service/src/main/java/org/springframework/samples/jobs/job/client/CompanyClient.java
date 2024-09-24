package org.springframework.samples.jobs.job.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "COMPANY-SERVICE")
public interface CompanyClient {
  @GetMapping("/companies/{compId}")
  Company getCompanyById(@PathVariable("compId") Long companyId);

//  @GetMapping("companies")
//  Company[] getCompanies();

  @GetMapping("companies")
  List<Company> getCompanies();

}
