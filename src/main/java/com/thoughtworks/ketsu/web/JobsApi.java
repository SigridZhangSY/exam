package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.core.ContainerRepository;
import com.thoughtworks.ketsu.infrastructure.core.JobRepository;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("jobs")
public class JobsApi {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Map<String, Object>info,
                           @Context JobRepository jobRepository,
                           @Context Routes routes){
        return Response.created(routes.jobUrl(jobRepository.create(info))).build();
    }
}
