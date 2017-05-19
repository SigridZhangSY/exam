package com.thoughtworks.ketsu.web.jersey;

import com.thoughtworks.ketsu.domain.user.User;
import com.thoughtworks.ketsu.infrastructure.core.Container;
import com.thoughtworks.ketsu.infrastructure.core.Job;
import com.thoughtworks.ketsu.infrastructure.core.Provider;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

public class Routes {

    private final String baseUri;

    public Routes(UriInfo uriInfo) {
        baseUri = uriInfo.getBaseUri().toASCIIString();
    }

    public URI userUrl(User user) {
        return URI.create(String.format("%susers/%s", baseUri, user.getUserId().id()));
    }

    public URI providerUrl(Provider provider) { return URI.create(String.format("%sproviders/%s", baseUri, String
            .valueOf(provider.getId())));}

    public URI containerUrl(Container container) { return URI.create(String.format("%scontainers/%s", baseUri, String
            .valueOf(container.getId())));}

    public URI jobUrl(Job job) { return URI.create(String.format("%sjobs/%s", baseUri, String
            .valueOf(job.getId())));}
}
