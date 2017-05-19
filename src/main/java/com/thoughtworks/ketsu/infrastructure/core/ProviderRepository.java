package com.thoughtworks.ketsu.infrastructure.core;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProviderRepository {
    Provider createProvider(Map<String, Object> info);

    List<Provider> findAll();

    Optional<Provider> findById(long id);

    int update(Map<String, Object> info, long id);

    int delete(long id);
}
