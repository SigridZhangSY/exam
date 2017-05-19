package com.thoughtworks.ketsu.infrastructure.core;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ContainerRepository {
    Container save(@Param("info") Map<String, Object>info);

    List<Container> findAll();

    Optional<Container> findById(long id);
}
