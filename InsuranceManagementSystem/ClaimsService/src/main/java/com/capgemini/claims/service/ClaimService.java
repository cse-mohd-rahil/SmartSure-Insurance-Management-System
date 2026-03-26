package com.capgemini.claims.service;

import org.springframework.stereotype.Service;

import com.capgemini.claims.entity.Claim;
import com.capgemini.claims.repository.ClaimRepository;
import com.capgemini.claims.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class ClaimService {

    private final ClaimRepository repo;

    public ClaimService(ClaimRepository repo) {
        this.repo = repo;
    }

    public Claim create(Claim claim) {
        claim.setStatus("PENDING");
        return repo.save(claim);
    }

    public List<Claim> getAll() {
        return repo.findAll();
    }

    public List<Claim> getByUser(String email) {
        return repo.findByUserEmail(email);
    }

    public Claim approve(Long id) {
        Claim c = repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Claim not found"));
        c.setStatus("APPROVED");
        return repo.save(c);
    }	

    public Claim reject(Long id) {
        Claim c = repo.findById(id).orElseThrow(()->new ResourceNotFoundException("Claim not found"));
        c.setStatus("REJECTED");
        return repo.save(c);
    }
}