package com.mindex.challenge.data;

import java.util.Date;

public class Compensation {

	//employee, salary, and effectiveDate
	private Employee employee;
	private Long salary;
	private String effectiveDate;
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee emp) {
		this.employee = emp;
	}
	public Long getSalary() {
		return salary;
	}
	public void setSalary(Long salary) {
		this.salary = salary;
	}
	public String getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
}
