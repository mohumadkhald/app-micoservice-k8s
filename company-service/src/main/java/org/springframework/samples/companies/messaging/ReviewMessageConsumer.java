package org.springframework.samples.companies.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.samples.companies.company.CompanyService;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageConsumer {
  private final CompanyService companyService;

  public ReviewMessageConsumer(CompanyService companyService) {
    this.companyService = companyService;
  }

  @RabbitListener(queues = "companyRatingQueue")
  public void consumeMessage(ReviewMessage reviewMessage){
    companyService.updateCompanyRating(reviewMessage);
  }
}
