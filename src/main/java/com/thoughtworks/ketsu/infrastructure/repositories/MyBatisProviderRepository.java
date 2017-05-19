package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.infrastructure.core.Provider;
import com.thoughtworks.ketsu.infrastructure.core.ProviderRepository;
import com.thoughtworks.ketsu.infrastructure.mybatis.mappers.ProviderMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

public class MyBatisProviderRepository implements ProviderRepository {
    @Inject
    ProviderMapper providerMapper;

    @Override
    public Provider createProvider(Map<String, Object> info) {
        providerMapper.save(info);

        return providerMapper.findById(Long.valueOf(String.valueOf(info.get("id"))));
    }

    @Override
    public List<Provider> findAll() {
        return providerMapper.findAll();
    }
}
