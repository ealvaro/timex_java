package com.timexautoweb.test;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import com.timexautoweb.domain.Department;
import com.timexautoweb.domain.DepartmentHome;

public class DepartmentHomeTest extends TestCase {
	DepartmentHome dh = null;
	Department dept = null;

	protected void setUp() throws Exception {
		super.setUp();
		dh = new DepartmentHome();
		this.dept = new Department("New Department", "PA", null);
		dh.persist(this.dept);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		dh.delete(this.dept);
	}

	/**
	 * Tests the Create
	 */
	public void testPersist() {
		assertNotNull("New Department exists", dh.findByExample(this.dept));
		assertTrue(dh.findByExample(this.dept).size() > 0);
	}

	/**
	 * Tests the Read
	 */
	public void testFindById() {
		Department dept2 = dh.findById(this.dept.getId());
		assertNotNull("New Department exists", dept2);
		assertEquals(dept2.getId(), this.dept.getId());
	}

	/**
	 * Tests the Update
	 */
	public void testMerge() {
		this.dept.setName("Old Department");
		this.dept.setState("FL");
		Department dept2 = dh.merge(this.dept);
		assertEquals("New Department is now called Old Department", dept2.getName(), this.dept.getName());
		assertEquals("New Department is now from FL State", dept2.getState(), this.dept.getState());
	}

	/**
	 * Tests the Delete
	 */
	public void testDelete() {
		dh.delete(this.dept);
		assertEquals(dh.findByExample(this.dept).size(), 0);
		assertNull(dh.findById(this.dept.getId()));
		this.dept = new Department("New Department", "PA", null);
		dh.persist(this.dept);
	}

	public static void main(String args[]) {
		junit.textui.TestRunner.run(suite());
	}

	public static Test suite() {
		return new TestSuite(DepartmentHomeTest.class);
	}

}
