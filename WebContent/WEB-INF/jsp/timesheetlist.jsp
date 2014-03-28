<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Online Timesheet System</title>
<meta name="keywords" content="Timeheets, Time, Clock" />
<meta name="description"
	content="This is TIMEX, the Online Timesheet System for ACME Company Inc." />
<link href="styles/default.css" rel="stylesheet" type="text/css" />
</head>
<body>
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
			<ul>
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
	<div id="page">
		<div id="content">
			<table align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<table align="center" cellpadding="4" cellspacing="0"
							bordercolor="#CCCCCC">
							<tr valign="middle">
								<td width="90%" height="60" valign="middle">
									<h1>Timesheet List</h1> <br /></td>
								<td align="right" nowrap="nowrap"><a href="signout.htm">Sign
										out</a></td>
							</tr>
							<tr>
								<td colspan="2">
									<p align="center">
										<!-- status messages -->
										<c:if test="${not empty message}">
											<font color="green"> <c:out value="${message}" /></font>
											<c:set var="message" value="" scope="session" />
										</c:if>
									</p> <br />
									<tr>
										<td>&nbsp;</td>
									</tr>
									<tr>
										<td>
											<div align="center">
												Employee :
												<c:out value="${employee.name}" />
												<p></p>
											</div></td>
									</tr>
									<p></p>
									<table border="0" align="center" cellpadding="8"
										cellspacing="10">
										<tr>
											<th>Period Ending</th>
											<th>Hours</th>
											<th>Department</th>
											<th>Status</th>
											<th>Timesheet Id</th>
										</tr>
										<c:forEach items="${timesheetsJSPVar}" var="t">
											<tr>
												<td align="center"><c:if
														test="${t.statusCode == 'P' || t.statusCode == 'D'}">
														<a
															href='enterhours.htm?tid=<c:out value="${t.id}"/>'>
															<fmt:formatDate value="${t.periodEndingDate}" type="date"
																pattern="MM/dd/yyyy" /> </a>
													</c:if> <c:if test="${t.statusCode == 'A' || t.statusCode == 'S'}">
														<a
															href='printhours.htm?tid=<c:out value="${t.id}"/>'>
															<fmt:formatDate value="${t.periodEndingDate}" type="date"
																pattern="MM/dd/yyyy" /> </a>
													</c:if>
												</td>
												<td align="center"><fmt:formatNumber
														value="${t.totalMinutes / 60}" pattern="0.00" /></td>
												<td align="center"><c:out value="${t.department.name}" />
													<td align="center"><c:out value="${t.statusCode}" />
														<div></div>
												</td>
													<td align="center"><c:out value="${t.id}" />
														<div></div>
												</td>
											</tr>
										</c:forEach>
									</table></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</div>
		<!-- end #content -->
		<div id="sidebar">
			<div id="news" class="boxed1">
				<h2 class="title">News &amp; Updates</h2>
				<div class="content">
					<ul>
						<li>
							<h3>
								February 14, 2012: <a href="#">TIMEX Training</a>
							</h3>
							<p>
								We are having a 2 hr training session on TIMEX. Please <a
									href="#">register here&hellip;</a>
							</p>
						</li>
						<li>
							<h3>
								March 31, 2012: <a href="#">End of Quarter Reports</a>
							</h3>
							<p>
								Make sure you submit your timesheets before this date for end of
								the quarter reports. <a href="#">Read more&hellip;</a>
							</p>
						</li>
						<li>
							<h3>
								June 30, 2012: <a href="#">End of Quarter Reports</a>
							</h3>
							<p>
								Make sure you submit your timesheets before this date for end of
								the quarter reports. <a href="#">Read more&hellip;</a>
							</p>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<!-- end #sidebar -->
		<div style="clear: both; height: 1px;"></div>
	</div>
	<!-- end #page -->
	<div id="footer">
		<p>
			Copyright &copy; 2012 TIMEX - Online Timesheet System for <a
				href="http://localhost:8080">ACME Company Inc.</a>
		</p>
	</div>
</body>
</html>
