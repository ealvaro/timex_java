package com.visualpatterns.timex.model;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.visualpatterns.timex.util.HibernateUtil;

/**
 * Manages database operations for Employee table.
 * 
 * @author Anil Hemrajani
 * @author Alvaro E. Escobar
 */
public class EmployeeManager {

	/**
	 * Gets employee record with matching employeeId.
	 */
	public Employee getEmployee(int employeeId) {
		Employee employee = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			employee = (Employee) session.createQuery(
					"from Employee where employeeId = ?").setInteger(0,
					employeeId).uniqueResult();
			//session.delete(employee);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return employee;
	}

	/**
	 * Returns list of all Employee records reporting to Employee with given
	 * employeeId.
	 */
	@SuppressWarnings("unchecked")
	public List<Employee> getReportingEmployees(int employeeId) {
		List<Employee> employeeList = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			employeeList = session.createQuery(
					"from Employee WHERE managerEmployeeId = ? ORDER BY name")
					.setInteger(0, employeeId).list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return employeeList;
	}

	/**
	 * Returns list of all hourly employees.
	 */
	@SuppressWarnings("unchecked")
	public List<Employee> getHourlyEmployees() {
		List<Employee> employeeList = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			employeeList = session.createQuery(
					"from Employee WHERE employeeCode='" + Employee.HOURLY
							+ "'" + "ORDER BY name").list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return employeeList;
	}

	/**
	 * Returns list of all employees.
	 */
	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployees() {
		List<Employee> employeeList = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			employeeList = session.createQuery("from Employee ORDER BY name")
					.list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return employeeList;
	}

	/**
	 * Returns list of all managers and executives.
	 */
	@SuppressWarnings("unchecked")
	public List<Employee> getManagers() {
		List<Employee> managersList = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			managersList = session.createQuery(
					"from Employee WHERE employeeCode='" + Employee.MANAGER
							+ "' OR employeeCode='" + Employee.EXECUTIVE
							+ "' ORDER BY name").list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return managersList;
	}

}
