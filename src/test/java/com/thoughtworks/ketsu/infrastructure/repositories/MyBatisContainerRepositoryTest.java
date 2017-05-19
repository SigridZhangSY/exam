package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.infrastructure.core.Container;
import com.thoughtworks.ketsu.infrastructure.core.ContainerRepository;
import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(DatabaseTestRunner.class)
public class MyBatisContainerRepositoryTest {
    @Inject
    ContainerRepository containerRepository;

    @Test
    public void should_save_and_find_container() throws Exception {
        Container res = containerRepository.save(TestHelper.containerJsonForTest(101));
        assertThat(res.getId(), notNullValue());
    }

    @Test
    public void should_find_container() throws Exception {
        Container container = containerRepository.save(TestHelper.containerJsonForTest(102));

        Optional<Container> res = containerRepository.findById(container.getId());

        assertThat(res.isPresent(), is(true));
    }

    @Test
    public void should_update_container() throws Exception {
        Container container = containerRepository.save(TestHelper.containerJsonForTest(103));
        long newId = 104;

        containerRepository.update(TestHelper.containerJsonForTest(newId), container.getId());

        Optional<Container> res = containerRepository.findById(newId);
        assertThat(res.isPresent(), is(true));
    }

    @Test
    public void should_delete_container() throws Exception {
        Container container = containerRepository.save(TestHelper.containerJsonForTest(104));

        containerRepository.delete(104);

        Optional<Container> res = containerRepository.findById(104);
        assertThat(res.isPresent(), is(false));
    }
}