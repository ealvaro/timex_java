<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="com.timexautoweb.domain.Timesheet"%>
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
<script type="text/javascript">
function timesheetPaid(name) {
	if (document.forms['markpaid'].elements[name].value == '<%= Timesheet.PAID %>')
		document.forms['markpaid'].elements[name].value=''
	else
		document.forms['markpaid'].elements[name].value='<%= Timesheet.PAID %>';
}
</script>
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
									<h1>Mark Paid</h1> <br />
								</td>
								<td align="right" nowrap="nowrap"><a href="signout.htm">Sign
										out</a>
								</td>
							</tr>
							<tr>
								<td colspan="2"><br>
									<form name="markpaid" method="post">
										<table border="0" align="center" cellpadding="8"
											cellspacing="10">
											<tr>
												<th scope="row">
													<div align="center" class="style30">Employee</div>
												</th>
												<th>
													<div align="center" class="style30">Hours For Week</div>
												</th>
												<th>
													<div align="center" class="style30">Mark Paid</div>
												</th>
											</tr>
											<c:forEach items="${command.timesheets}" var="timesheet"
												varStatus="tStatus">
												<tr>
													<td scope="row">
														<div align="left" class="style30">
															<a
																href='printhours.htm?tid=<c:out value="${timesheet.id}"/>'>
																<c:out value="${timesheet.employee.name}" />, <c:out
																	value="${timesheet.periodEndingDate}" /> </a>
														</div>
													</td>
													<td nowrap>
														<div align="right" class="style30">
															<fmt:formatNumber value="${timesheet.totalMinutes / 60}"
																pattern="0.00" />
															<c:set var="incrementor"
																value="${incrementor + (timesheet.totalMinutes / 60)}" />
														</div>
													</td>
													<td align="center" nowrap>
														<div align="left" class="style30">
															<div align="center">
																<spring:bind
																	path="command.timesheets[${tStatus.index}].statusCode">
																	<input name='${status.expression}' type="checkbox"
																		value="" onClick="timesheetPaid(this.name)">
																</spring:bind>
															</div>
														</div>
													</td>
												</tr>
											</c:forEach>
											<tr bgcolor="#CCCCCC">
												<td scope="row"><span class="style30"><strong>TOTAL</strong>
												</span></td>
												<td nowrap>
													<div align="right" class="style30">
														<strong><fmt:formatNumber value="${incrementor}"
																pattern="0.00" /> </strong>
													</div>
												</td>
												<td nowrap>&nbsp;</td>
											</tr>
										</table>
							<p align="center">
								&nbsp;&nbsp; <input name="submit" id="submit" type="submit"
									value="Save Changes"> &nbsp; <input name="reset"
									id="reset" type="reset" value="Reset"> <input
									name="Cancel" type="button" value="Cancel"
									onclick="javascript:window.location='timesheetlist.htm'">
							</p>
							</form>
							</td>
							</tr>
							<tr>
								<td colspan="2" bgcolor="#C2DCEB">&nbsp;</td>
							</tr>
						</table>
				</td>
				</tr>
			</table>
		</div>
		<!-- end #content -->

		<jsp:include page="../../sidebar_home.jsp"/>
		
		<div style="clear: both; height: 1px;"></div>
	</div>
<!-- end #page -->

		<jsp:include page="../../footer.jsp"/>
		
</body>
</html>