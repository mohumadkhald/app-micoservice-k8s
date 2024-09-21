package org.springframework.samples.first;

import java.util.List;

public interface JobService {
  List<Job> findJobs();

  Job addJob(Job job);

  Job findJob(long id);

  boolean deleteJob(long id);

  boolean updateJob(long id, Job job);
}
