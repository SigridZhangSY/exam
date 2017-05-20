package com.thoughtworks.ketsu.infrastructure.core;

import java.util.List;
import java.util.Map;

public interface JobRepository {
    Job create(Map<String, Object> info);

    List<Job> findAll();
}
