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
			<li><a href="index.jsp" accesskey="H" title="Home Page"><b>H</b>ome</a>
			</li>
			<li><a href="signin.htm" accesskey="L" title="Login"><b>L</b>ogin</a>
			</li>
			<li><a href="registration.html" accesskey="R"
				title="Employee Registration"><b>R</b>egister</a>
			</li>
			<li><a href="timesheetlist.htm" accesskey="T"
				title="Employee Timesheets"><b>T</b>imesheets</a>
				<ul>
					<li><a href="enterhours.htm" accesskey="N"
						title="New Timesheet"><b>N</b>ew</a></li>
					<li><a href="timesheetlist.htm" accesskey="N"
						title="All Unpaid Timesheets"><b>U</b>npaid Timesheets</a></li>
					<li><a href="printpaycheck.html" accesskey="P"
						title="All Paid Timesheets"><b>P</b>aid Timesheets</a></li>
					<li><a href="approvetimesheets.html" accesskey="P"
						title="Approve Timesheet Payments"><b>A</b>pprove Timesheets</a></li>
				</ul></li>
			<li><a href="timesheetlist.htm" accesskey="T"
				title="Employee Timesheets"><b>R</b>eports</a>
				<ul>
					<c:if
						test="${employee.employeeType == 'M' || employee.employeeType == 'E'}">
						<li><a href="staffhours.html" accesskey="S"
							title="view a summary of a given week's hours for all employees"><b>S</b>taff
								Hours Report</a></li>
					</c:if>
					<c:if test="${employee.employeeType == 'E'}">
						<li><a href="overallsummary.html" accesskey="O"
							title="view a summary of a given week's hours for all employees per Manager."><b>O</b>verall
								Summary Report</a></li>
					</c:if>
					<c:if test="${employee.employeeType == 'A'}">
						<li><a href="markpaid.html" accesskey="P"
							title="pay this	week's hours for all employees"><b>P</b>ay
								Timesheets</a></li>
					</c:if>
				</ul></li>
			<li><a href="#" accesskey="H" title="Help"><b>H</b>elp</a></li>
		</ul>
	</div>
</div>
