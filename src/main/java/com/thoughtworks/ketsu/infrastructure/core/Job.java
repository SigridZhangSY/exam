package com.thoughtworks.ketsu.infrastructure.core;

import java.util.List;

public interface Job {
    long getId();

    long getProviderId();

    List<Container> getContainers();
}
