package org.springframework.samples.first;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService{

  private final JobRepository jobRepository;

  public JobServiceImpl(JobRepository jobRepository) {
    this.jobRepository = jobRepository;
  }

  @Override
  public List<Job> findJobs() {
    return jobRepository.findAll();
  }

  @Override
  public Job addJob(Job job) {
    jobRepository.save(job);
    return job;
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
