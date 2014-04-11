package com.timexautoweb.controllers;

import org.springframework.mock.web.MockHttpServletRequest;

import antlr.debug.NewLineEvent;

import com.timexautoweb.domain.Employee;
import com.timexautoweb.domain.EmployeeHome;
import com.timexautoweb.util.ApplicationSecurityManager;

import junit.framework.TestCase;

public class SignInControllerTest extends TestCase {
	private MockHttpServletRequest mockHttpServletRequest = null;
	private SignInController signInController = null;
	private ApplicationSecurityManager applicationSecurityManager = null;	
	private EmployeeHome employeeManager = new EmployeeHome();
	private final int EMPLOYEE_ID = 1;
	
	protected void setUp() throws Exception {
		super.setUp();
		EmployeeHome employeeManager = new EmployeeHome();
		applicationSecurityManager = new ApplicationSecurityManager();
		signInController = new SignInController();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testFormBackingObjectHttpServletRequest() {
		// Someone that is not login tries to login
		mockHttpServletRequest = new MockHttpServletRequest("GET", "/signin.htm");
		applicationSecurityManager.removeEmployee(mockHttpServletRequest);
		Object o = null;
		try {
			o = signInController.formBackingObject(mockHttpServletRequest);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(o);
		Employee employee = (Employee)o;
		
	}

	public void testShowFormHttpServletRequestHttpServletResponseBindExceptionMap() {
		assertTrue(true);
	}

	public void testOnBindAndValidateHttpServletRequestObjectBindException() {
		assertTrue(true);
	}

	public void testOnSubmitHttpServletRequestHttpServletResponseObjectBindException() {
		assertTrue(true);
	}

}
