package com.capgemini.claims.service;

import com.capgemini.claims.entity.Claim;
import com.capgemini.claims.repository.ClaimRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClaimServiceTest {

    @Mock
    private ClaimRepository repo;

    @InjectMocks
    private ClaimService service;

    public ClaimServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void approveClaim_shouldSetStatusApproved() {

        Claim claim = new Claim();
        claim.setId(1L);
        claim.setStatus("PENDING");

        when(repo.findById(1L)).thenReturn(Optional.of(claim));
        when(repo.save(any(Claim.class))).thenReturn(claim);

        Claim result = service.approve(1L);

        assertEquals("APPROVED", result.getStatus());
        verify(repo, times(1)).save(claim);
    }
}