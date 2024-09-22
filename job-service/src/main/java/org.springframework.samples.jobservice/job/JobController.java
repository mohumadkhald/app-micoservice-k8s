package org.springframework.samples.jobservice.job;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {
  private final JobService jobService;

//  @RequestMapping(value = "/jobs", method = RequestMethod.GET)
  @GetMapping
  public List<Job> findJobs() {
    return jobService.findJobs();
  }

  @PostMapping
  public ResponseEntity<String> addJob(@RequestBody Job job) {

    boolean added = jobService.addJob(job);
    if (added) {
      return new ResponseEntity<>(HttpStatus.CREATED);
    }
    return new ResponseEntity<>("Company Not found", HttpStatus.NOT_FOUND);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Job> findJob(@PathVariable("id") long id) {
    Job job = jobService.findJob(id);
    if (job == null) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(job, HttpStatus.FOUND);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteJob(@PathVariable("id") long id) {
    boolean deleted = jobService.deleteJob(id);
    if (deleted) {
      return new ResponseEntity<>("This job Deleted",HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> updateJob(@PathVariable("id") long id, @RequestBody Job job) {
    boolean updated = jobService.updateJob(id, job);
    if (updated) {
      return new ResponseEntity<>("This job Updated",HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
