package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.core.Job;
import com.thoughtworks.ketsu.infrastructure.core.JobRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

public class JobApi {
    private Job job;

    public JobApi (Job job){
        this.job = job;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Job get(){
        return job;
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Map<String, Object> info,
                           @Context JobRepository jobRepository){
        jobRepository.update(info, job.getId());
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    public Response delete(@Context JobRepository jobRepository){
        jobRepository.delete(job.getId());
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
