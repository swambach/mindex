package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

public interface CompensationService {	
	Compensation create(Compensation employeeId);
	Compensation read(String employeeId);
}
