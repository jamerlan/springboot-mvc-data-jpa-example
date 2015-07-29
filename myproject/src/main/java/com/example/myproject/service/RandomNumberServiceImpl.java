package com.example.myproject.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;

@Service
public class RandomNumberServiceImpl implements RandomNumberService {
    @Override
    public long getNextLong(long bound) {
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        return threadLocalRandom.nextLong(bound);
    }
}
