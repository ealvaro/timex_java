package com.timexautoweb.controllers;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.timexautoweb.domain.Employee;
import com.timexautoweb.domain.EmployeeHome;
import com.timexautoweb.util.ApplicationSecurityManager;

/**
 * Controller for the Sign In screen.
 * 
 * @author anil
 */
public class SignInController extends SimpleFormController {
	private EmployeeHome employeeManager;
	private ApplicationSecurityManager applicationSecurityManager;

	/**
	 * Always returns a new Employee object
	 * 
	 * @see Employee
	 */
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		return new Employee();
	}

	/** Forwards to success view, if already logged in */
	public ModelAndView showForm(HttpServletRequest request, HttpServletResponse response, BindException errors,
			Map controlModel) throws Exception {
		if (applicationSecurityManager.getEmployee(request) != null)
			return new ModelAndView(getSuccessView());

		return super.showForm(request, response, errors, controlModel);
	}

	/** Validates user/password against database */
	public void onBindAndValidate(HttpServletRequest request, Object command, BindException errors) throws Exception {
		if (errors.hasErrors())
			return;

		Employee formEmployee = (Employee) command;
		Employee dbEmployee = (Employee) command;
		if ((dbEmployee = employeeManager.findById(formEmployee.getId())) == null)
			errors.reject("error.login.invalid");
		else {
			if (encryptPassword(formEmployee.getPassword()).equals(dbEmployee.getPassword())) {
				applicationSecurityManager.setEmployee(request, dbEmployee);
			} else {
				errors.reject("error.login.invalid");
			}
		}
	}

	/** returns ModelAndView(getSuccessView()) */
	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {
		return new ModelAndView(getSuccessView());
	}

	public EmployeeHome getEmployeeManager() {
		return employeeManager;
	}

	public void setEmployeeManager(EmployeeHome employeeManager) {
		this.employeeManager = employeeManager;
	}

	public ApplicationSecurityManager getApplicationSecurityManager() {
		return applicationSecurityManager;
	}

	public void setApplicationSecurityManager(ApplicationSecurityManager applicationSecurityManager) {
		this.applicationSecurityManager = applicationSecurityManager;
	}

	/**
	 * This method will allow us to encrypt passwords in the database
	 * 
	 */
	private static String encryptPassword(String password) {
		String sha1 = "";
		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(password.getBytes("UTF-8"));
			sha1 = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return sha1;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}
}
