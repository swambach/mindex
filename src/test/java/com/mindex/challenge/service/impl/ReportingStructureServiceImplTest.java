package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {
	
    private String reportStructureUrl;
    
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
    	reportStructureUrl = "http://localhost:" + port + "/employee/reportingStructure/{id}";
    }

   
    @Test
    public void testReportStructureRead() {
    	
    	Employee emp = new Employee();
    	emp.setEmployeeId("16a596ae-edd3-4847-99fe-c4518e82c86f");
    	emp.setFirstName("John");
    	emp.setLastName("Lennon");

        // Read checks on known id with four reports
    	ReportingStructure reportStructure = restTemplate.getForEntity(reportStructureUrl, ReportingStructure.class, "16a596ae-edd3-4847-99fe-c4518e82c86f").getBody();
        assertEquals(emp.getEmployeeId(), reportStructure.getEmployee().getEmployeeId());
        assertEquals(emp.getFirstName(), reportStructure.getEmployee().getFirstName());
        assertEquals(emp.getLastName(), reportStructure.getEmployee().getLastName());
        assertEquals(Long.parseLong("4"), reportStructure.getNumberOfReports());

    }
}
