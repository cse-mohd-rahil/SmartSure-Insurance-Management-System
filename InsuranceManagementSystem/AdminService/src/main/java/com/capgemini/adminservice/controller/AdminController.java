package com.capgemini.adminservice.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import com.capgemini.adminservice.dto.DashboardResponse;
import com.capgemini.adminservice.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService service;

    public AdminController(AdminService service) {
        this.service = service;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/dashboard")
    public DashboardResponse dashboard(
            @RequestHeader("Authorization") String token
    ) {
        return service.getDashboard(token);
    }
}