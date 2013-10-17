package com.visualpatterns.timex.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.visualpatterns.timex.model.DepartmentManager;
import com.visualpatterns.timex.model.Employee;
import com.visualpatterns.timex.model.EmployeeManager;
import com.visualpatterns.timex.model.Timesheet;
import com.visualpatterns.timex.model.TimesheetManager;
import com.visualpatterns.timex.util.ApplicationSecurityManager;
import com.visualpatterns.timex.util.DateUtil;

/**
 * Controller class for the "Enter Hours" screen
 * 
 * @author Anil Hemrajani
 * @author Alvaro E. Escobar
 */
public class EnterHoursController extends SimpleFormController {

	private TimesheetManager timesheetManager;
	private DepartmentManager departmentManager;
	private ApplicationSecurityManager applicationSecurityManager;
	private SimpleMailMessage message;
	private EmployeeManager employeeManager;
	private MailSender mailSender;
	public static final String TID = "tid";
	public static final int PERIOD_ENTRIES = 7;

	/**
	 * Returns a new instance of Timesheet object if "tid" HTTP parameter is not
	 * specified; otherwise returns instance of object in database matching the
	 * value of the "tid" parameter.
	 * 
	 * @see Timesheet
	 */
	protected Object formBackingObject(HttpServletRequest request) {
		Timesheet timesheet;
		if (request.getParameter(TID) != null
				&& request.getParameter(TID).trim().length() > 0) {
			timesheet = timesheetManager.getTimesheet(Integer.parseInt(request
					.getParameter(TID)), false);
		} else {
			timesheet = new Timesheet();
			timesheet.setEmployeeId(((Employee) applicationSecurityManager
					.getEmployee(request)).getEmployeeId());
			timesheet
					.setPeriodEndingDate(DateUtil.getCurrentPeriodEndingDate());
		}
		timesheet.setStatusCode(Timesheet.PENDING);
		return timesheet;
	}

	/**
	 * Registers the MinutesPropertyEditor.
	 * 
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(
	 *      javax.servlet.http.HttpServletRequest,
	 *      org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(int.class, new MinutesPropertyEditor());
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
	}

	/**
	 * Returns Hashmap containing a list of all Department database records.
	 * 
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#referenceData(
	 *      javax.servlet.http.HttpServletRequest)
	 */
	@SuppressWarnings("unchecked")
	protected Map referenceData(HttpServletRequest request) throws Exception {
		HashMap model = new HashMap();
		model.put("departments", departmentManager.getDepartments());
		model.put("periods", DateUtil.getPeriodRange(PERIOD_ENTRIES));
		return model;
	}

	/**
	 * Saves or submits timesheet.
	 */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) {
		Timesheet timesheet = (Timesheet) command;
		try {
			if (request.getParameter("sendEmail").equalsIgnoreCase("no")) {
				timesheet.setStatusCode(Timesheet.PENDING);
			} else {
				timesheet.setStatusCode(Timesheet.SUBMITTED);
				sendSubmittalEmail(request);
			}
			timesheetManager.saveTimesheet(timesheet);

			request.getSession().setAttribute(
					"message",
					getMessageSourceAccessor().getMessage(
							"message.enterhours.savesuccess"));
		} catch (Exception e) {
			if (e instanceof org.hibernate.exception.ConstraintViolationException) {
				request.getSession().setAttribute(
						"error",
						getMessageSourceAccessor().getMessage(
								"error.enterhours.preexistingerror"));
				request.getSession().removeAttribute("message");
			}
		}
		return new ModelAndView(getSuccessView());
	}

	/**
	 * Send submittal email to Manager.
	 */
	private void sendSubmittalEmail(HttpServletRequest request) {
		Employee employee = (Employee) applicationSecurityManager
				.getEmployee(request);
		message.setFrom(employee.getEmail());
		message.setTo(employeeManager.getEmployee(
				employee.getManagerEmployeeId()).getEmail());
		SimpleMailMessage threadSafeMailMessage = new SimpleMailMessage(message);
		try {
			mailSender.send(threadSafeMailMessage);
		} catch (MailException e) {
			request.getSession()
					.setAttribute(
							"error",
							getMessageSourceAccessor().getMessage(
									"error.mail.notsent"));
			request.getSession().removeAttribute("message");
		}
	}

	public void setTimesheetManager(TimesheetManager timesheetManager) {
		this.timesheetManager = timesheetManager;
	}

	public void setDepartmentManager(DepartmentManager departmentManager) {
		this.departmentManager = departmentManager;
	}

	public void setApplicationSecurityManager(
			ApplicationSecurityManager applicationSecurityManager) {
		this.applicationSecurityManager = applicationSecurityManager;
	}

	public void setMessage(SimpleMailMessage message) {
		this.message = message;
	}

	public void setEmployeeManager(EmployeeManager employeeManager) {
		this.employeeManager = employeeManager;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

}
