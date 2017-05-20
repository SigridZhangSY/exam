package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.core.*;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Before;
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

    private Provider provider;

    private List<Integer> containers;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        containerRepository.save(TestHelper.containerJsonForTest(301));
        containerRepository.save(TestHelper.containerJsonForTest(302));
        provider = providerRepository.createProvider(TestHelper.providerJsonForTest("PROVIDER"));
        containers = new ArrayList<Integer>();
        containers.add(Integer.valueOf(301));
        containers.add(Integer.valueOf(302));
    }

    @Test
    public void should_201_and_uri_when_post() throws Exception {
        Response post = post("/jobs", TestHelper.jobJsonForTest(provider.getId(), containers));

        assertThat(post.getStatus(), is(201));
        assertThat(post.getLocation().toASCIIString().contains("/jobs"), is(true));
    }

    @Test
    public void should_200_and_detail_when_get_all() throws Exception {
        jobRepository.create(TestHelper.jobJsonForTest(provider.getId(), containers));

        final Response get = get("/jobs");
        assertThat(get.getStatus(), is(200));
        Map res = get.readEntity(Map.class);
        assertThat(res.get("totalCount"), is(1));
        assertThat(res.get("jobs"), notNullValue());
    }

    @Test
    public void should_200_when_get_job_by_id() throws Exception {
        Job job = jobRepository.create(TestHelper.jobJsonForTest(provider.getId(), containers));

        final Response get = get("/jobs/" + job.getId());
        assertThat(get.getStatus(), is(200));
        Map res = get.readEntity(Map.class);
        assertThat(res.get("provider_id"), notNullValue());
        assertThat(res.get("id"), notNullValue());
        assertThat(res.get("containers"), notNullValue());
    }

    @Test
    public void should_204_when_update_job() throws Exception {
        Job job = jobRepository.create(TestHelper.jobJsonForTest(provider.getId(), containers));
        Provider newProvider = providerRepository.createProvider(TestHelper.providerJsonForTest("NEWPROVIDER"));
        containers = new ArrayList<Integer>();

        final Response put = put("/jobs/" + job.getId(), TestHelper.jobJsonForTest(newProvider.getId(), containers));

        assertThat(put.getStatus(), is(204));
    }

    @Test
    public void should_return_204_when_delete_job() throws Exception {
        Job job = jobRepository.create(TestHelper.jobJsonForTest(provider.getId(), containers));

        final Response delete = delete("/jobs/" + job.getId());

        assertThat(delete.getStatus(), is(204));
    }
}