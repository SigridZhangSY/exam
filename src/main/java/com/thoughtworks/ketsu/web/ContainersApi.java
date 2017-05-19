package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.core.ContainerRepository;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.Map;

@Path("containers")
public class ContainersApi {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Map<String, Object>info,
                           @Context ContainerRepository containerRepository){
        return Response.status(201).build();
    }
}
