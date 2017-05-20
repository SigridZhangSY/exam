package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.infrastructure.core.*;
import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(DatabaseTestRunner.class)
public class MyBatisJobRepositoryTest {
    @Inject
    JobRepository jobRepository;
    @Inject
    ContainerRepository containerRepository;
    @Inject
    ProviderRepository providerRepository;

    private Provider provider;
    private List<Integer> containers;

    @Before
    public void setUp() throws Exception {
        containerRepository.save(TestHelper.containerJsonForTest(201));
        containerRepository.save(TestHelper.containerJsonForTest(202));
        provider = providerRepository.createProvider(TestHelper.providerJsonForTest("PROVIDER"));
        containers = new ArrayList<Integer>();
        containers.add(Integer.valueOf(201));
        containers.add(Integer.valueOf(202));
    }

    @Test
    public void should_create_job() throws Exception {
        Job job = jobRepository.create(TestHelper.jobJsonForTest(provider.getId(), containers));

        assertThat(job.getId(), notNullValue());
    }


    @Test
    public void should_update_job() throws Exception {
        Job job = jobRepository.create(TestHelper.jobJsonForTest(provider.getId(), containers));
        Provider newProvider = providerRepository.createProvider(TestHelper.providerJsonForTest("NEWPROVIDER"));

        containerRepository.save(TestHelper.containerJsonForTest(203));
        containerRepository.save(TestHelper.containerJsonForTest(204));
        containers = new ArrayList<Integer>();
        containers.add(Integer.valueOf(203));

        jobRepository.update(TestHelper.jobJsonForTest(newProvider.getId(), containers), job.getId());

        Job res = jobRepository.findById(job.getId()).get();
        assertThat(res.getProviderId(), is(newProvider.getId()));
        assertThat(res.getContainers().size(), is(1));
    }
}