package com.visualpatterns.timex.model;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.visualpatterns.timex.util.HibernateUtil;

/**
 * Manages database operations for Department table.
 * 
 * @author Anil Hemrajani
 */
public class DepartmentManager {

	/**
	 * Returns list of all records in Department table, sorted by name.
	 */
	@SuppressWarnings("unchecked")
	public List<Department> getDepartments() {
		List<Department> departmentList = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			departmentList = session.createQuery(
					"from Department ORDER BY name").list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return departmentList;
	}

}
