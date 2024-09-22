package org.springframework.samples.jobservice.job;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JobDto {
  private Job job;
  private  Company company;
}
