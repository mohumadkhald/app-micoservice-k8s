package org.springframework.samples.jobservice.job;

import java.util.List;

public interface JobService {
  List<Job> findJobs();

  boolean addJob(Job job);

  Job findJob(long id);

  boolean deleteJob(long id);

  boolean updateJob(long id, Job job);
}
