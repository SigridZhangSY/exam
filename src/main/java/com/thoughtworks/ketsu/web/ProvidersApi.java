package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.core.Provider;
import com.thoughtworks.ketsu.infrastructure.core.ProviderRepository;
import com.thoughtworks.ketsu.infrastructure.records.ProviderRecord;
import com.thoughtworks.ketsu.web.jersey.Routes;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

@Path("providers")
public class ProvidersApi {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProvider(Map<String, Object> info,
                                   @Context Routes routes,
                                   @Context ProviderRepository providerRepository){

        return Response.created(routes.providerUrl(providerRepository.createProvider(info))).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getAllProviders(@Context Routes routes,
                                               @Context ProviderRepository providerRepository){
        List<Provider> providers = providerRepository.findAll();

        return new HashMap<String, Object>(){{
            put("totalCount", providers.size());
            put("providers", providers.stream().map(provider -> ((ProviderRecord)provider).toJson(routes)).collect(toList()));
        }};
    }

}
