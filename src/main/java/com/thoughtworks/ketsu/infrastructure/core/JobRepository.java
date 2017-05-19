package com.thoughtworks.ketsu.infrastructure.core;

import java.util.Map;

public interface JobRepository {
    Job create(Map<String, Object> info);
}
