package com.ebanma.cloud.demo.domain.service;

import org.springframework.stereotype.Component;

@Component
public class DefaultGenerator implements IdGenerator{
    @Override
    public Long next() {
        return null;
    }
}
