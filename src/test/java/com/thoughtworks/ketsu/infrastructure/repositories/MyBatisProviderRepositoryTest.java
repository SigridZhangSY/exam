package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.infrastructure.core.Provider;
import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(DatabaseTestRunner.class)
public class MyBatisProviderRepositoryTest {
    @Inject
    MyBatisProviderRepository providerRepository;

    @Test
    public void should_create_and_get_provider() throws Exception {
        String providerName = "testProvider";
        Provider res = providerRepository.createProvider(TestHelper.providerJsonForTest(providerName));

        assertThat(res.getName(), is(providerName));
        assertThat(res.getId(), notNullValue());
    }
}
