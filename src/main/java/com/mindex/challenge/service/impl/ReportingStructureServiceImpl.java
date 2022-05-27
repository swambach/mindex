package com.mindex.challenge.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;

@Service
public class ReportingStructureServiceImpl implements ReportingStructureService {
	
	private ReportingStructure structure = new ReportingStructure();
	
	@Autowired
	EmployeeService empService;

	@Override
	public ReportingStructure read(String employeeId) {
		
		//get current employee and iterate through children
		Employee emp = empService.read(employeeId);
		
		List<Employee> empList = emp.getDirectReports();
		
		structure.setEmployee(emp);
		structure.setNumberOfReports(getAllEmployees(empList,empList.size()));
		
		return structure;
	}
	
	private int getAllEmployees(List<Employee> empList, int currEmployees) {
		
		if(empList.isEmpty())
			return currEmployees;
	
		for(Employee e : empList) {
			e = empService.read(e.getEmployeeId());
			List<Employee> currentEmplReports = e.getDirectReports();
			if(currentEmplReports != null) {
				currEmployees += e.getDirectReports().size();
				getAllEmployees(currentEmplReports, currEmployees);
			}			
		}
		
		return currEmployees;
	}

}
