package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.core.ContainerRepository;
import com.thoughtworks.ketsu.infrastructure.core.JobRepository;
import com.thoughtworks.ketsu.infrastructure.core.Provider;
import com.thoughtworks.ketsu.infrastructure.core.ProviderRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(ApiTestRunner.class)
public class JobsApiTest extends ApiSupport {
    @Inject
    ContainerRepository containerRepository;
    @Inject
    ProviderRepository providerRepository;
    @Inject
    JobRepository jobRepository;

    @Test
    public void should_201_and_uri_when_post() throws Exception {
        containerRepository.save(TestHelper.containerJsonForTest(301));
        containerRepository.save(TestHelper.containerJsonForTest(302));
        Provider provider = providerRepository.createProvider(TestHelper.providerJsonForTest("PROVIDER"));
        List<Integer> containers = new ArrayList<Integer>();
        containers.add(Integer.valueOf(301));
        containers.add(Integer.valueOf(302));

        Response post = post("/jobs", TestHelper.jobJsonForTest(provider.getId(), containers));

        assertThat(post.getStatus(), is(201));
        assertThat(post.getLocation().toASCIIString().contains("/jobs"), is(true));
    }

    @Test
    public void should_200_and_detail_when_get_all() throws Exception {
        containerRepository.save(TestHelper.containerJsonForTest(303));
        containerRepository.save(TestHelper.containerJsonForTest(304));
        Provider provider = providerRepository.createProvider(TestHelper.providerJsonForTest("PROVIDER"));
        List<Integer> containers = new ArrayList<Integer>();
        containers.add(Integer.valueOf(303));
        containers.add(Integer.valueOf(304));
        jobRepository.create(TestHelper.jobJsonForTest(provider.getId(), containers));

        final Response get = get("/jobs");
        Map res = get.readEntity(Map.class);
        assertThat(res.get("totalCount"), notNullValue());
        assertThat(res.get("jobs"), notNullValue());

    }
}