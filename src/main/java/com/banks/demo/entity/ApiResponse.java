package com.banks.demo.entity;

public class ApiResponse {
	
	private String name;
	private double bal;
	public ApiResponse(String name, double bal) {
		super();
		this.name = name;
		this.bal = bal;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getBal() {
		return bal;
	}
	public void setBal(double bal) {
		this.bal = bal;
	}
	

}
