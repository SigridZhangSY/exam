package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.core.Container;
import com.thoughtworks.ketsu.infrastructure.core.ContainerRepository;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

public class ContainerApi {
    private Container container;

    public ContainerApi (Container container){
        this.container = container;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Container get(){
        return container;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Map<String, Object> info,
                           @Context ContainerRepository containerRepository){
        containerRepository.update(info, container.getId());
        return Response.status(204).build();
    }
}
