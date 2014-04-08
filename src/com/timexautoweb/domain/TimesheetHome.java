package com.timexautoweb.domain;

// Generated Feb 13, 2013 6:38:59 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
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
 * Home object for domain model class Timesheet.
 * 
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

	public void attachDirty(Timesheet instance) {
		log.debug("attaching dirty Timesheet instance");
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

	public void attachClean(Timesheet instance) {
		log.debug("attaching clean Timesheet instance");
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

	public void delete(Timesheet persistentInstance) {
		log.debug("deleting Timesheet instance");
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

	public Timesheet merge(Timesheet detachedInstance) {
		log.debug("merging Timesheet instance");
		Session session = getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Timesheet result = (Timesheet) session.merge(detachedInstance);
			log.debug("merge successful");
			session.getTransaction().commit();
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			session.getTransaction().rollback();
			throw re;
		}
	}

	public Timesheet findById(java.lang.Integer id) {
		log.debug("getting Timesheet instance with id: " + id);
		Session session = getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Timesheet instance = (Timesheet) session.get(Timesheet.class, id);
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

	public List findByExample(Timesheet instance) {
		log.debug("finding Timesheet instance by example");
		Session session = getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List results = session.createCriteria(Timesheet.class).add(Example.create(instance)).list();
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
	 * Returns list of all the unpaid timesheets for a matching employeeId.
	 */
	@SuppressWarnings("unchecked")
	public List<Timesheet> getTimesheets(int employeeId) {
		List<Timesheet> timesheetList = null;
		Session session = getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			timesheetList = session
					.createQuery("from Timesheet where employee_id = ? and statusCode <> '" + Timesheet.PAID + "'")
					.setInteger(0, employeeId).list();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return timesheetList;
	}

	/**
	 * Returns Timesheet database record with matching employeeId and
	 * periodEndingDate.
	 */
	public Timesheet getTimesheet(int employeeId, Date periodEndingDate) {
		Timesheet timesheet = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			timesheet = (Timesheet) session
					.createQuery("from Timesheet where employee_id = ? and periodEndingDate = ?")
					.setInteger(0, employeeId).setDate(1, periodEndingDate).uniqueResult();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return timesheet;
	}

	/**
	 * Returns Timesheet database record with matching employeeId and
	 * periodEndingDate.
	 */
	public Timesheet findLastOne(int employeeId) {
		Timesheet timesheet = null;
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		try {
			timesheet = (Timesheet) session
					.createQuery("from Timesheet where employee_id = ? order by periodEndingDate desc")
					.setInteger(0, employeeId).setMaxResults(1).uniqueResult();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw e;
		}
		return timesheet;
	}
}
