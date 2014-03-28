package com.timexautoweb.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.timexautoweb.domain.Department;
import com.timexautoweb.domain.Employee;
import com.timexautoweb.domain.EmployeeHome;
import com.timexautoweb.domain.Timesheet;
import com.timexautoweb.util.HibernateUtil;

//TODO: Add Javadoc comments
public class HibernateTest {

	public static void main(String args[]) throws Exception {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession(); // changed to openSession
		Transaction tx = session.beginTransaction();

		Department department;

		// Demo 1: Get single record
		department = (Department) session.get("com.timexautoweb.domain.Department", 4);
		System.out.println("Name for IT = " + department.getName());

		// Demo 2: Get all records
		List departmentList = session.createQuery("from Department").list();
		for (int i = 0; i < departmentList.size(); i++) {
			department = (Department) departmentList.get(i);
			System.out.println("Row " + (i + 1) + "> " + department.getName() + " (" + department.getId() + ") from " + department.getState());
		}
		// Demo 3: Get all Pending Timesheets
		// from Timesheet where statusCode = ?
		char statusCode = 'P';
		List timesheetList = session.createQuery("from Timesheet where statusCode = ?").setCharacter(0, statusCode).list();

		Timesheet timesheet;
		for (int i = 0; i < timesheetList.size(); i++) {
			timesheet = (Timesheet) timesheetList.get(i);
			System.out.println("Row " + (i + 1) + "> " + timesheet.getEmployee().getName() + " ("
					+ timesheet.getPeriodEndingDate() + ")");
		}

		// Demo 4: Get single record
		timesheet = (Timesheet) session.get(Timesheet.class, 1);
		System.out.println("Timesheet id = " + timesheet.getId() + " is from " + timesheet.getEmployee().getName()
				+ " for end-date " + timesheet.getPeriodEndingDate());

		tx.commit();
		session.close();
		sessionFactory.close();

		// Demo 5: Load an Employee
		// Notice how this test is totally independent from the session for the 4 previous tests
		HibernateUtil.getSessionFactory().openSession();
		EmployeeHome empHome = new EmployeeHome();
		Employee emp = empHome.findById(2);
		System.out.println("Employee id = " + emp.getId() + " with Name =  " + emp.getName() + " and UserName (SSN) = "
				+ emp.getUsername());

	}
}
