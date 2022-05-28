package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.controller.EmployeeController;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {

    private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImplTest.class);
    private String compCreateUrl;
    private String compReadUrl;
    private String employeeUrl;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        compCreateUrl = "http://localhost:" + port + "/employee/compensation";
        compReadUrl = "http://localhost:" + port + "/employee/compensation/{id}";
        employeeUrl = "http://localhost:" + port + "/employee";
    }
    
	@Test
	public void compTest() {
		//test create
        Employee testEmployee = new Employee();
        testEmployee.setFirstName("Don");
        testEmployee.setLastName("Joe");
        testEmployee.setDepartment("Engineering");
        testEmployee.setPosition("Tester");
        
        //create the new test employee
        Employee createdEmployee = restTemplate.postForEntity(employeeUrl, testEmployee, Employee.class).getBody();
		
		Compensation compTest = new Compensation();
		compTest.setEffectiveDate("12/21/2022");
		compTest.setSalary(1234L);
		compTest.setEmployee(createdEmployee);
		
		Compensation createdComp = restTemplate.postForEntity(compCreateUrl, compTest, Compensation.class).getBody();
		LOG.debug(createdComp.getSalary().toString());
        assertNotNull(createdComp);
        assertCompEquivalence(compTest, createdComp);
		
		//test read
        Compensation readComp = restTemplate.getForEntity(compReadUrl, Compensation.class, createdComp.getEmployee().getEmployeeId()).getBody();
        assertNotNull(readComp);
        LOG.debug(readComp.getEmployee().getFirstName());
        assertEquals(compTest.getEffectiveDate(), readComp.getEffectiveDate());
	}
	
	private static void assertCompEquivalence(Compensation expected, Compensation actual) {
		assertEquals(expected.getEffectiveDate(), actual.getEffectiveDate());
		assertEquals(expected.getSalary(), actual.getSalary());
		assertEquals(expected.getEmployee().getLastName(), actual.getEmployee().getLastName()); //making an assumption that if we got a name back that the rest is ok too
	}

}
