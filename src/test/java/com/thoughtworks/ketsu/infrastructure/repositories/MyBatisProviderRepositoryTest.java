package com.thoughtworks.ketsu.infrastructure.repositories;

import com.thoughtworks.ketsu.infrastructure.core.Provider;
import com.thoughtworks.ketsu.support.DatabaseTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @Test
    public void should_find_all_providers() throws Exception {
        String providerName = "testProvider";
        Provider provider = providerRepository.createProvider(TestHelper.providerJsonForTest(providerName));
        List<Provider> res = providerRepository.findAll();

        assertThat(res.size(), is(1));
        assertThat(res.get(0).getName(), is(providerName));
    }


    @Test
    public void should_get_provider_by_id() throws Exception {
        String providerName = "testProvider";
        Provider provider = providerRepository.createProvider(TestHelper.providerJsonForTest(providerName));

        Optional<Provider> res = providerRepository.findById(provider.getId());
        assertThat(res.isPresent(), is(true));
    }

    @Test
    public void should_update_provider() throws Exception {
        Provider provider = providerRepository.createProvider(TestHelper.providerJsonForTest("provider"));
        String newName = "newName";
        long newId = provider.getId() + 1;
        Map info = new HashMap<String, Object>(){{
            put("name", newName);
            put("id", newId);
        }};
        providerRepository.update(info, provider.getId());
        provider = providerRepository.findById(newId).get();

        assertThat(provider.getName(), is(newName));
        assertThat(provider.getId(), is(newId));
    }

    @Test
    public void should_delete_provider() throws Exception {
        Provider provider = providerRepository.createProvider(TestHelper.providerJsonForTest("provider"));
        providerRepository.delete(provider.getId());

        Optional<Provider> res = providerRepository.findById(provider.getId());
        assertThat(res.isPresent(), is(false));
    }
}
