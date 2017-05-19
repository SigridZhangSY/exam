package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.infrastructure.core.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProviderMapper {
    void save(@Param("info") Map<String, Object> info);

    Provider findById(long id);

    List<Provider> findAll();
}
