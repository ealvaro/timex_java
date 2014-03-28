package com.timexautoweb.test;

import java.util.List;

import junit.framework.TestCase;

import com.timexautoweb.domain.DepartmentHome;
import com.timexautoweb.domain.Employee;
import com.timexautoweb.domain.EmployeeHome;
import com.timexautoweb.util.DateUtil;

public class EmployeeHomeTest extends TestCase {
	EmployeeHome eh;
	DepartmentHome dh;
	Employee employee;

	protected void setUp() throws Exception {
		super.setUp();
		eh = new EmployeeHome();
		this.employee = new Employee(eh.findById(1), "Test Tester", "test@tester.com", Employee.HOURLY, "12345", "tester", "FL", 15.50, 15.50, DateUtil.getCurrentPeriodStartingDate());
		eh.persist(this.employee);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		eh.delete(this.employee);
	}

	/**
	 * Tests the Create
	 */
	public void testPersist() {
		assertNotNull("Employee with name = Test Tester exists", eh.findByExample(this.employee));
		assertTrue(eh.findByExample(this.employee).size() > 0);
	}

	/**
	 * Tests the Update
	 */
	public void testMerge() {
		this.employee.setCity("Davie");
		this.employee.setState("GA");
		Employee employee2 = eh.merge(this.employee);
		assertEquals("New Employee lives in city ","Davie", this.employee.getCity());
		assertEquals("New Employee lives in new state ", "GA", this.employee.getState());
	}

	/**
	 * Tests the Read
	 */
	public void testFindById() {
		Employee employee2 = eh.findById(this.employee.getId());
		assertNotNull("New Employee with new id = "+employee2.getId()+" exists");
		assertEquals(employee2.getId(), this.employee.getId());
	}

	/**
	 * Tests the Delete
	 */
	public void testDelete() {
		eh.delete(this.employee);
		assertEquals(eh.findByExample(this.employee).size(),0);
		assertNull(eh.findById(this.employee.getId()));
		this.employee = new Employee(eh.findById(1), "Test Tester", "test@tester.com", Employee.HOURLY, "12345", "tester", "FL", 15.50, 15.50, DateUtil.getCurrentPeriodStartingDate());
		eh.persist(this.employee);
	}

	/**
	 * Tests getting all unpaid employees for a particular employee id.
	 */
	public void testGetAllEmployees() {
		List<Employee> employees = eh.getAllEmployees();
		assertNotNull("All Employees exists", employees);
		assertTrue("# of Employees are > 0", employees.size() > 0);
	}

}
