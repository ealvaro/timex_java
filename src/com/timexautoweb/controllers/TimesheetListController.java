package com.timexautoweb.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.timexautoweb.domain.Employee;
import com.timexautoweb.domain.Timesheet;
import com.timexautoweb.domain.TimesheetHome;
import com.timexautoweb.util.ApplicationSecurityManager;
import com.timexautoweb.util.TimexJmxBean;
import com.timexautoweb.view.XMLView;
/**
 * Controller for the Timesheet List screen.
 * 
 * @author anil
 */
public class TimesheetListController implements Controller {

	public static final String MAP_KEY = "timesheetsJSPVar";
	public static final String XML_KEY = "timesheetsxml";
	public static final String EMP_KEY = "employee";
	private String uploadFileURL = "http://localhost/uploads";
	private ApplicationSecurityManager applicationSecurityManager;
	private XMLView xmlView;

	private TimesheetHome timesheetManager;
	private String successView;

	private TimexJmxBean timexJmxBean;
	private static final Logger logger = Logger.getLogger(TimesheetListController.class);

	/**
	 * Returns a list of Timesheets database objects in ModelAndView.
	 * 
	 * @see com.visualpatterns.timex.model.Timesheet
	 */
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Employee myEmp = (Employee) applicationSecurityManager.getEmployee(request);
		List<Timesheet> timesheets = timesheetManager.getTimesheets(myEmp.getId());
		timexJmxBean.setTimesheetsFetched(timexJmxBean.getTimesheetsFetched() + timesheets.size());
		logger.debug("Showing timesheets for employee id = " + myEmp.getId());
		ModelAndView model = new ModelAndView(getSuccessView(), MAP_KEY, timesheets).addObject(EMP_KEY, myEmp).addObject("fileURL", this.uploadFileURL);
		Source timesheetsXml = null;
		timesheetsXml = xmlView.createXsltSource(model.getModel(), "timesheets", request, response);
		return model.addObject(XML_KEY, timesheetsXml);
	}

	public XMLView getXmlView() {
		return xmlView;
	}

	public void setXmlView(XMLView xmlView) {
		this.xmlView = xmlView;
	}

	public TimesheetHome getTimesheetManager() {
		return timesheetManager;
	}

	public void setTimesheetManager(TimesheetHome timesheetManager) {
		this.timesheetManager = timesheetManager;
	}

	public String getSuccessView() {
		return successView;
	}

	public void setSuccessView(String successView) {
		this.successView = successView;
	}

	public ApplicationSecurityManager getApplicationSecurityManager() {
		return applicationSecurityManager;
	}

	public void setApplicationSecurityManager(ApplicationSecurityManager applicationSecurityManager) {
		this.applicationSecurityManager = applicationSecurityManager;
	}

	public String getUploadFileURL() {
		return uploadFileURL;
	}

	public void setUploadFileURL(String uploadFileURL) {
		this.uploadFileURL = uploadFileURL;
	}
	public TimexJmxBean getTimexJmxBean() {
		return timexJmxBean;
	}

	public void setTimexJmxBean(TimexJmxBean timexJmxBean) {
		this.timexJmxBean = timexJmxBean;
	}

}
