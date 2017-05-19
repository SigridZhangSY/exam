package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.core.ProviderRepository;
import com.thoughtworks.ketsu.infrastructure.records.ProviderRecord;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("providers")
public class ProvidersApi {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProvider(Map<String, Object> info,
                                   @Context Routes routes,
                                   @Context ProviderRepository providerRepository){

        return Response.created(routes.providerUrl(providerRepository.createProvider(info))).build();
    }

}
