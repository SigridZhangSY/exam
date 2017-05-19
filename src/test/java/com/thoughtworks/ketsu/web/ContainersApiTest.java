package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.support.ApiSupport;
import com.thoughtworks.ketsu.support.ApiTestRunner;
import com.thoughtworks.ketsu.support.TestHelper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.core.Response;

import java.util.HashMap;
import java.util.regex.Pattern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(ApiTestRunner.class)
public class ContainersApiTest extends ApiSupport {

    @Test
    public void should_return_201_when_create() throws Exception {
        Response post = post("/containers", TestHelper.containerJsonForTest(1));

        assertThat(post.getStatus(), is(201));
        assertThat(Pattern.matches(".*?/containers/[0-9-]*", post.getLocation().toASCIIString()), Matchers.is(true));
    }
}