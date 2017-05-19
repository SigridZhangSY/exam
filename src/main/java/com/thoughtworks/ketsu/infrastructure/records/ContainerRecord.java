package com.thoughtworks.ketsu.infrastructure.records;

import com.thoughtworks.ketsu.infrastructure.core.Container;

public class ContainerRecord implements Container{
    long id;

    @Override
    public long getId() {
        return id;
    }
}
