package org.springframework.samples.first;

import org.springframework.stereotype.Service;


import java.util.List;

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
    jobRepository.deleteById(id);
    return true;
  }

  @Override
  public boolean updateJob(long id, Job updatedJob) {

    Job job = jobRepository.findById(id).orElse(null);
    if (job != null) {
      job.setMinSalary(updatedJob.getMinSalary());
      job.setMaxSalary(updatedJob.getMaxSalary());
    }
    return true;
  }
}
