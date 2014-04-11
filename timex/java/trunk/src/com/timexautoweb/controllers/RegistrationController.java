package com.timexautoweb.controllers;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.timexautoweb.domain.Employee;
import com.timexautoweb.domain.EmployeeHome;
import com.timexautoweb.util.PasswordUtil;

/**
 * Controller for the Sign In screen.
 * 
 * @author anil
 */
public class RegistrationController extends SimpleFormController {
	private EmployeeHome employeeManager;
	private String uploadFileSystemPath = "/uploads";
	private String uploadFileURL = "http://localhost/uploads";

	/**
	 * Always returns a new Employee object
	 * 
	 * @see Employee
	 */
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		return new Employee();
	}

	/**
	 * 
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest,
	 *      org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
		binder.registerCustomEditor(Date.class, editor);
	}

	/** returns ModelAndView(getSuccessView()) */
	public ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command,
			BindException errors) throws Exception {
		Employee formEmployee = (Employee) command;
		if (formEmployee.getId() == null && (employeeManager.findByUsername(formEmployee.getUsername())) == null) {
			formEmployee.setPassword(PasswordUtil.encryptPassword(formEmployee.getPassword()));
			formEmployee.setRegistrationDate(new Date());
			uploadFile(formEmployee);
			employeeManager.persist(formEmployee);
		} else {
			errors.reject("error.registration.invalid");
		}
		return new ModelAndView(getSuccessView());
	}

	protected void uploadFile(Employee formEmployee) {
		MultipartFile multipartFile = formEmployee.getFile();

		String fileName = "";
		String fileNameExtension = "";

		if (multipartFile != null) {
			fileName = multipartFile.getOriginalFilename();
			fileNameExtension = fileName.substring(fileName.lastIndexOf('.'));
			Timestamp ts = new Timestamp(new Date().getTime());
			String strTS = ts.toString().replace(':', '.');
			fileName = strTS + fileNameExtension;
			formEmployee.setPictureFilename(fileName);
			// Save the uploaded file in a folder where you can find it later on
			// and outside the webcontent directory of this project.
			String fileNameToCreate = this.uploadFileSystemPath + fileName;

			try {
				logger.info(fileNameToCreate);
				File diskFile = new File(fileNameToCreate);
				FileUtils.writeByteArrayToFile(diskFile, multipartFile.getBytes());

			} catch (Throwable e) {
				e.printStackTrace();
			}

		}
	}

	public EmployeeHome getEmployeeManager() {
		return employeeManager;
	}

	public void setEmployeeManager(EmployeeHome employeeManager) {
		this.employeeManager = employeeManager;
	}

	public String getUploadFileSystemPath() {
		return uploadFileSystemPath;
	}

	public void setUploadFileSystemPath(String uploadFileSystemPath) {
		this.uploadFileSystemPath = uploadFileSystemPath;
	}

	public String getUploadFileURL() {
		return uploadFileURL;
	}

	public void setUploadFileURL(String uploadFileURL) {
		this.uploadFileURL = uploadFileURL;
	}
}
