package com.visualpatterns.timex.controller;

import java.beans.PropertyEditorSupport;

import com.visualpatterns.timex.model.Timesheet;

/**
 * Property editor for the Enter Hours screen; registered in the
 * EnterHoursController class.
 * 
 * @author Anil Hemrajani
 * @see com.visualpatterns.timex.controller.EnterHoursController
 */
public class YesNoPropertyEditor extends PropertyEditorSupport {

	/**
	 * @see java.beans.PropertyEditorSupport#getAsText()
	 * @return Yes or No
	 */
	public String getAsText() {
		String value = (String) getValue();
		String newValue = "No";
		if (value.equalsIgnoreCase(Timesheet.APPROVED))
			newValue = "Yes";
		return newValue;
	}

	/**
	 * Saves timesheet status code.
	 * 
	 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
	 */
	public void setAsText(String text) throws IllegalArgumentException {
		try {
			String newValue = Timesheet.PENDING;
			if (text.equalsIgnoreCase("Yes")) {
				newValue = Timesheet.APPROVED;
			} else if (text.equalsIgnoreCase("No")) {
				newValue = Timesheet.DISAPPROVED;
			}
			setValue(newValue);
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid option: " + text);
		}
	}

}
