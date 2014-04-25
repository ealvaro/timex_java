package com.timexautoweb.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.timexautoweb.domain.Employee;
import com.timexautoweb.domain.EmployeeHome;
import com.timexautoweb.util.ApplicationSecurityManager;
import com.timexautoweb.util.PasswordUtil;
import com.timexautoweb.util.TimexJmxBean;
/**
 * Controller for the Sign In screen.
 * 
 * @author anil
 */
public class SignInController extends SimpleFormController {
	private EmployeeHome employeeManager;
	private ApplicationSecurityManager applicationSecurityManager;
    private TimexJmxBean timexJmxBean;

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
		// If already login then redirect to success view.
		if (applicationSecurityManager.getEmployee(request) != null)
			return new ModelAndView(getSuccessView());

		return super.showForm(request, response, errors, controlModel);
	}

	/**
	 * 
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest,
	 *      org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true));
	}

	/** Validates user/password against database */
	public void onBindAndValidate(HttpServletRequest request, Object command, BindException errors) throws Exception {
		if (errors.hasErrors())
			return;

		Employee formEmployee = (Employee) command;
		Employee dbEmployee = null;
		if (formEmployee.getId() == null || (dbEmployee = employeeManager.findById(formEmployee.getId())) == null)
			errors.reject("error.login.invalid");
		else {
			if (PasswordUtil.encryptPassword(formEmployee.getPassword()).equals(dbEmployee.getPassword())) {
				timexJmxBean.setSignInCount(timexJmxBean.getSignInCount() + 1);
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

	public TimexJmxBean getTimexJmxBean() {
		return timexJmxBean;
	}

	public void setTimexJmxBean(TimexJmxBean timexJmxBean) {
		this.timexJmxBean = timexJmxBean;
	}

}
