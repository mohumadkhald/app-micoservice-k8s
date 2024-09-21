package org.springframework.samples.first.job;

import org.springframework.samples.first.company.Company;
import org.springframework.samples.first.company.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService{

  private final JobRepository jobRepository;
  private final CompanyRepository companyRepository;

  public JobServiceImpl(JobRepository jobRepository, CompanyRepository companyRepository) {
    this.jobRepository = jobRepository;
    this.companyRepository = companyRepository;
  }

  @Override
  public List<Job> findJobs() {
    return jobRepository.findAll();
  }

  @Override
  public boolean addJob(Job job) {
    Long conpanyId = job.getCompany().getId();
    Optional<Company> company = companyRepository.findById(conpanyId);
    if (company.isPresent()) {
      job.setCompany(company.get());
      jobRepository.save(job);
      return true;
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
      jobRepository.save(job);
      return true;
    }
    return false;
  }
}
