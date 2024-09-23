package org.springframework.samples.companyservice.company.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "REVIEW-SERVICE")
public interface ReviewClient {
  @GetMapping("/reviews/{id}")
  Review geReviewById(@PathVariable("id") Long id);

  @GetMapping("reviews")
  List<Review> getReviewCompanies(@RequestParam Long compId);
}
