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
<link href="styles/menu.css" rel="stylesheet" type="text/css" />
<script src="javascript/jquery.min.js" type="text/javascript"></script>
<script src="javascript/menu.js" type="text/javascript"></script>
</head>
<body>

		<jsp:include flush="false" page="../../header.jsp"/>
		
	<div id="page">
		<div id="content">
			<table align="center" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<table align="center" cellpadding="4" cellspacing="0"
							bordercolor="#CCCCCC">
							<tr valign="middle">
								<td width="90%" height="60" valign="middle">
									<h1>Report: Staff Hours</h1> <br />
								</td>
								<td align="right" nowrap="nowrap"><a href="signout.htm">Sign
										out</a>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div align="center">
										Employee :
										<c:out value="${employee.name}" />
									</div>
									<div align="center">
										Type :
										<c:out value="${employee.employeeType}" />
									</div></td>
							</tr>
							<tr>
								<td colspan="2"><br>
										<p></p>
										<table border="0" align="center" cellpadding="8"
											cellspacing="20">
											<tr>
												<th scope="row">
													<div align="center" class="style18">Employee</div>
												</th>
												<th><span class="style18">Type</span></th>
												<th>
													<div align="center" class="style18">Hours For Week</div>
												</th>
												<th>
													<div align="center" class="style18">Status</div>
												</th>
											</tr>
											<c:forEach items="${command.timesheets}" var="timesheet"
												varStatus="tStatus">
												<tr>
													<td scope="row">
														<div align="left" class="style18">
															<a
																href='printhours.htm?tid=<c:out	value="${timesheet.id}"/>'><c:out
																	value="${timesheet.employee.name}" />, <fmt:formatDate
																	value="${timesheet.periodEndingDate}" type="date"
																	pattern="MM/dd/yyyy" /> </a>
														</div>
													</td>
													<td nowrap><span class="style18"><c:out
																value="${timesheet.employee.employeeType}" /> </span></td>
													<td nowrap>
														<div align="right" class="style18">
															<fmt:formatNumber value="${timesheet.totalMinutes / 60}"
																pattern="0.00" />
															<c:set var="incrementor"
																value="${incrementor + (timesheet.totalMinutes / 60)}" />
														</div>
													</td>
													<td nowrap><span class="style18"><c:out
																value="${timesheet.statusCode}" /> </span></td>
												</tr>
											</c:forEach>
											<tr bgcolor="#CCCCCC">
												<td colspan="2" scope="row">
													<div align="right" class="style18">Average Hours</div>
												</td>
												<td nowrap>
													<div align="right" class="style18">
														<fmt:formatNumber value="${incrementor / command.size}"
															pattern="0.00" />
													</div>
												</td>
											</tr>
											<tr bgcolor="#CCCCCC">
												<td colspan="2" scope="row">
													<div align="right" class="style18">
														<strong>TOTAL</strong>
													</div>
												</td>
												<td nowrap>
													<div align="right" class="style18">
														<strong><c:out value="${incrementor}" /> </strong>
													</div>
												</td>
											</tr>
										</table>
								</td>
							</tr>
							<tr>
								<td colspan="2"><p align="center">
										&nbsp;&nbsp; <input name="submit" type="button" id="submit"
											value="Print" onClick="window.print()"> &nbsp; <input
											name="reset" type="reset" id="reset" value="Cancel"
											onClick="javascript:window.location='timesheetlist.htm'">
									</p></td>
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
							</p></li>
						<li>
							<h3>
								March 31, 2012: <a href="#">End of Quarter Reports</a>
							</h3>
							<p>
								Make sure you submit your timesheets before this date for end of
								the quarter reports. <a href="#">Read more&hellip;</a>
							</p></li>
						<li>
							<h3>
								June 30, 2012: <a href="#">End of Quarter Reports</a>
							</h3>
							<p>
								Make sure you submit your timesheets before this date for end of
								the quarter reports. <a href="#">Read more&hellip;</a>
							</p></li>
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
