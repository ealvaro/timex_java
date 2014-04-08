<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
						<table border="0" align="center" cellpadding="4" cellspacing="0"
							bordercolor="#CCCCCC">
							<tr valign="middle">
								<td width="90%" height="60" valign="middle">
									<h1>Timesheet Report</h1> <br />
								</td>
								<div align="left">
									<a href="javascript:history.go(-1)">Previous page</a>
								</div>
								<td align="right" nowrap="nowrap"><a href="signout.htm">Sign
										out</a>
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<div align="center">
										<span class="style31">Employee: <c:out
												value="${command.employee.name}" />
											&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Period
											Ending: <fmt:formatDate value="${command.periodEndingDate}"
												type="date" pattern="MMMM dd, yyyy" />
										</span><br>
									</div>
									<table border="0" align="center" cellpadding="8" cellspacing="25">
										<tr>
											<th scope="row"><span class="style25">Department</span>
											</th>
											<th><span class="style25">Mo</span>
											</th>
											<th><span class="style25">Tu</span>
											</th>
											<th><span class="style25">We</span>
											</th>
											<th><span class="style25">Th</span>
											</th>
											<th><span class="style25">Fr</span>
											</th>
											<th><span class="style25">Sa</span>
											</th>
											<th><span class="style25">Su</span>
											</th>
											<th><span class="style25">Total</span>
											</th>
										</tr>
										<tr>
											<td scope="row">
												<div align="center" class="style25">
													<c:out value="${command.department.name}" />
												</div></td>
											<td>
												<div align="center" class="style25">
													<c:choose>
														<c:when test="${command.minutesMon > 0}">
															<fmt:formatNumber value="${command.minutesMon / 60}"
																pattern="0.0" />
														</c:when>
														<c:otherwise>&nbsp;</c:otherwise>
													</c:choose>
												</div>
											<td>
												<div align="center" class="style25">
													<c:choose>
														<c:when test="${command.minutesTue > 0}">
															<fmt:formatNumber value="${command.minutesTue / 60}"
																pattern="0.0" />
														</c:when>
														<c:otherwise>&nbsp;</c:otherwise>
													</c:choose>
												</div>
											<td>
												<div align="center" class="style25">
													<c:choose>
														<c:when test="${command.minutesWed > 0}">
															<fmt:formatNumber value="${command.minutesWed / 60}"
																pattern="0.0" />
														</c:when>
														<c:otherwise>&nbsp;</c:otherwise>
													</c:choose>
												</div>
											<td>
												<div align="center" class="style25">
													<c:choose>
														<c:when test="${command.minutesThu > 0}">
															<fmt:formatNumber value="${command.minutesThu / 60}"
																pattern="0.0" />
														</c:when>
														<c:otherwise>&nbsp;</c:otherwise>
													</c:choose>
												</div></td>
											<td>
												<div align="center" class="style25">
													<c:choose>
														<c:when test="${command.minutesFri > 0}">
															<fmt:formatNumber value="${command.minutesFri / 60}"
																pattern="0.0" />
														</c:when>
														<c:otherwise>&nbsp;</c:otherwise>
													</c:choose>
												</div></td>
											<td>
												<div align="center" class="style24">
													<c:choose>
														<c:when test="${command.minutesSat > 0}">
															<fmt:formatNumber value="${command.minutesSat / 60}"
																pattern="0.0" />
														</c:when>
														<c:otherwise>&nbsp;</c:otherwise>
													</c:choose>
												</div></td>
											<td>
												<div align="center" class="style24">
													<c:choose>
														<c:when test="${command.minutesSun > 0}">
															<fmt:formatNumber value="${command.minutesSun / 60}"
																pattern="0.0" />
														</c:when>
														<c:otherwise>&nbsp;</c:otherwise>
													</c:choose>
												</div></td>
											<td>
												<div align="center" class="style25">
													<c:choose>
														<c:when test="${command.totalMinutes > 0}">
															<fmt:formatNumber value="${command.totalMinutes / 60}"
																pattern="0.0" />
														</c:when>
														<c:otherwise>&nbsp;</c:otherwise>
													</c:choose>
												</div></td>
										</tr>
									</table>
							<p align="center">
								&nbsp;&nbsp; <input name="submit" type="button" id="submit"
									value="Print Timesheet" onClick="window.print()">
							</p>
							</td>
							</tr>
							<tr>
								<td colspan="2" bgcolor="#C2DCEB">&nbsp;</td>
							</tr>
						</table></td>
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
