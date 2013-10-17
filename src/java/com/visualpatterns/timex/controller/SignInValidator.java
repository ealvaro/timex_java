package com.visualpatterns.timex.controller;

import org.springframework.validation.Errors;

import com.visualpatterns.timex.model.Employee;

/**
 * Validator for SignInController
 * 
 * @author Anil Hemrajani
 * @see com.visualpatterns.timex.controller.SignInController
 */
public class SignInValidator implements
		org.springframework.validation.Validator {

	/**
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean supports(Class clazz) {
		return clazz.equals(Employee.class);
	}

	/**
	 * Validates an Employee command object. Ensures that employeeId is greater
	 * than zero and that a password length is not bigger than 10 (field size in
	 * database).
	 * 
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 *      org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object command, Errors errors) {
		if (command == null)
			return;
		Employee employee = (Employee) command;
		int employeeId = employee.getEmployeeId();
		String password = employee.getPassword();

		if (employeeId < 1) {
			errors.reject("error.login.invalid");
		} else if (password == null || password.trim().length() > 10) {
			errors.reject("error.login.invalid");
		}
	}

}
