package com.capgemini.adminservice.dto;


public class DashboardResponse {

    private int totalUsers;
    private int totalPolicies;
    private int totalClaims;
    public DashboardResponse() {}
	public DashboardResponse(int totalUsers, int totalPolicies, int totalClaims) {
		super();
		this.totalUsers = totalUsers;
		this.totalPolicies = totalPolicies;
		this.totalClaims = totalClaims;
	}
	public int getTotalUsers() {
		return totalUsers;
	}
	public void setTotalUsers(int totalUsers) {
		this.totalUsers = totalUsers;
	}
	public int getTotalPolicies() {
		return totalPolicies;
	}
	public void setTotalPolicies(int totalPolicies) {
		this.totalPolicies = totalPolicies;
	}
	public int getTotalClaims() {
		return totalClaims;
	}
	public void setTotalClaims(int totalClaims) {
		this.totalClaims = totalClaims;
	}

    
}
