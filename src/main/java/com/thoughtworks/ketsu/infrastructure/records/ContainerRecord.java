package com.thoughtworks.ketsu.infrastructure.records;

import com.thoughtworks.ketsu.infrastructure.core.Container;
import com.thoughtworks.ketsu.web.jersey.Routes;

import java.util.HashMap;
import java.util.Map;

public class ContainerRecord implements Container, Record{
    long id;

    @Override
    public long getId() {
        return id;
    }

    @Override
    public Map<String, Object> toRefJson(Routes routes) {
        return toJson(routes);
    }

    @Override
    public Map<String, Object> toJson(Routes routes) {
        return new HashMap<String, Object>(){{
            put("id", id);
        }};
    }
}
