package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.infrastructure.core.Container;
import com.thoughtworks.ketsu.infrastructure.core.ContainerRepository;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.ContainerMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

public class MyBatisContainerRepository implements ContainerRepository {
   @Inject
    ContainerMapper containerMapper;
    @Override
    public Container save(Map<String, Object> info) {
        containerMapper.save(info);
        return containerMapper.findById(Long.valueOf(String.valueOf(info.get("id"))));
    }

    @Override
    public List<Container> findAll() {
        return containerMapper.findAll();
    }
}
