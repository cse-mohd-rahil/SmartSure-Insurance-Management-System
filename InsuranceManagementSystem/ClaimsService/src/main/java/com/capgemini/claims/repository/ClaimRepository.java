package com.capgemini.claims.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.claims.entity.Claim;

public interface ClaimRepository extends JpaRepository<Claim, Long>{
	List<Claim> findByUserEmail(String email);
}
