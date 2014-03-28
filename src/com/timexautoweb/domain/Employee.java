package com.timexautoweb.domain;
// Generated Feb 13, 2013 6:38:57 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Employee generated by hbm2java
 */
public class Employee implements java.io.Serializable {

	public static final char HOURLY = 'H';
	public static final char MANAGER = 'M';
	public static final char EXECUTIVE = 'E';
	public static final char ACCOUNTING = 'A';

	private Integer id;
	private int employee_id;
	private Employee employeeMngr;
	private String name;
	private String email;
	private char employeeType;
	private String password;
	private String username;
	private String address;
	private String city;
	private String state;
	private String zipcode;
	private double payrate;
	private double taxrate;
	private Date registrationDate;
	private Set employees = new HashSet(0);
	private Set timesheets = new HashSet(0);

	public Employee() {
	}

	public Employee(Employee employeeMngr, String name, String email, char employeeType, String password, String username,
			String state, double payrate, double taxrate, Date registrationDate) {
		this.employeeMngr = employeeMngr;
		
		this.name = name;
		this.email = email;
		this.employeeType = employeeType;
		this.password = password;
		this.username = username;
		this.state = state;
		this.payrate = payrate;
		this.taxrate = taxrate;
		this.registrationDate = registrationDate;
	}

	public Employee(Employee employeeMngr, String name, String email, char employeeType, String password, String username,
			String address, String city, String state, String zipcode, double payrate, double taxrate,
			Date registrationDate, Set employees, Set timesheets) {
		this.employeeMngr = employeeMngr;
		this.name = name;
		this.email = email;
		this.employeeType = employeeType;
		this.password = password;
		this.username = username;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.payrate = payrate;
		this.taxrate = taxrate;
		this.registrationDate = registrationDate;
		this.employees = employees;
		this.timesheets = timesheets;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}

	public Employee getEmployeeMngr() {
		return this.employeeMngr;
	}

	public void setEmployeeMngr(Employee employeeMngr) {
		this.employeeMngr = employeeMngr;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public char getEmployeeType() {
		return this.employeeType;
	}

	public void setEmployeeType(char employeeType) {
		this.employeeType = employeeType;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return this.zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public double getPayrate() {
		return this.payrate;
	}

	public void setPayrate(double payrate) {
		this.payrate = payrate;
	}

	public double getTaxrate() {
		return this.taxrate;
	}

	public void setTaxrate(double taxrate) {
		this.taxrate = taxrate;
	}

	public Date getRegistrationDate() {
		return this.registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Set getEmployees() {
		return this.employees;
	}

	public void setEmployees(Set employees) {
		this.employees = employees;
	}

	public Set getTimesheets() {
		return this.timesheets;
	}

	public void setTimesheets(Set timesheets) {
		this.timesheets = timesheets;
	}

	public static char getHourly() {
		return HOURLY;
	}

	public static char getManager() {
		return MANAGER;
	}

	public static char getExecutive() {
		return EXECUTIVE;
	}

	public static char getAccounting() {
		return ACCOUNTING;
	}

}
