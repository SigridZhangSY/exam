package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.core.*;
import com.thoughtworks.ketsu.infrastructure.core.Container;
import com.thoughtworks.ketsu.infrastructure.records.ContainerRecord;
import com.thoughtworks.ketsu.infrastructure.records.ProviderRecord;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.*;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Path("containers")
public class ContainersApi {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Map<String, Object>info,
                           @Context ContainerRepository containerRepository,
                           @Context Routes routes){
        return Response.created(routes.containerUrl(containerRepository.save(info))).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getAllContainers(@Context ContainerRepository containerRepository,
                                                @Context Routes routes){
        List<Container> containers = containerRepository.findAll();

        return new HashMap<String, Object>() {{
            put("totalCount", containers.size());
            put("containers", containers.stream().map(container -> ((ContainerRecord) container).toJson(routes)).collect
                    (toList()));
        }};
    }
}
