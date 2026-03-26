package com.capgemini.adminservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "AuthService")
public interface AuthClient {

    @GetMapping("/api/users")
    List<Object> getAllUsers(@RequestHeader("Authorization") String token);
}