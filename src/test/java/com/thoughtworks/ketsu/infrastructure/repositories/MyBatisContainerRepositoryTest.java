package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.infrastructure.core.Container;
import com.thoughtworks.ketsu.infrastructure.core.ContainerRepository;
import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

@RunWith(DatabaseTestRunner.class)
public class MyBatisContainerRepositoryTest {
    @Inject
    ContainerRepository containerRepository;

    @Test
    public void should_save_and_find_container() throws Exception {
        Container res = containerRepository.save(TestHelper.containerJsonForTest(1));
        assertThat(res.getId(), notNullValue());
    }
}