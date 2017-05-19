package com.thoughtworks.ketsu.web;

import com.thoughtworks.ketsu.infrastructure.core.Provider;

public class ProviderResource {
    private Provider provider;

    public ProviderResource(Provider provider){
        this.provider = provider;
    }
}
