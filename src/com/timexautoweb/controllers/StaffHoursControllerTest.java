package com.timexautoweb.controllers;

import java.util.HashMap;

import junit.framework.TestCase;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.timexautoweb.domain.Employee;
import com.timexautoweb.domain.EmployeeHome;
import com.timexautoweb.domain.Timesheet;
import com.timexautoweb.domain.TimesheetHome;
import com.timexautoweb.domain.TimesheetList;
import com.timexautoweb.util.ApplicationSecurityManager;
import com.timexautoweb.util.DateUtil;

public class StaffHoursControllerTest extends TestCase {
	private MockHttpServletRequest request = new MockHttpServletRequest();
	private MockHttpServletResponse response = new MockHttpServletResponse();
	private StaffHoursController staffHoursController = null;
	private ApplicationSecurityManager applicationSecurityManager = null;
	private EmployeeHome employeeManager = new EmployeeHome();
	private TimesheetHome timesheetManager = new TimesheetHome();
	Employee employee;
	Employee manager;
	Timesheet timesheet;

	protected void setUp() throws Exception {
		super.setUp();
		// Find first employee
		this.manager = employeeManager.findById(1);
		// Create a new employee and make him report to first employee
		this.employee = new Employee(this.manager, "Test Tester", "test@tester.com", Employee.HOURLY,
				"8cb2237d0679ca88db6464eac60da96345513964", "tester", "FL", 15.50, 15.50,
				DateUtil.getCurrentPeriodStartingDate());
		this.employee.setManagerEmployeeId(this.manager.getId());
		employeeManager.persist(this.employee);
		// Create a new timesheet for the new employee and pretend was submitted
		this.timesheet = new Timesheet(this.employee, null, Timesheet.SUBMITTED.charValue(),
				DateUtil.getCurrentPeriodEndingDate());
		this.timesheet.setEmployee_id(this.employee.getId());
		timesheetManager.persist(this.timesheet);

		applicationSecurityManager = new ApplicationSecurityManager();
		staffHoursController = new StaffHoursController();
		staffHoursController.setTimesheetManager(timesheetManager);
		staffHoursController.setEmployeeManager(employeeManager);
		staffHoursController.setApplicationSecurityManager(applicationSecurityManager);
		staffHoursController.setCommandClass(TimesheetList.class);
		staffHoursController.setFormView("staffhours");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		timesheetManager.delete(this.timesheet);
		employeeManager.delete(this.employee);
	}

	public void testFormBackingObjectHttpServletRequest() {
		// Go to Approve Timesheets
		request = new MockHttpServletRequest("GET", "/staffhours.htm");
		// Simulate that Employee with id = 1 is logged in as a manager
		applicationSecurityManager.setEmployee(request, this.manager);
		Object o = null;
		try {
			o = staffHoursController.formBackingObject(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(o);
		TimesheetList tl = (TimesheetList) o;
		// There is only one timesheet
		assertTrue(tl.getTimesheets().size() == 1);
	}

	public void testReferenceDataHttpServletRequest() {
		// Go to Approve Timesheets
		request = new MockHttpServletRequest("GET", "/staffhours.htm");
		// Simulate that Employee with id = 1 is logged in as a manager
		applicationSecurityManager.setEmployee(request, this.manager);
		Object o = null;
		try {
			o = staffHoursController.referenceData(request);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(o);
		HashMap hash = (HashMap) o;
		Employee employee = (Employee) hash.get("employee");
		// It's the login employee
		assertTrue(employee.getId() == this.manager.getId());

	}

}
