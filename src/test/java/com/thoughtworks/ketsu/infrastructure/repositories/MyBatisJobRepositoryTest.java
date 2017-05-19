package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.infrastructure.core.*;
import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

@RunWith(DatabaseTestRunner.class)
public class MyBatisJobRepositoryTest {
    @Inject
    JobRepository jobRepository;
    @Inject
    ContainerRepository containerRepository;
    @Inject
    ProviderRepository providerRepository;

    @Test
    public void should_create_job() throws Exception {
        containerRepository.save(TestHelper.containerJsonForTest(201));
        containerRepository.save(TestHelper.containerJsonForTest(202));
        Provider provider = providerRepository.createProvider(TestHelper.providerJsonForTest("PROVIDER"));
        List<Integer> containers = new ArrayList<Integer>();
        containers.add(Integer.valueOf(201));
        containers.add(Integer.valueOf(202));

        Job job = jobRepository.create(TestHelper.jobJsonForTest(provider.getId(), containers));
        assertThat(job.getId(), notNullValue());
    }
}