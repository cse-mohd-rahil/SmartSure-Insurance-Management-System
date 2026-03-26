package com.capgemini.policyservice.controller;


import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.capgemini.policyservice.dto.PolicyRequest;
import com.capgemini.policyservice.dto.PolicyResponse;
import com.capgemini.policyservice.service.PolicyService;



@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    private PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    //CREATE POLICY (ADMIN)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public PolicyResponse createPolicy(@RequestBody PolicyRequest request) {
        return policyService.createPolicy(request);
    }

    //PURCHASE POLICY (CUSTOMER)
    @PostMapping("/purchase/{id}")
    public PolicyResponse purchasePolicy(@PathVariable Long id) {
        return policyService.purchasePolicy(id);
    }

    //GET POLICY BY ID
    @GetMapping("/{id}")
    public PolicyResponse getPolicy(@PathVariable Long id) {
        return policyService.getPolicy(id);
    }

    //GET ALL POLICIES
    @GetMapping
    public List<PolicyResponse> getAllPolicies() {
        return policyService.getAllPolicies();
    }
}