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
import org.hibernate.util.JDBCExceptionReporter;

import com.timexautoweb.util.HibernateUtil;

/**
 * Home object for domain model class Department.
 * 
 * @see .Department
 * @author Hibernate Tools
 */
public class DepartmentHome {

	private static final Log log = LogFactory.getLog(DepartmentHome.class);

	protected SessionFactory getSessionFactory() {
		try {
			return HibernateUtil.getSessionFactory();
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Department transientInstance) {
		log.debug("persisting Department instance");
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

	public void attachDirty(Department instance) {
		log.debug("attaching dirty Department instance");
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

	public void attachClean(Department instance) {
		log.debug("attaching clean Department instance");
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

	public void delete(Department persistentInstance) {
		log.debug("deleting Department instance");
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

	public Department merge(Department detachedInstance) {
		log.debug("merging Department instance");
		Session session = getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Department result = (Department) session.merge(detachedInstance);
			log.debug("merge successful");
			session.getTransaction().commit();
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}

	public Department findById(java.lang.Integer id) {
		log.debug("getting Department instance with id: " + id);
		Session session = getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Department instance = (Department) session.get(Department.class, id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			session.getTransaction().commit();
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}

	public List findByExample(Department instance) {
		log.debug("finding Department instance by example");
		Session session = getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List results = session.createCriteria(Department.class).add(Example.create(instance)).list();
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
	 * Returns list of all records in Department table, sorted by name.
	 */
	public List<Department> getDepartments() {
		List<Department> departmentList = null;
		Session session = getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			departmentList = session.createQuery("from Department ORDER BY name").list();
			log.debug("Get all Departments successful, result size: " + departmentList.size());
			session.getTransaction().commit();
		} catch (HibernateException e) {
			try {
				session.getTransaction().rollback();
			} catch (HibernateException se) {
				return null;
			}
			throw e;
		}

		return departmentList;
	}
}
