package com.timexautoweb.controllers;

import java.beans.PropertyEditorSupport;

import com.timexautoweb.domain.Timesheet;

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
		Character value = (Character) getValue();
		String newValue = "No";
		if (value == (Timesheet.APPROVED))
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
			Character newValue = Timesheet.PENDING;
			if (text.equalsIgnoreCase("A")) {
				newValue = Timesheet.APPROVED;
			} else if (text.equalsIgnoreCase("D")) {
				newValue = Timesheet.DISAPPROVED;
			}
			setValue(newValue);
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid option: " + text);
		}
	}

}
