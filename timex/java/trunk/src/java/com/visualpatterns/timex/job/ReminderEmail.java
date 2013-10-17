package com.visualpatterns.timex.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.visualpatterns.timex.model.Employee;
import com.visualpatterns.timex.model.EmployeeManager;
import com.visualpatterns.timex.model.Timesheet;
import com.visualpatterns.timex.model.TimesheetManager;
import com.visualpatterns.timex.util.DateUtil;

/**
 * Sending reminder emails to employees.
 * 
 * @author Anil Hemrajani
 * @author Alvaro E. Escobar
 */
public class ReminderEmail {

	private EmployeeManager employeeManager;
	private MailSender mailSender;
	private SimpleMailMessage employeeReminderMessage;
	private SimpleMailMessage managerReminderMessage;
	private TimesheetManager timesheetManager;
	private static final Logger logger = Logger.getLogger(ReminderEmail.class
			.getName());

	/**
	 * Sends emails to employees without submitted timesheet for this week.
	 */
	public void sendEmployeeReminderMail() {
		List<Employee> employees = employeeManager.getAllEmployees();
		if (employees == null || employees.size() == 0)
			return;

		Date periodEndingDate = DateUtil.getCurrentPeriodEndingDate();
		List<String> emailAddressesList = new ArrayList<String>();
		Employee employee;
		for (Object obj : employees) {
			employee = (Employee) obj;
			if (timesheetManager.getTimesheet(employee.getEmployeeId(),
					periodEndingDate) == null) {
				emailAddressesList.add(employee.getEmail());
			}
		}

		// preparing and sending an email
		if (emailAddressesList.size() > 0) {
			String emailAddresses[] = new String[emailAddressesList.size()];
			for (int i = 0; i < emailAddressesList.size(); ++i) {
				emailAddresses[i] = emailAddressesList.get(i);
			}
			employeeReminderMessage.setTo(emailAddresses);
			SimpleMailMessage threadSafeMailMessage = new SimpleMailMessage(
					employeeReminderMessage);
			try {
				mailSender.send(threadSafeMailMessage);
			} catch (MailException e) {
				logger.warning("Send email failure: from "
						+ threadSafeMailMessage.getFrom() + ", to "
						+ threadSafeMailMessage.getTo());
			}
		}
	}

	/**
	 * Send reminder email to managers who have timesheets pending approval.
	 */
	public void sendManagerReminderMail() {
		List<Timesheet> timesheets = timesheetManager
				.getTimesheets(Timesheet.PENDING.charAt(0));
		if (timesheets == null || timesheets.size() < 1)
			return;

		Set<Integer> toRemind = new TreeSet<Integer>();
		for (Timesheet t : timesheets) {
			toRemind.add(t.getEmployee().getManagerEmployeeId());
		}
		for (Integer employeeId : toRemind) {
			managerReminderMessage.setTo(employeeManager
					.getEmployee(employeeId).getEmail());
			SimpleMailMessage threadSafeMailMessage = new SimpleMailMessage(
					managerReminderMessage);
			try {
				mailSender.send(threadSafeMailMessage);
			} catch (MailException e) {
				logger.warning("Send email failure: from "
						+ threadSafeMailMessage.getFrom() + ", to "
						+ threadSafeMailMessage.getTo());
			}
		}
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void setEmployeeReminderMessage(
			SimpleMailMessage employeeReminderMessage) {
		this.employeeReminderMessage = employeeReminderMessage;
	}

	public void setEmployeeManager(EmployeeManager employeeManager) {
		this.employeeManager = employeeManager;
	}

	public void setTimesheetManager(TimesheetManager timesheetManager) {
		this.timesheetManager = timesheetManager;
	}

	public void setManagerReminderMessage(SimpleMailMessage managerReminderMessage) {
		this.managerReminderMessage = managerReminderMessage;
	}
}
