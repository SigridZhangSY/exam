package com.thoughtworks.ketsu.infrastructure.core;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface JobRepository {
    Job create(Map<String, Object> info);

    List<Job> findAll();

    Optional<Job> findById(long jobId);
}
