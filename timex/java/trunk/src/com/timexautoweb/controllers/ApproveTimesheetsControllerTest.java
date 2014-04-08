package com.timexautoweb.controllers;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.DefaultMessageCodesResolver;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.servlet.ModelAndView;

import antlr.debug.NewLineEvent;

import com.timexautoweb.domain.Employee;
import com.timexautoweb.domain.EmployeeHome;
import com.timexautoweb.domain.Timesheet;
import com.timexautoweb.domain.TimesheetHome;
import com.timexautoweb.domain.TimesheetList;
import com.timexautoweb.test.TimesheetHomeTest;
import com.timexautoweb.util.ApplicationSecurityManager;
import com.timexautoweb.util.DateUtil;

public class ApproveTimesheetsControllerTest extends TestCase {
	private MockHttpServletRequest request = new MockHttpServletRequest();
	private MockHttpServletResponse response = new MockHttpServletResponse();
	private ApproveTimesheetsController approveTimesheetsController = null;
	private ApplicationSecurityManager applicationSecurityManager = null;
	private EmployeeHome employeeManager = new EmployeeHome();
	private TimesheetHome timesheetManager = new TimesheetHome();
	Employee employee;
	Employee manager;
	Timesheet timesheet;
	
	private final int EMPLOYEE_ID = 1;

	protected void setUp() throws Exception {
		super.setUp();
		//Find first employee
		this.manager = employeeManager.findById(1);
		//Create a new employee and make him report to first employee 
		this.employee = new Employee(this.manager, "Test Tester", "test@tester.com", Employee.HOURLY, "8cb2237d0679ca88db6464eac60da96345513964", "tester", "FL", 15.50, 15.50, DateUtil.getCurrentPeriodStartingDate());
		this.employee.setManagerEmployeeId(this.manager.getId());
		employeeManager.persist(this.employee);
		//Create a new timesheet for the new employee and pretend was submitted
		this.timesheet = new Timesheet(this.employee, null, Timesheet.SUBMITTED.charValue(),
				DateUtil.getCurrentPeriodEndingDate());
		this.timesheet.setEmployee_id(this.employee.getId());
		timesheetManager.persist(this.timesheet);

		applicationSecurityManager = new ApplicationSecurityManager();
		approveTimesheetsController = new ApproveTimesheetsController();
		approveTimesheetsController.setTimesheetManager(timesheetManager);
		approveTimesheetsController.setEmployeeManager(employeeManager);
		approveTimesheetsController.setApplicationSecurityManager(applicationSecurityManager);
		approveTimesheetsController.setCommandClass(TimesheetList.class);
		approveTimesheetsController.setSuccessView("redirect:staffhours.htm");
		approveTimesheetsController.setValidator(new ApproveTimesheetsValidator());
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("hr@hr.com");
		approveTimesheetsController.setApprovalMessage(message);
		approveTimesheetsController.setDisapprovalMessage(new SimpleMailMessage());
		approveTimesheetsController.setMailSender(new JavaMailSenderImpl());
		MessageCodesResolver messageCodesResolver = new DefaultMessageCodesResolver();
		approveTimesheetsController.setMessageCodesResolver(messageCodesResolver);
		
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		timesheetManager.delete(this.timesheet);
		employeeManager.delete(this.employee);	
	}

	public void testFormBackingObjectHttpServletRequest() {
		// Go to Approve Timesheets
		request = new MockHttpServletRequest("GET", "/approvetimesheets.htm");
		// Simulate that Employee with id = 1 is logged in as a manager
		applicationSecurityManager.setEmployee(request, this.manager);
		Object o = approveTimesheetsController.formBackingObject(request);
		assertNotNull(o);
		TimesheetList tl = (TimesheetList) o;
		// There is only one timesheet
		assertTrue(tl.getTimesheets().size() == 1);
	}

	public void testOnSubmitHttpServletRequestHttpServletResponseObjectBindException() {
		// Go to Approve Timesheets simulating a form post of an approval of the only timesheet
		request = new MockHttpServletRequest("POST", "/approvetimesheets.htm");
		this.timesheet.setStatusCode(Timesheet.APPROVED);
		List<Timesheet> timesheets = new ArrayList<Timesheet>();
		timesheets.add(this.timesheet);
		TimesheetList command = new TimesheetList(timesheets);
		request.setAttribute("command", command);
		// Simulate that Employee with id = 1 is logged in as a manager
		applicationSecurityManager.setEmployee(request, this.manager);
		ModelAndView mav = null;
		try {
		mav = approveTimesheetsController.onSubmit(request, response, command, null);
		}catch (IllegalStateException ise) {
			assertTrue(true);
		}
		assertEquals(200, response.getStatus());
		assertNotNull(mav);
		assertNotNull(mav.getModel());		
		//assertEquals("redirect:staffhours.htm", response.getRedirectedUrl());
	}

	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	}

	public static Test suite() {
		return new TestSuite(ApproveTimesheetsControllerTest.class);
	}

}
