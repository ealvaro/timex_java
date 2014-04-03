package com.timexautoweb.jobs;

import java.util.List;

import junit.framework.TestCase;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.timexautoweb.domain.EmployeeHome;
import com.timexautoweb.domain.TimesheetHome;

public class ReminderEmailTest extends TestCase {
	private EmployeeHome employeeManager = new EmployeeHome();
	private JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	private SimpleMailMessage employeeReminderMessage = new SimpleMailMessage();
	private TimesheetHome timesheetManager = new TimesheetHome();
	private ReminderEmail reminder = new ReminderEmail();

	protected void setUp() throws Exception {
		super.setUp();
		mailSender.setHost("smtp.nova.edu");
		employeeReminderMessage.setFrom("ealvaro@nova.edu");
		employeeReminderMessage.setSubject("Reminder: Please Submit Timesheet");
		employeeReminderMessage.setText("Please don't forget to submit your timesheet.  Thank you!");
		reminder.setEmployeeManager(employeeManager);
		reminder.setEmployeeReminderMessage(employeeReminderMessage);
		reminder.setMailSender(mailSender);
		reminder.setTimesheetManager(timesheetManager);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testSendEmployeeReminderEMail() {
		reminder.sendEmployeeReminderEMail();
		SimpleMailMessage message = (SimpleMailMessage) reminder.getEmployeeReminderMessage();
		assertNotNull(message);
		assertNotNull(message.getTo());
	}

	public void testGetEmployeeEmails() {
		List<String> emails = reminder.getEmployeeEmails();
		assertNotNull(emails);

	}

}
