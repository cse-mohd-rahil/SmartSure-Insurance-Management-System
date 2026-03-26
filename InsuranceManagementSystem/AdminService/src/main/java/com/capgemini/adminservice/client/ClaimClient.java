package com.capgemini.adminservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "ClaimsService")  
public interface ClaimClient {

    @GetMapping("/api/claims")
    List<Object> getClaims(@RequestHeader("Authorization") String token);
}