package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@RunWith(ApiTestRunner.class)
public class ProvidersApiTest extends ApiSupport{
    @Test
    public void should_return_201_and_uri_when_create_provider_with_specified_parameters() throws Exception {
        final String name = "provider";
        final Response post = post("/providers", TestHelper.providerJsonForTest(name));

        assertThat(post.getStatus(), is(201));
        assertThat(Pattern.matches(".*?/providers/[0-9-]*", post.getLocation().toASCIIString()), is(true));
    }

    @Test
    public void should_return_200_when_get_all_providers() throws Exception {
        final Response get = get("/providers");

        assertThat(get.getStatus(), is(200));
    }
}
