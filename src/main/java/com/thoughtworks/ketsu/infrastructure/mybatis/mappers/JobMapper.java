package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.infrastructure.core.Job;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface JobMapper {
    int save(@Param("info")Map<String, Object> info);

    Job findById(@Param("id")long id);

    int addContainer(@Param("job_id")long job_id, @Param("container_id")long container_id);

    List<Job> findAll();
}
