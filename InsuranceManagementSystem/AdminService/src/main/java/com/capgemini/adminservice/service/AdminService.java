package com.capgemini.adminservice.service;

import org.springframework.stereotype.Service;

import com.capgemini.adminservice.client.*;
import com.capgemini.adminservice.dto.DashboardResponse;

@Service
public class AdminService {

    private final AuthClient authClient;
    private final PolicyClient policyClient;
    private final ClaimClient claimClient;

    public AdminService(AuthClient a, PolicyClient p, ClaimClient c) {
        this.authClient = a;
        this.policyClient = p;
        this.claimClient = c;
    }

    public DashboardResponse getDashboard(String token) {

        DashboardResponse res = new DashboardResponse();

        try {
            res.setTotalUsers(authClient.getAllUsers(token).size());
        } catch (Exception e) {
            res.setTotalUsers(0);
            System.out.println("AuthService error: " + e.getMessage());
        }

        try {
            res.setTotalPolicies(policyClient.getPolicies(token).size());
        } catch (Exception e) {
            res.setTotalPolicies(0);
            System.out.println("PolicyService error: " + e.getMessage());
        }

        try {
            res.setTotalClaims(claimClient.getClaims(token).size());
        } catch (Exception e) {
            res.setTotalClaims(0);
            System.out.println("ClaimsService error: " + e.getMessage());
        }

        return res;
    }
}