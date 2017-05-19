package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


@RunWith(ApiTestRunner.class)
public class ProvidersApiTest extends ApiSupport{
    @Test
    public void should_return_201_when_create_provider() throws Exception {
        Map <String, Object> map = new HashMap<>();
        final Response post = post("/providers", map);

        assertThat(post.getStatus(), is(201));
        assertThat(Pattern.matches(".*?/providers/[0-9-]*", post.getLocation().toASCIIString()), is(true));
    }
}
