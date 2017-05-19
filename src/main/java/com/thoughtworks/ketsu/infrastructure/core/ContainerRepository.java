package com.thoughtworks.ketsu.infrastructure.core;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ContainerRepository {
    Container save(Map<String, Object>info);

    List<Container> findAll();

    Optional<Container> findById(long id);

    int update(Map<String, Object>info, long id);
}
