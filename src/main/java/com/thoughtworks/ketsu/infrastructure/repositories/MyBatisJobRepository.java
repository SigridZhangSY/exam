package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.infrastructure.core.Job;
import com.thoughtworks.ketsu.infrastructure.core.JobRepository;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.JobMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

public class MyBatisJobRepository implements JobRepository {
    @Inject
    JobMapper jobMapper;

    @Override
    public Job create(Map<String, Object> info) {
        jobMapper.save(info);
        List<Integer> containers = (List<Integer>) info.get("containers");
        for(int i = 0; i < containers.size(); i++){
            jobMapper.addContainer(Long.valueOf(String.valueOf(info.get("id"))), containers.get(i));
        }

        return jobMapper.findById(Long.valueOf(String.valueOf(info.get("id"))));
    }
}
