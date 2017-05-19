package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.core.Provider;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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

}
