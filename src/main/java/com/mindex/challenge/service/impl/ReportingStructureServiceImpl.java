package com.mindex.challenge.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;

public class ReportingStructureServiceImpl implements ReportingStructureService {
	
	private ReportingStructure structure;
	
	@Autowired
	EmployeeService empService;

	@Override
	public ReportingStructure read(String employeeId) {
		
		//get current employee and iterate through children
		Employee emp = empService.read(employeeId);
		
		List<Employee> empList = emp.getDirectReports();
		
		structure.setEmployee(emp);
		structure.setNumberOfReports(getAllEmployees(empList,0));
		
		return structure;
	}
	
	private int getAllEmployees(List<Employee> empList, int currEmployees) {
		
		if(empList.isEmpty())
			return currEmployees;

		currEmployees += empList.size();
		
		for(Employee e : empList) {
			empList.remove(e);
			
			List<Employee> currentEmplReports = e.getDirectReports();
			getAllEmployees(currentEmplReports, currEmployees);
		}
		
		return currEmployees;
	}

}
