package org.springframework.samples.first.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.samples.first.job.Job;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class Company {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private String description;

  @JsonIgnore
  @OneToMany(mappedBy = "company")
  private List<Job> jobs;

//  private List<Review> reviews;

}
