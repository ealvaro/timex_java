package com.timexautoweb.controllers;

import java.util.HashMap;

import junit.framework.TestCase;

import org.springframework.mock.web.MockHttpServletRequest;

import com.timexautoweb.domain.DepartmentHome;
import com.timexautoweb.domain.EmployeeHome;
import com.timexautoweb.domain.Timesheet;
import com.timexautoweb.domain.TimesheetHome;
import com.timexautoweb.util.ApplicationSecurityManager;

public class EnterHoursControllerTest extends TestCase {
	private MockHttpServletRequest mockHttpServletRequest = null;
	private EnterHoursController enterHoursController = null;
	private ApplicationSecurityManager applicationSecurityManager = null;
	private EmployeeHome employeeManager = new EmployeeHome();
	private final int EMPLOYEE_ID = 1;

	protected void setUp() throws Exception {
		super.setUp();
		TimesheetHome timesheetManager = new TimesheetHome();
		DepartmentHome departmentManager = new DepartmentHome();
		applicationSecurityManager = new ApplicationSecurityManager();
		enterHoursController = new EnterHoursController();
		enterHoursController.setTimesheetManager(timesheetManager);
		enterHoursController.setDepartmentManager(departmentManager);
		enterHoursController.setApplicationSecurityManager(applicationSecurityManager);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testFormBackingObject1() {
		// Enter Hours to a New Timesheet
		mockHttpServletRequest = new MockHttpServletRequest("GET", "/enterhours.htm");
		// Simulate that Employee with id = 1 is logged in.
		applicationSecurityManager.setEmployee(mockHttpServletRequest, employeeManager.findById(1));
		Object o = enterHoursController.formBackingObject(mockHttpServletRequest);
		assertNotNull(o);
		Timesheet t = (Timesheet) o;
		// Timesheet status after Enter Hours will always be PENDING
		assertEquals(t.getStatusCode(), Timesheet.PENDING.charValue());
		assertEquals(t.getEmployee_id(), 1);
	}

	public void testFormBackingObject2() {
		// Enter Hours into an Existing Timesheet (timesheet with id=5 belongs
		// to employee id=1)
		mockHttpServletRequest = new MockHttpServletRequest("GET", "/enterhours.htm");
		mockHttpServletRequest.addParameter("tid", "5");

		Object o = enterHoursController.formBackingObject(mockHttpServletRequest);
		assertNotNull(o);
		Timesheet t = (Timesheet) o;
		// Timesheet status after Enter Hours will always be PENDING
		assertEquals(t.getStatusCode(), Timesheet.PENDING.charValue());
		assertEquals(t.getEmployee().getId().intValue(), EMPLOYEE_ID);
		assertEquals(t.getId().intValue(), 5);
	}

	public void testOnSubmitHttpServletRequestHttpServletResponseObjectBindException() {
		assertTrue(true);
	}

	public void testInitBinderHttpServletRequestServletRequestDataBinder() {
		assertTrue(true);
	}

	public void testReferenceDataHttpServletRequest() {
		// Existing Timesheet
		mockHttpServletRequest = new MockHttpServletRequest("GET", "/enterhours.htm");
		mockHttpServletRequest.addParameter("tid", "1");

		Object o = null;
		try {
			o = enterHoursController.referenceData(mockHttpServletRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(o);
		HashMap h = (HashMap) o;
		assertTrue(h.containsKey("departments"));
		assertNotNull(h.get("departments"));
	}

}
