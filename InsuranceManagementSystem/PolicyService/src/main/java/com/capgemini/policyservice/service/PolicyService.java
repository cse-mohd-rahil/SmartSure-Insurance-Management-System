package com.capgemini.policyservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.capgemini.policyservice.dto.PolicyRequest;
import com.capgemini.policyservice.dto.PolicyResponse;
import com.capgemini.policyservice.entity.Policy;
import com.capgemini.policyservice.exception.ResourceNotFoundException;
import com.capgemini.policyservice.repository.PolicyRepository;


@Service
public class PolicyService {

    private PolicyRepository policyRepository;

    public PolicyService(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    public PolicyResponse createPolicy(PolicyRequest request) {

        Policy policy = new Policy();
        policy.setPolicyName(request.getPolicyName());
        policy.setPolicyType(request.getPolicyType());
        policy.setPremiumAmount(request.getPremiumAmount());
        policy.setStatus("CREATED");

        Policy saved = policyRepository.save(policy);

        return mapToResponse(saved);
    }


    public PolicyResponse purchasePolicy(Long id) {

        Policy policy = policyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found"));

        policy.setStatus("ACTIVE");

        Policy updated = policyRepository.save(policy);

        return mapToResponse(updated);
    }


    public PolicyResponse getPolicy(Long id) {

        Policy policy = policyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Policy not found"));

        return mapToResponse(policy);
    }

    public List<PolicyResponse> getAllPolicies() {

        List<Policy> policies = policyRepository.findAll();
        List<PolicyResponse> responseList = new ArrayList<>();

        for (Policy policy : policies) {
            responseList.add(mapToResponse(policy));
        }

        return responseList;
    }

    private PolicyResponse mapToResponse(Policy policy) {
        return new PolicyResponse(
                policy.getId(),
                policy.getPolicyName(),
                policy.getPolicyType(),
                policy.getPremiumAmount(),
                policy.getStatus()
        );
    }
}