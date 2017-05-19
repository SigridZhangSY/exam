package com.thoughtworks.ketsu.infrastructure.records;

import com.thoughtworks.ketsu.infrastructure.core.Provider;

public class ProviderRecord implements Provider {
    private long id;
    private String name;

    public ProviderRecord (long id){
        this.id = id;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }
}
