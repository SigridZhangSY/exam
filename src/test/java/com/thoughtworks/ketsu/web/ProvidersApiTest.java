package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.core.Provider;
import com.thoughtworks.ketsu.infrastructure.core.ProviderRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static java.lang.Math.toIntExact;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@RunWith(ApiTestRunner.class)
public class ProvidersApiTest extends ApiSupport{
    @Inject
    ProviderRepository providerRepository;

    @Test
    public void should_return_201_and_uri_when_create_provider_with_specified_parameters() throws Exception {
        final String name = "provider";
        final Response post = post("/providers", TestHelper.providerJsonForTest(name));

        assertThat(post.getStatus(), is(201));
        assertThat(Pattern.matches(".*?/providers/[0-9-]*", post.getLocation().toASCIIString()), is(true));
    }

    @Test
    public void should_return_detail_when_get_all_providers() throws Exception {
        final String name = "provider";
        final Provider provider = providerRepository.createProvider(TestHelper.providerJsonForTest(name));
        final Response get = get("/providers");

        assertThat(get.getStatus(), is(200));
        final Map<String, Object> map = get.readEntity(Map.class);
        assertThat(map.get("totalCount"), is(1));
        assertThat(((List)map.get("providers")).size(), is(1));
    }

    @Test
    public void should_return_detail_when_get_provider_by_id() throws Exception {
        final Provider provider = providerRepository.createProvider(TestHelper.providerJsonForTest("provider"));
        final Response get = get("/providers/" + provider.getId());

        assertThat(get.getStatus(), is(200));
        final Map<String, Object> map = get.readEntity(Map.class);
        assertThat(map.get("name"), is(provider.getName()));
        assertThat(map.get("id"), is(toIntExact(provider.getId())));
    }

    @Test
    public void should_return_204_when_update_provider() throws Exception {
        final Provider provider = providerRepository.createProvider(TestHelper.providerJsonForTest("provider"));
        final Response put = put("/providers/" + provider.getId(), new HashMap<String, Object>(){{
            put("name", "newName");
            put("id", provider.getId()+1);
        }});

        assertThat(put.getStatus(), is(204));
        final Response get = get("/providers/" + (provider.getId()+1));
        final Map<String, Object> map = get.readEntity(Map.class);
        assertThat(String.valueOf(map.get("name")), is("newName"));
    }
}
