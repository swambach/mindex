package com.mindex.challenge;

import com.mindex.challenge.controller.EmployeeController;
import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataBootstrapTest {

	private static final Logger LOG = LoggerFactory.getLogger(DataBootstrapTest.class);
	
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private CompensationRepository compRepo;

    @Test
    public void test() {
        Employee employee = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
        assertNotNull(employee);
        assertEquals("John", employee.getFirstName());
        assertEquals("Lennon", employee.getLastName());
        assertEquals("Development Manager", employee.getPosition());
        assertEquals("Engineering", employee.getDepartment());
    }
    
    @Test
    public void testComp() {
    	Employee emp = employeeRepository.findByEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
    	
    	Compensation compensation = compRepo.findByEmployee(emp);
    	assertNotNull(compensation);
    	assertTrue(assertAreEqual(emp, compensation.getEmployee()));
    	assertTrue(120 == compensation.getSalary());
    	assertEquals("05/27/2022", compensation.getEffectiveDate());
    }
    
    private boolean assertAreEqual(Employee expected, Employee actual) {
    	try {
	    	assertEquals(expected.getFirstName(), actual.getFirstName());
	    	assertEquals(expected.getLastName(), actual.getLastName());
	    	assertEquals(expected.getDepartment(), actual.getDepartment());
	    	//assertEquals(expected.getDirectReports(), actual.getDirectReports());
	    	assertEquals(expected.getEmployeeId(), actual.getEmployeeId());
	    	assertEquals(expected.getPosition(), actual.getPosition());
    	}
    	catch(AssertionError e) {
    		LOG.debug(e.toString());
    		return false;
    	}
    	return true;
    }
}