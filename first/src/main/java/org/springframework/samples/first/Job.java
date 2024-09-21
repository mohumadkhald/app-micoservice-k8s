package org.springframework.samples.first;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Job {

  private Long id;
  private String title;
  private String description;
  private Double minSalary;
  private Double maxSalary;
  private String location;
  private String status;

}
