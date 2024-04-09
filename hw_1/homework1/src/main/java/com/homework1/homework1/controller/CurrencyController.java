package com.homework1.homework1.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.*;

import com.homework1.homework1.services.CacheService;


@RestController
@RequestMapping("/currency")
public class CurrencyController {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private final CacheService cacheService = new CacheService(cacheManager);

    @GetMapping("/getall")
    public Map<String,Object> getAllTrips() {
        return cacheService.getFromCache("currencies");
    }
}
