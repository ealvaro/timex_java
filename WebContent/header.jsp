<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<div id="header">
		<div id="logo">
			<h1>
				<a href="#"><b>T I M E X</b> </a>
			</h1>
			<h2>
				<a href="">Online Timesheet System</a>
			</h2>
		</div>
		<div id="menu">
			<ul id="jsddm">
				<li class="active"><a href="enterhours.htm" accesskey="N"
					title="Login"><b>N</b>ew</a></li>
				<c:if
					test="${employee.employeeType == 'M' || employee.employeeType == 'E'}">
					<li class="active"><a href="staffhours.htm" accesskey="S"
						title="view a summary of a given week's hours for all employees"><b>S</b>taff
							Hours Report</a></li>
				</c:if>
				<c:if test="${employee.employeeType == 'E'}">
					<li class="active"><a href="overallsummary.htm" accesskey="O"
						title="view a summary of a given week's hours for all employees per Manager."><b>O</b>verall Summary Report</a></li>
				</c:if>
				<c:if test="${employee.employeeType == 'A'}">
					<li class="active"><a href="markpaid.htm"
						accesskey="P" title="pay this	week's hours for all employees"><b>P</b>ay
							Timesheets</a></li>
				</c:if>
				<li><a href="#" accesskey="R" title="Register"><b>R</b>egister</a>
				</li>
				<li><a href="#" accesskey="C" title="Contact Us"><b>C</b>ontact
						Us</a></li>
				<li><a href="#" accesskey="H" title="Help"><b>H</b>elp</a></li>
			</ul>
		</div>
	</div>
