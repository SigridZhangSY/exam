package com.thoughtworks.ketsu.infrastructure.records;

import com.thoughtworks.ketsu.infrastructure.core.Job;

public class JobRecord implements Job {
    private long id;
    private long provider_id;

    @Override
    public long getId() {
        return id;
    }
}
