package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.core.Provider;
import com.thoughtworks.ketsu.infrastructure.core.ProviderRepository;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
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
    public Response updateProvider(@Context ProviderRepository providerRepository,
                                   Map<String, Object> info){
        providerRepository.update(info, provider.getId());
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
