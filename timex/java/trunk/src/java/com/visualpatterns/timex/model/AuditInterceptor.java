package com.visualpatterns.timex.model;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

/**
 * Hibernate interceptor class.
 * 
 * @author Anil Hemrajani
 */
public class AuditInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 1L;
	private static Log log = LogFactory.getLog(AuditInterceptor.class);

	/**
	 * @see org.hibernate.EmptyInterceptor#afterTransactionCompletion(
	 *      org.hibernate.Transaction)
	 */
	public void afterTransactionCompletion(Transaction tx) {
		log.debug("afterTransactionCompletion called!");
	}

	/**
	 * @see org.hibernate.EmptyInterceptor#onSave(java.lang.Object,
	 *      java.io.Serializable, java.lang.Object[], java.lang.String[],
	 *      org.hibernate.type.Type[])
	 */
	public boolean onSave(Object entity, Serializable id, Object[] state,
			String[] propertyNames, Type[] types) {
		return false;
	}

}
