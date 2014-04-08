package com.timexautoweb.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.timexautoweb.controllers.ApproveTimesheetsControllerTest;
import com.timexautoweb.controllers.EnterHoursControllerTest;
import com.timexautoweb.controllers.SignInControllerTest;
import com.timexautoweb.controllers.StaffHoursControllerTest;


public class AllTests extends TestCase {
	public static Test suite() {
		TestSuite modelTestSuite = new TestSuite("Managers Tests");
		modelTestSuite.addTestSuite(TimesheetHomeTest.class);
		modelTestSuite.addTestSuite(DepartmentHomeTest.class);
		modelTestSuite.addTestSuite(EmployeeHomeTest.class);

		TestSuite controllerTestSuite = new TestSuite("Controller Tests");
		controllerTestSuite.addTestSuite(TimesheetListControllerTest.class);
		controllerTestSuite.addTestSuite(EnterHoursControllerTest.class);
		controllerTestSuite.addTestSuite(SignInControllerTest.class);
		controllerTestSuite.addTestSuite(ApproveTimesheetsControllerTest.class);
		controllerTestSuite.addTestSuite(PrintTimesheetController2Test.class);
		controllerTestSuite.addTestSuite(StaffHoursControllerTest.class);

		// Full test suite
		TestSuite fullSuite = new TestSuite("All Tests");
		fullSuite.addTest(modelTestSuite);
		fullSuite.addTest(controllerTestSuite);
		return fullSuite;
	}
}