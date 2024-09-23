package org.springframework.samples.reviewservice.review.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "COMPANY-SERVICE")
public interface CompanyClient {
  @GetMapping("/companies/{compId}")
  Company getCompanyById(@PathVariable("compId") Long companyId);
}
