package com.timexautoweb.test;

import java.util.List;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.timexautoweb.controllers.EnterHoursControllerTest;
import com.timexautoweb.domain.DepartmentHome;
import com.timexautoweb.domain.EmployeeHome;
import com.timexautoweb.domain.Timesheet;
import com.timexautoweb.domain.TimesheetHome;
import com.timexautoweb.util.DateUtil;

public class TimesheetHomeTest extends TestCase {
	TimesheetHome th;
	EmployeeHome eh;
	DepartmentHome dh;
	Timesheet timeSheet;

	protected void setUp() throws Exception {
		super.setUp();
		th = new TimesheetHome();
		eh = new EmployeeHome();
		dh = new DepartmentHome();
		this.timeSheet = new Timesheet(eh.findById(1), dh.findById(1), Timesheet.PENDING.charValue(),
				DateUtil.getCurrentPeriodEndingDate());
		th.persist(this.timeSheet);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		th.delete(this.timeSheet);
	}

	/**
	 * Tests the Create
	 */
	public void testPersist() {
		assertNotNull("New Timesheet for employee id = 1 and Department id = 1 exists", th.findByExample(this.timeSheet));
		assertTrue(th.findByExample(this.timeSheet).size() > 0);
	}

	/**
	 * Tests the Read
	 */
	public void testFindById() {
		Timesheet timeSheet2 = th.findById(this.timeSheet.getId());
		assertNotNull("New Timesheet with new id exists", timeSheet2);
		assertEquals(timeSheet2.getId(), this.timeSheet.getId());
	}

	/**
	 * Tests the Update
	 */
	public void testMerge() {
		this.timeSheet.setMinutesMon(new Integer(480));
		this.timeSheet.setDepartment(dh.findById(2));
		Timesheet timeSheet2 = th.merge(this.timeSheet);
		assertEquals("New Timesheet has minutes on Monday ", timeSheet2.getMinutesMon(), this.timeSheet.getMinutesMon());
		assertEquals("New Timesheet's Deparment id = 2 ", timeSheet2.getDepartment().getId(), this.timeSheet.getDepartment().getId());
	}

	/**
	 * Tests the Delete
	 */
	public void testDelete() {
		th.delete(this.timeSheet);
		assertEquals(th.findByExample(this.timeSheet).size(),0);
		assertNull(th.findById(this.timeSheet.getId()));
		this.timeSheet = new Timesheet(eh.findById(1), dh.findById(1), Timesheet.PENDING.charValue(),
				DateUtil.getCurrentPeriodEndingDate());
		th.persist(this.timeSheet);
	}

	/**
	 * Tests getting all unpaid timesheets for a particular employee id.
	 */
	public void testGetTimesheets() {
		List<Timesheet> timesheets = th.getTimesheets(1);
		assertNotNull("Timesheet for employee id = 1 exists", timesheets);
		assertEquals(timesheets.get(0).getEmployee().getId(), new Integer(1));
		assertFalse("Timesheet has not been paid", timesheets.get(0).getStatusCode() == 'C');
	}

	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	}

	public static Test suite() {
		return new TestSuite(TimesheetHomeTest.class);
	}

}
