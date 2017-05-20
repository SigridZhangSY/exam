package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.core.Job;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
}
