package com.timexautoweb.test;

import java.util.List;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.timexautoweb.controllers.PrintTimesheetController2;
import com.timexautoweb.controllers.TimesheetListController;
import com.timexautoweb.domain.Employee;
import com.timexautoweb.domain.Timesheet;
import com.timexautoweb.domain.TimesheetHome;
import com.timexautoweb.util.ApplicationSecurityManager;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class PrintTimesheetController2Test extends TestCase {

	private MockHttpServletRequest mockHttpServletRequest = null;
	private PrintTimesheetController2 printTimesheetController2 = null;
	private TimesheetHome timesheetManager = null;
	private ApplicationSecurityManager applicationSecurityManager = null;
	private final int EMPLOYEE_ID = 1;
	Timesheet timesheet1 = null;
	Timesheet timesheet2 = null;

	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	}

	public static Test suite() {
		return new TestSuite(PrintTimesheetController2Test.class);
	}


	public void testHandleRequest() throws Exception {
		mockHttpServletRequest.setMethod("GET");
		mockHttpServletRequest.setQueryString("printhours.htm");

		// This is the real test
		ModelAndView modelAndView = printTimesheetController2.handleRequest(mockHttpServletRequest, null);

		// Make sure model and view from handleRequest is not null
		assertNotNull(modelAndView);

		// Make sure timesheets passed on to the view is not null
		Timesheet timesheet = (Timesheet) modelAndView.getModel().get(printTimesheetController2.MAP_KEY);
		assertNotNull(timesheet);

	}

	protected void setUp() throws Exception {
		Employee employee = new Employee();
		employee.setId(EMPLOYEE_ID);
		mockHttpServletRequest = new MockHttpServletRequest();
		timesheetManager = new TimesheetHome();
		applicationSecurityManager = new ApplicationSecurityManager();
		applicationSecurityManager.setEmployee(mockHttpServletRequest, employee);
		// Inject objects Spring normally would
		printTimesheetController2 = new PrintTimesheetController2();
		printTimesheetController2.setTimesheetManager(timesheetManager);
		printTimesheetController2.setApplicationSecurityManager(applicationSecurityManager);

	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
}
