package org.springframework.samples.first;

import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface JobRepository extends JpaRepository<Job, Long> {
}
