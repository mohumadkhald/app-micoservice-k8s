package org.springframework.samples.jobservice.job;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {
  private Long id;
  private String name;
  private String description;

}
