package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.infrastructure.core.Container;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ContainerMapper {
    void save(@Param("info") Map<String, Object> info);

    Container findById(@Param("id") long id);

    List<Container> findAll();

    int update(@Param("info") Map<String, Object> info, @Param("id") long id);

    int delete(@Param("id") long id);
}
