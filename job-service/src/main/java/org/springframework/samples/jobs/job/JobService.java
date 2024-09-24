package org.springframework.samples.jobs.job;

import java.util.List;

public interface JobService {
  List<JobDto> findJobs();

  boolean addJob(Job job);

  JobDto findJob(long id);

  boolean deleteJob(long id);

  boolean updateJob(long id, Job job);
}
