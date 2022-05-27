package com.mindex.challenge.data;

import java.util.Date;

public class Compensation {

	//employee, salary, and effectiveDate
	private Employee employee;
	private Long salary;
	private Date effectiveDate;
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
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
}
