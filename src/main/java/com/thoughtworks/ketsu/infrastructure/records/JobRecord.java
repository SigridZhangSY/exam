package com.thoughtworks.ketsu.infrastructure.records;

import com.thoughtworks.ketsu.infrastructure.core.Container;
import com.thoughtworks.ketsu.infrastructure.core.Job;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class JobRecord implements Job, Record{
    private long id;
    private long provider_id;
    private List<Container> containers;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return null;
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return new HashMap<String, Object>(){{
            put("id", id);
            put("provider_id", provider_id);
            put("containers", containers.stream().map(container -> container.getId()).collect(toList()));
        }};
    }
}
