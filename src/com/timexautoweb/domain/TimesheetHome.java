package com.timexautoweb.domain;
// Generated Feb 13, 2013 6:38:59 PM by Hibernate Tools 3.4.0.CR1

import java.util.List;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.timexautoweb.util.HibernateUtil;

/**
 * Home object for domain model class Timesheet.
 * @see .Timesheet
 * @author Hibernate Tools
 */
public class TimesheetHome {

	private static final Log log = LogFactory.getLog(TimesheetHome.class);

	protected SessionFactory getSessionFactory() {
		try {
			return HibernateUtil.getSessionFactory();
		} catch (Exception e) {
			log.error("Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(Timesheet transientInstance) {
		log.debug("persisting Timesheet instance");
		try {
			getSessionFactory().getCurrentSession().persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void attachDirty(Timesheet instance) {
		log.debug("attaching dirty Timesheet instance");
		try {
			getSessionFactory().getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Timesheet instance) {
		log.debug("attaching clean Timesheet instance");
		try {
			getSessionFactory().getCurrentSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void delete(Timesheet persistentInstance) {
		log.debug("deleting Timesheet instance");
		try {
			getSessionFactory().getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Timesheet merge(Timesheet detachedInstance) {
		log.debug("merging Timesheet instance");
		try {
			Timesheet result = (Timesheet) getSessionFactory().getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public Timesheet findById(java.lang.Integer id) {
		log.debug("getting Timesheet instance with id: " + id);
		try {
			Timesheet instance = (Timesheet) getSessionFactory().getCurrentSession().get("Timesheet", id);
			if (instance == null) {
				log.debug("get successful, no instance found");
			} else {
				log.debug("get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Timesheet instance) {
		log.debug("finding Timesheet instance by example");
		try {
			List results = getSessionFactory().getCurrentSession().createCriteria("Timesheet").add(Example.create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}
	
	/**
	 * Returns list of all the timesheets for a matching employeeId.
	 */
	@SuppressWarnings("unchecked")
	public List<Timesheet> getTimesheets(int employeeId) {
		List<Timesheet> timesheetList = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			timesheetList = session.createQuery(
					"from Timesheet where employee_id = ? and statusCode <> '" +	Timesheet.PAID + "'")
					.setInteger(0,employeeId).list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return timesheetList;
	}


}
