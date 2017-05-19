package com.thoughtworks.ketsu.infrastructure.core;

import java.util.Map;

public interface ProviderRepository {
    Provider createProvider(Map<String, Object> info);
}
