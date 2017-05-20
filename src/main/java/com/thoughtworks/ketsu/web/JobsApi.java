package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.core.ContainerRepository;
import com.thoughtworks.ketsu.infrastructure.core.Job;
import com.thoughtworks.ketsu.infrastructure.core.JobRepository;
import com.thoughtworks.ketsu.infrastructure.records.JobRecord;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Path("jobs")
public class JobsApi {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Map<String, Object> info,
                           @Context JobRepository jobRepository,
                           @Context Routes routes) {
        return Response.created(routes.jobUrl(jobRepository.create(info))).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getAll(@Context JobRepository jobRepository,
                                      @Context Routes routes) {
        List<Job> jobs = jobRepository.findAll();
        return new HashMap<String, Object>() {{
            put("totalCount", jobs.size());
            put("jobs", jobs.stream().map(job -> ((JobRecord) job).toJson(routes)).collect(toList()));
        }};
    }

    @Path("{id}")
    public JobApi getJobApi(@PathParam("id") long jobId,
                            @Context JobRepository jobRepository) {
        Job job = jobRepository.findById(jobId).orElseThrow(() -> new NotFoundException("JOB NOT FOUND"));
        return new JobApi(job);
    }
}
