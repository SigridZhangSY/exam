package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.records.ProviderRecord;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("providers")
public class ProvidersApi {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProvider(@Context Routes routes){

        return Response.created(routes.providerUrl(new ProviderRecord(1))).build();
    }

}
