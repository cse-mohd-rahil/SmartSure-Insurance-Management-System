package com.capgemini.claims.controller;


import io.jsonwebtoken.Claims;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.capgemini.claims.entity.Claim;
import com.capgemini.claims.security.JwtUtil;
import com.capgemini.claims.service.ClaimService;

import java.nio.file.*;
import java.util.List;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    private final ClaimService service;
    private final JwtUtil jwtUtil;

    public ClaimController(ClaimService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping(consumes = "multipart/form-data")
    @PreAuthorize("hasRole('CUSTOMER')")
    public Claim create(
            @RequestHeader("Authorization") String header,
            @RequestParam String policyName,
            @RequestParam String description,
            @RequestParam MultipartFile file) throws Exception {

        String token = header.substring(7);
        Claims claims = jwtUtil.getClaims(token);
        String email = claims.getSubject();

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        String path = "uploads/" + fileName;

        Files.copy(file.getInputStream(), Paths.get(path), StandardCopyOption.REPLACE_EXISTING);

        Claim claim = new Claim();
        claim.setPolicyName(policyName);
        claim.setDescription(description);
        claim.setUserEmail(email);
        claim.setDocumentPath(path);

        return service.create(claim);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Claim> getAll() {
        return service.getAll();
    }

    @GetMapping("/my")
    @PreAuthorize("hasRole('CUSTOMER')")
    public List<Claim> myClaims(@RequestHeader("Authorization") String header) {
        String token = header.substring(7);
        String email = jwtUtil.getClaims(token).getSubject();
        return service.getByUser(email);
    }

    @PutMapping("/{id}/approve")
    @PreAuthorize("hasRole('ADMIN')")
    public Claim approve(@PathVariable Long id) {
        return service.approve(id);
    }

    @PutMapping("/{id}/reject")
    @PreAuthorize("hasRole('ADMIN')")
    public Claim reject(@PathVariable Long id) {
        return service.reject(id);
    }
}