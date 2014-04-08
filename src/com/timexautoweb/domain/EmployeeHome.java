package com.timexautoweb.domain;
// Generated Feb 13, 2013 6:38:59 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.timexautoweb.util.HibernateUtil;

/**
 * Home object for domain model class Employee.
 * @see .Employee
 * @author Hibernate Tools
 */
public class EmployeeHome {

	private static final Log log = LogFactory.getLog(EmployeeHome.class);

	protected SessionFactory getSessionFactory() {
		try {
			return HibernateUtil.getSessionFactory();
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Employee transientInstance) {
		log.debug("persisting Employee instance");
		Session session = getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.persist(transientInstance);
			log.debug("persist successful");
			session.getTransaction().commit();

		} catch (RuntimeException re) {
			log.error("persist failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}

	public void attachDirty(Employee instance) {
		log.debug("attaching dirty Employee instance");
		Session session = getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(instance);
			log.debug("attach successful");
			session.getTransaction().commit();
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}

	public void attachClean(Employee instance) {
		log.debug("attaching clean Employee instance");
		Session session = getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.lock(instance, LockMode.NONE);
			log.debug("attach successful");
			session.getTransaction().commit();
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}

	public void delete(Employee persistentInstance) {
		log.debug("deleting Employee instance");
		Session session = getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.delete(persistentInstance);
			log.debug("delete successful");
			session.getTransaction().commit();

		} catch (RuntimeException re) {
			log.error("delete failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}

	public Employee merge(Employee detachedInstance) {
		log.debug("merging Employee instance");
		Session session = getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Employee result = (Employee) session.merge(detachedInstance);
			log.debug("merge successful");
			session.getTransaction().commit();
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}

	public Employee findById(java.lang.Integer id) {
		log.debug("getting Employee instance with id: " + id);
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Employee instance = (Employee) getSessionFactory().getCurrentSession().get("com.timexautoweb.domain.Employee", id);
			session.getTransaction().commit();
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}

	public List findByExample(Employee instance) {
		log.debug("finding Employee instance by example");
		Session session = getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List results = session.createCriteria(Employee.class).add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			session.getTransaction().commit();
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			session.getTransaction().rollback();
			throw re;
		}
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
			employeeList = session.createQuery("from Employee ORDER BY name").list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return employeeList;
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
			employeeList = session.createQuery("from Employee WHERE managerEmployeeId = ? ORDER BY name")
					.setInteger(0, employeeId).list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return employeeList;
	}

}