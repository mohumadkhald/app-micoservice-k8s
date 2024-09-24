package org.springframework.samples.jobs.job;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.samples.jobs.job.client.Company;

@Setter
@Getter
@Builder
public class JobDto {
  private Long id;
  private String title;
  private String description;
  private Double minSalary;
  private Double maxSalary;
  private String location;
  private String status;
  private Company company;
}
