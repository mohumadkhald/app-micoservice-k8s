package org.springframework.samples.jobservice.job;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class JobServiceImpl implements JobService {

  private static final Logger log = LoggerFactory.getLogger(JobServiceImpl.class);
  private final JobRepository jobRepository;

  public JobServiceImpl(JobRepository jobRepository) {
    this.jobRepository = jobRepository;
  }

  @Override
  public List<JobDto> findJobs() {
    RestTemplate restTemplate = new RestTemplate();

    // 1. Fetch all jobs
    return jobRepository.findAll().stream()
      .map(job -> {
        // 2. Fetch the company for each job
        String companyUrl = "http://localhost:8082/companies/" + job.getCompanyId();
        Company company = restTemplate.getForObject(companyUrl, Company.class);

        // 3. Map the Job and Company to JobDto
        return JobMapper.toJobDto(job, company);
      })
      .toList();
  }

  @Override
  public boolean addJob(Job job) {
    RestTemplate restTemplate = new RestTemplate();
    Company[] companiesArray = restTemplate.getForObject("http://localhost:8082/companies", Company[].class);
    Long companyId = job.getCompanyId();
    assert companiesArray != null;
    for (Company company : companiesArray) {
      if (company.getId().equals(companyId)) {
        jobRepository.save(job);
        return true;
      }
    }
    return false;
  }

  @Override
  public JobDto findJob(long id) {
   Job job = jobRepository.findById(id).orElse(null);
    RestTemplate restTemplate = new RestTemplate();
    assert job != null;
    String companyUrl = "http://localhost:8082/companies/" + job.getCompanyId();
    Company company = restTemplate.getForObject(companyUrl, Company.class);
   return JobMapper.toJobDto(job, company);
  }

  @Override
  public boolean deleteJob(long id) {
    Optional<Job> job = jobRepository.findById(id);
    if (job.isPresent()) {
      jobRepository.delete(job.get());
      return true;
    }
    return false;
  }

  @Override
  public boolean updateJob(long id, Job updatedJob) {

    Optional<Job> jobOptional = jobRepository.findById(id);
    if (jobOptional.isPresent()) {
      Job job = jobOptional.get();
      job.setMinSalary(updatedJob.getMinSalary());
      job.setMaxSalary(updatedJob.getMaxSalary());
      job.setStatus(updatedJob.getStatus());
      job.setTitle(updatedJob.getTitle());
      job.setDescription(updatedJob.getDescription());
      job.setLocation(updatedJob.getLocation());
      job.setCompanyId(updatedJob.getCompanyId());
      jobRepository.save(job);
      return true;
    }
    return false;
  }
}
