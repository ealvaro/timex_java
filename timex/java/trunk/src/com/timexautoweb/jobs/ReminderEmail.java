package com.timexautoweb.jobs;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.timexautoweb.domain.Employee;
import com.timexautoweb.domain.EmployeeHome;
import com.timexautoweb.domain.Timesheet;
import com.timexautoweb.domain.TimesheetHome;
import com.timexautoweb.util.DateUtil;

/**
 * Sending reminder emails to employees.
 */
public class ReminderEmail {

	private EmployeeHome employeeManager;
	private MailSender mailSender;
	private SimpleMailMessage employeeReminderMessage;
	private SimpleMailMessage managerReminderMessage;
	private TimesheetHome timesheetManager;
	private static final Logger logger = Logger.getLogger(ReminderEmail.class.getName());

	/**
	 * Sends emails to employees without submitted timesheet for this week.
	 */
	public void sendEmployeeReminderEMail() {
		List<String> emailAddressesList = getEmployeeEmails();
		// preparing and sending an email
		if (emailAddressesList.size() > 0) {
			String[] emailAddresses = new String[emailAddressesList.size()];
			// emailAddresses = (String[]) emailAddressesList.toArray();

			for (int i = 0; i < emailAddressesList.size(); ++i) {
				emailAddresses[i] = emailAddressesList.get(i);
			}

			employeeReminderMessage.setTo(emailAddresses);
			SimpleMailMessage mailMessage = new SimpleMailMessage(employeeReminderMessage);
			try {
				mailSender.send(mailMessage);
			} catch (MailException e) {
				logger.warning("sendEmployeeReminderEMail failure: from " + mailMessage.getFrom() + ", to "
						+ emailAddressesList);
			}
		}
	}

	protected List<String> getEmployeeEmails() {
		List<Employee> employees = employeeManager.getAllEmployees();
		if (employees == null || employees.size() == 0)
			return null;

		Date periodEndingDate = DateUtil.getCurrentPeriodEndingDate();
		List<String> emailAddressesList = new ArrayList<String>();
		Employee employee;
		for (Object obj : employees) {
			employee = (Employee) obj;
			// if employee has not created a current period timesheet add their email
			Timesheet t= timesheetManager.getTimesheet(employee.getId(), periodEndingDate);
			if (t == null || t.isPending()) {
				emailAddressesList.add(employee.getEmail());
			}
		}
		return emailAddressesList;
	}

	/**
	 * Send reminder email to managers who have timesheets pending approval.
	 */
	public void sendManagerReminderMail() {
		List<Timesheet> timesheets = timesheetManager.getTimesheets(Timesheet.PENDING);
		if (timesheets == null || timesheets.size() < 1)
			return;

		Set<Integer> toRemind = new TreeSet<Integer>();
		for (Timesheet t : timesheets) {
			toRemind.add(t.getEmployee().getManagerEmployeeId());
		}
		for (Integer employeeId : toRemind) {
			managerReminderMessage.setTo(employeeManager.findById(employeeId).getEmail());
			SimpleMailMessage threadSafeMailMessage = new SimpleMailMessage(managerReminderMessage);
			try {
				mailSender.send(threadSafeMailMessage);
			} catch (MailException e) {
				logger.log(Level.ALL, "Send email failure: from " + threadSafeMailMessage.getFrom() + ", to "
						+ threadSafeMailMessage.getTo());
			}
		}
	}
	public EmployeeHome getEmployeeManager() {
		return employeeManager;
	}

	public void setEmployeeManager(EmployeeHome employeeManager) {
		this.employeeManager = employeeManager;
	}

	public MailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public SimpleMailMessage getEmployeeReminderMessage() {
		return employeeReminderMessage;
	}

	public void setEmployeeReminderMessage(SimpleMailMessage employeeReminderMessage) {
		this.employeeReminderMessage = employeeReminderMessage;
	}

	public SimpleMailMessage getManagerReminderMessage() {
		return managerReminderMessage;
	}

	public void setManagerReminderMessage(SimpleMailMessage managerReminderMessage) {
		this.managerReminderMessage = managerReminderMessage;
	}
	public TimesheetHome getTimesheetManager() {
		return timesheetManager;
	}

	public void setTimesheetManager(TimesheetHome timesheetManager) {
		this.timesheetManager = timesheetManager;
	}

}
