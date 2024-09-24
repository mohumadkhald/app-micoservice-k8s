package org.springframework.samples.jobservice.job;

import org.springframework.samples.jobservice.job.client.Company;

public interface JobMapper {
  public static JobDto toJobDto (final Job job, final Company company) {
    return JobDto.builder()
      .company(company)
      .title(job.getTitle())
      .description(job.getDescription())
      .maxSalary(job.getMaxSalary())
      .minSalary(job.getMinSalary())
      .id(job.getId())
      .location(job.getLocation())
      .status(job.getStatus())

      .build();
  }
}
