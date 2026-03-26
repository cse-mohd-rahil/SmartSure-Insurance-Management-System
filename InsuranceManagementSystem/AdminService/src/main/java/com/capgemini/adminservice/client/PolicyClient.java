package com.capgemini.adminservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "PolicyService")
public interface PolicyClient {

    @GetMapping("/api/policies")
    List<Object> getPolicies(@RequestHeader("Authorization") String token);
}