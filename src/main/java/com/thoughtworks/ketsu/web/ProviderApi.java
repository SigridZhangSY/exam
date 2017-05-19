package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.core.Provider;
import com.thoughtworks.ketsu.infrastructure.core.ProviderRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

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
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProvider(@Context ProviderRepository providerRepository,
                                   Map<String, Object> info){
        providerRepository.update(info, provider.getId());
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    public Response deleteProvider(@Context ProviderRepository providerRepository){
        providerRepository.delete(provider.getId());
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
