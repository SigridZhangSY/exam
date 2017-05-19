package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.core.Container;
import com.thoughtworks.ketsu.infrastructure.core.ContainerRepository;
import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(ApiTestRunner.class)
public class ContainersApiTest extends ApiSupport {
    @Inject
    ContainerRepository containerRepository;
    @Test
    public void should_return_201_when_create() throws Exception {
        Response post = post("/containers", TestHelper.containerJsonForTest(1));

        assertThat(post.getStatus(), is(201));
        assertThat(Pattern.matches(".*?/containers/[0-9-]*", post.getLocation().toASCIIString()), Matchers.is(true));
    }

    @Test
    public void should_get_all_containers() throws Exception {
        Container container = containerRepository.save(TestHelper.containerJsonForTest(2));

        Response get = get("/containers");
        assertThat(get.getStatus(), is(200));
        Map map = get.readEntity(Map.class);
        assertThat(map.get("totalCount"), is(1));
        assertThat(((List)map.get("containers")).size(), is(1));
        assertThat(((Map)((List)map.get("containers")).get(0)).get("id"), is(2));
    }

    @Test
    public void should_get_200_and_detail_when_get_container_by_id() throws Exception {
        Container container = containerRepository.save(TestHelper.containerJsonForTest(3));

        Response get = get("/containers/" + container.getId());
        assertThat(get.getStatus(), is(200));
        Map map = get.readEntity(Map.class);
        assertThat(map.get("id"), notNullValue());
    }

    @Test
    public void should_return_204_and_update_container() throws Exception {
        Container container = containerRepository.save(TestHelper.containerJsonForTest(4));

        Response put = put("/containers/" + container.getId(), TestHelper.containerJsonForTest(5));
        assertThat(put.getStatus(), is(204));
    }
}