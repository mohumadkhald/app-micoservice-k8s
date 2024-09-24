package org.springframework.samples.jobs.job;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.samples.jobs.job.client.Company;
import org.springframework.samples.jobs.job.client.CompanyClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class JobServiceImpl implements JobService {


  private final JobRepository jobRepository;
  private final RestTemplate restTemplate;
  private final CompanyClient companyClient;

  public JobServiceImpl(JobRepository jobRepository, RestTemplate restTemplate, CompanyClient companyClient) {
    this.jobRepository = jobRepository;
    this.restTemplate = restTemplate;
    this.companyClient = companyClient;
  }

  @Override
//  @CircuitBreaker(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
//  @Retry(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
  @RateLimiter(name = "companyBreaker", fallbackMethod = "companyBreakerFallback")
  public List<JobDto> findJobs() {
    // 1. Fetch all jobs
    return jobRepository.findAll().stream()
      .map(job -> {
        // 2. Fetch the company for each job
        Company company = companyClient.getCompanyById(job.getCompanyId());

        // 3. Map the Job and Company to JobDto
        return JobMapper.toJobDto(job, company);
      })
      .toList();
  }

  public List<String> companyBreakerFallback(Exception e) {
    List<String> list = new ArrayList<>();
    list.add("Down");
    return list;
  }

  @Override
  public boolean addJob(Job job) {
    List<Company> companies = companyClient.getCompanies();
    Long companyId = job.getCompanyId();
    for (Company company : companies) {
      if (companyId.equals(company.getId())) {
        jobRepository.save(job);
        return true;
      }
    }
    return false;
  }

  @Override
  public JobDto findJob(long id) {
   Optional<Job> jobOptional = jobRepository.findById(id);
   if (jobOptional.isPresent()) {
     Job job = jobOptional.get();
     Company company = companyClient.getCompanyById(job.getCompanyId());
     return JobMapper.toJobDto(job, company);
   }
   return null;
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
