package com.mindex.challenge.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;

@Service
public class CompensationServiceImpl implements CompensationService {
	
	private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);
	
	@Autowired
	CompensationRepository compRepo;
	@Autowired
	EmployeeService empService;

	@Override
	public Compensation create(Compensation compensation) {
		LOG.debug("creating compensation data for comp with employee id [{}]", compensation.getEmployee().getEmployeeId());

		compRepo.insert(compensation);
		return compensation;
	}

	@Override
	public Compensation read(String employeeId) {
		LOG.debug("reading compensation data for id [{}]", employeeId);
		
		Employee e = empService.read(employeeId);
		Compensation comp = compRepo.findByEmployee(e);
		
		return comp;
	}

}
