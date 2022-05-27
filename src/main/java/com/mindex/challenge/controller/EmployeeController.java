package com.mindex.challenge.controller;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;    
    @Autowired
    private ReportingStructureService reportingStructure;    
    @Autowired
    private CompensationService compService;

    @PostMapping("/employee")
    public Employee create(@RequestBody Employee employee) {
        LOG.debug("Received employee create request for [{}]", employee);

        return employeeService.create(employee);
    }

    @GetMapping("/employee/{id}")
    public Employee read(@PathVariable String id) {
        LOG.debug("Received employee create request for id [{}]", id);

        return employeeService.read(id);
    }

    @PutMapping("/employee/{id}")
    public Employee update(@PathVariable String id, @RequestBody Employee employee) {
        LOG.debug("Received employee create request for id [{}] and employee [{}]", id, employee);

        employee.setEmployeeId(id);
        return employeeService.update(employee);
    }
    
    @GetMapping("employee/reportingStructure/{id}")
    public ReportingStructure getReportingStructure(@PathVariable String id) {    	
    	LOG.debug("Received employee reporting structure request for id [{}]", id);
    	
    	return reportingStructure.read(id);
    }
    
    @GetMapping("employee/compensation/{id}")
    public Compensation getCompensationData(@PathVariable String id) {
    	LOG.debug("Received compensation reporting structure request for id [{}]", id);

    	return compService.read(id);
    }
    
    @PostMapping("employee/compensation/")
    public Compensation create(@RequestBody Compensation compensation) {
    	LOG.debug("Received compensation create request for [{}]", compensation);
    	
    	return compService.create(compensation);
    }
}
