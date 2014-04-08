package com.timexautoweb.controllers;

import java.util.List;
import java.util.Vector;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.timexautoweb.domain.Employee;
import com.timexautoweb.domain.EmployeeHome;
import com.timexautoweb.domain.Timesheet;
import com.timexautoweb.domain.TimesheetHome;
import com.timexautoweb.domain.TimesheetList;
import com.timexautoweb.util.ApplicationSecurityManager;
import com.timexautoweb.util.DateUtil;

/**
 * Controller class for the "Approve Timesheets" screen
 * 
 * @author Alvaro Escobar
 */
public class ApproveTimesheetsController extends SimpleFormController {

	private TimesheetHome timesheetManager;
	private EmployeeHome employeeManager;
	private ApplicationSecurityManager applicationSecurityManager;
	private SimpleMailMessage approvalMessage;
	private SimpleMailMessage disapprovalMessage;
	private MailSender mailSender;
	private static final Logger logger = Logger.getLogger(ApproveTimesheetsController.class.getName());

	/**
	 * Returns a list of approvable timesheets from employees that report to
	 * signed-in employee.
	 * 
	 * @see Timesheet
	 */
	protected Object formBackingObject(HttpServletRequest request) {
		Employee employee = (Employee) applicationSecurityManager.getEmployee(request);
		List<Employee> reportingEmployees = employeeManager.getReportingEmployees(employee.getId());
		List<Timesheet> timesheets = new Vector<Timesheet>();
		for (Employee e : reportingEmployees) {
			Integer employeeId = e.getId();
			List<Timesheet> empTimesheets = timesheetManager.getTimesheets(employeeId);
			for (Timesheet t : empTimesheets) {
				if (t.getStatusCode() == (Timesheet.SUBMITTED)
						|| (DateUtil.isInCurrentPayPeriod(t.getPeriodEndingDate()) && (t.getStatusCode() == (Timesheet.DISAPPROVED) || t
								.getStatusCode() == (Timesheet.APPROVED))))
					timesheets.add(t);
			}
		}
		return new TimesheetList(timesheets);
	}

	/**
	 * Registers the YesNoPropertyEditor.
	 */
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(String.class, new YesNoPropertyEditor());
	}

	/**
	 * Saves <code>Timesheet</code> command object using
	 * <code>timesheetManager.saveTimesheet(timesheet)</code>;
	 * 
	 * @see TimesheetHome
	 */
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) {
		String emailFrom = ((Employee) applicationSecurityManager.getEmployee(request)).getEmail();

		TimesheetList appTimesheets = (TimesheetList) command;
		List<Timesheet> timesheets = appTimesheets.getTimesheets();
		for (Timesheet t : timesheets) {
			timesheetManager.attachDirty(t);
			if (t.getStatusCode() == (Timesheet.APPROVED)) {
				sendApprovalEmail(emailFrom, t);
			} else {
				sendDisapprovalEmail(emailFrom, t);
			}
		}
//		request.getSession().setAttribute("message",
//				getMessageSourceAccessor().getMessage("message.approvetimesheets.savesuccess"));
		return new ModelAndView(getSuccessView());
	}

	/**
	 * Send approval email to Employee and accounting department.
	 */
	private void sendApprovalEmail(String emailFrom, Timesheet t) {
		approvalMessage.setFrom(emailFrom);
		approvalMessage.setTo(new String[] { approvalMessage.getTo()[0], t.getEmployee().getEmail() });
		SimpleMailMessage threadSafeMailMessage = new SimpleMailMessage(approvalMessage);
		sendMessage(threadSafeMailMessage);
	}

	/**
	 * Send disapproval email to Employee.
	 */
	private void sendDisapprovalEmail(String emailFrom, Timesheet t) {
		disapprovalMessage.setFrom(emailFrom);
		disapprovalMessage.setTo(t.getEmployee().getEmail());
		SimpleMailMessage threadSafeMailMessage = new SimpleMailMessage(disapprovalMessage);
		sendMessage(threadSafeMailMessage);
	}

	private void sendMessage(SimpleMailMessage msg) {
		try {
			mailSender.send(msg);
		} catch (MailException e) {
			logger.warning("Send email failure: from " + msg.getFrom() + ", to " + msg.getTo());
		}
	}

	public void setTimesheetManager(TimesheetHome timesheetManager) {
		this.timesheetManager = timesheetManager;
	}

	public void setEmployeeManager(EmployeeHome employeeManager) {
		this.employeeManager = employeeManager;
	}

	public void setApplicationSecurityManager(ApplicationSecurityManager applicationSecurityManager) {
		this.applicationSecurityManager = applicationSecurityManager;
	}

	public void setApprovalMessage(SimpleMailMessage approvalMessage) {
		this.approvalMessage = approvalMessage;
	}

	public void setDisapprovalMessage(SimpleMailMessage disapprovalMessage) {
		this.disapprovalMessage = disapprovalMessage;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
}
