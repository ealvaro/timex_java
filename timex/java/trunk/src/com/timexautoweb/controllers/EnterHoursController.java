package com.timexautoweb.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.timexautoweb.domain.DepartmentHome;
import com.timexautoweb.domain.Employee;
import com.timexautoweb.domain.EmployeeHome;
import com.timexautoweb.domain.Timesheet;
import com.timexautoweb.domain.TimesheetHome;
import com.timexautoweb.util.ApplicationSecurityManager;
import com.timexautoweb.util.DateUtil;

/**
 * Controller class for the "Enter Hours" screen
 * 
 * @author Anil Hemrajani
 * @author Alvaro E. Escobar
 */
public class EnterHoursController extends SimpleFormController {

	private TimesheetHome timesheetManager;
	private DepartmentHome departmentManager;
	private ApplicationSecurityManager applicationSecurityManager;
	private EmployeeHome employeeManager;
	private SimpleMailMessage message;
	private MailSender mailSender;

	public static final String TID = "tid";

	/**
	 * Returns a new instance of Timesheet object if "tid" HTTP parameter is not
	 * specified; otherwise returns instance of object in database matching the
	 * value of the "tid" parameter.
	 * 
	 * @see Timesheet
	 */
	protected Object formBackingObject(HttpServletRequest request) {
		Timesheet timesheet;
		Employee employee;
		if (request.getParameter(TID) != null && request.getParameter(TID).trim().length() > 0) {
			timesheet = timesheetManager.findById(Integer.parseInt(request.getParameter(TID)));
		} else {
			timesheet = new Timesheet();
			timesheet.setEmployee_id(((Employee) applicationSecurityManager.getEmployee(request)).getId());
			timesheet.setPeriodEndingDate(DateUtil.getCurrentPeriodEndingDate());
		}
		timesheet.setStatusCode(Timesheet.PENDING);
		return timesheet;
	}

	/**
	 * Registers the MinutesPropertyEditor.
	 * 
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest,
	 *      org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Integer.class, new MinutesPropertyEditor());
//		SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM dd, yyyy");
//		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
//		binder.registerCustomEditor(Date.class, editor);
	}

	/**
	 * Returns Hashmap containing a list of all Department database records.
	 * 
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#referenceData(javax.servlet.http.HttpServletRequest)
	 */
	@SuppressWarnings("unchecked")
	protected Map referenceData(HttpServletRequest request) throws Exception {
		HashMap model = new HashMap();
		model.put("departments", departmentManager.getDepartments());
		return model;
	}

	/**
	 * Saves or submits timesheet.
	 */
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) {
		Timesheet timesheet = (Timesheet) command;
		try {
			if (request.getParameter("sendEmail").equalsIgnoreCase("no")) {
				timesheet.setStatusCode(Timesheet.PENDING);
			} else {
				timesheet.setStatusCode(Timesheet.SUBMITTED);
				sendEmailToManager(request);
			}
			timesheetManager.attachDirty(timesheet);

			request.getSession().setAttribute("message",
					getMessageSourceAccessor().getMessage("message.enterhours.savesuccess"));
		} catch (Exception e) {
			if (e instanceof org.hibernate.exception.ConstraintViolationException) {
				request.getSession().setAttribute("error",
						getMessageSourceAccessor().getMessage("error.enterhours.preexistingerror"));
				request.getSession().removeAttribute("message");
			}
		}
		return new ModelAndView(getSuccessView());
	}

	private void sendEmailToManager(HttpServletRequest request) {
		Employee employee = (Employee) applicationSecurityManager.getEmployee(request);
		message.setFrom(employee.getEmail());
		message.setTo(employee.getEmployeeMngr().getEmail());
		SimpleMailMessage threadSafeMailMessage = new SimpleMailMessage(message);
		try {
			mailSender.send(threadSafeMailMessage);
		} catch (MailException e) {
			System.out.println("Error sending email OBO " + message.getFrom());
			request.getSession().setAttribute("error", getMessageSourceAccessor().getMessage("error.mail.notsent"));
			request.getSession().removeAttribute("message");
		}

	}

	public void setTimesheetManager(TimesheetHome timesheetManager) {
		this.timesheetManager = timesheetManager;
	}

	public void setDepartmentManager(DepartmentHome departmentManager) {
		this.departmentManager = departmentManager;
	}

	public void setEmployeeManager(EmployeeHome employeeManager) {
		this.employeeManager = employeeManager;
	}

	public void setApplicationSecurityManager(ApplicationSecurityManager applicationSecurityManager) {
		this.applicationSecurityManager = applicationSecurityManager;
	}

	public void setMessage(SimpleMailMessage message) {
		this.message = message;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

}
