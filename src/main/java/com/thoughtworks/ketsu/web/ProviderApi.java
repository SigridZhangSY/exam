package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.core.Provider;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class ProviderApi {
    private Provider provider;

    public ProviderApi(Provider provider){
        this.provider = provider;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Provider get(){
        return provider;
    }

    @PUT
    public Response updateProvider(){
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
