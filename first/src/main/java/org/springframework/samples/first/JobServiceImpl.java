package org.springframework.samples.first;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class JobServiceImpl implements JobService{
  private final List<Job> jobs = new ArrayList<>();
//  private Long nextId = 1L; // if not have entity with @id

  @Override
  public List<Job> findJobs() {
    return jobs;
  }

  @Override
  public Job addJob(Job job) {
//    job.setId(nextId++); // if not have entity with @id
    jobs.add(job);
    return job;
  }

  @Override
  public Job findJob(long id) {
    for (Job job : jobs) {
      if (job.getId() == id) {
        return job;
      }
    }
    return null;
  }

  @Override
  public boolean deleteJob(long id) {
//    Iterator<Job> iterator = jobs.iterator();
//    while (iterator.hasNext()) {
//      Job job = iterator.next();
//      if (job.getId() == id) {
//        iterator.remove();
//        return true;
//      }
//    }
    return jobs.removeIf(job -> job.getId() == id);
  }

  @Override
  public boolean updateJob(long id, Job updatedJob) {
    for (Job job : jobs) {
      if (job.getId() == id) {
        job.setTitle(updatedJob.getTitle());
        job.setDescription(updatedJob.getDescription());
        job.setStatus(updatedJob.getStatus());
        job.setMinSalary(updatedJob.getMinSalary());
        job.setMaxSalary(updatedJob.getMaxSalary());
        return true;
      }
    }
    return false;
  }
}
