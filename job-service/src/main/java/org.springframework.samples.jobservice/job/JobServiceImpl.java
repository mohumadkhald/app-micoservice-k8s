package org.springframework.samples.jobservice.job;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.samples.jobservice.job.Job;
import org.springframework.samples.jobservice.job.JobRepository;
import org.springframework.samples.jobservice.job.JobService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

  private final JobRepository jobRepository;

  public JobServiceImpl(JobRepository jobRepository) {
    this.jobRepository = jobRepository;
  }

  @Override
  public List<Job> findJobs() {
    return jobRepository.findAll();
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
  public Job findJob(long id) {
   return jobRepository.findById(id).orElse(null);
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
