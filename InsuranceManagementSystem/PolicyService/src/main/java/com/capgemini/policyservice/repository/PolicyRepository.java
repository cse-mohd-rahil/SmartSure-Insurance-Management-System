package com.capgemini.policyservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.policyservice.entity.Policy;

public interface PolicyRepository extends JpaRepository<Policy, Long>{

}
