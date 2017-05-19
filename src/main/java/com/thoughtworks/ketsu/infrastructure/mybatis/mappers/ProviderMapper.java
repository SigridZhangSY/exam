package com.thoughtworks.ketsu.infrastructure.mybatis.mappers;

import com.thoughtworks.ketsu.infrastructure.core.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProviderMapper {
    void save(@Param("info") Map<String, Object> info);

    Provider findById(@Param("id") long id);

    List<Provider> findAll();

    int update(@Param("info") Map<String, Object> info, @Param("id") long id);

    int delete(@Param("id") long id);
}
