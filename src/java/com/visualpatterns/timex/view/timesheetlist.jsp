<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Time Expression - Timesheet List</title>
<link href="includes/timex.css" rel="stylesheet" type="text/css">
</head>

<body>
<table width="85%" border="1" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td>
		<table width="100%" border="0" align="center" cellpadding="4"
			cellspacing="0" bordercolor="#CCCCCC">
			<tr bgcolor="#C2DCEB" valign="middle">
				<td width="90%" height="60" valign="middle" bgcolor="#C2DCEB">
				<h1>Timesheet List</h1>
				</td>
				<td align="right" nowrap="nowrap"><a href="signout.htm">Sign
				out</a></td>
			</tr>
			<tr>
				<td colspan="2"><!-- error messages --> <c:if
					test="${not empty error}">
					<p align="center"><font color="red"><c:out
						value="${error}" /></font> <c:set var="error" value="" scope="session" /></p>
				</c:if> <!-- status messages --> <c:if test="${not empty message}">
					<p align="center"><font color="green"><c:out
						value="${message}" /></font> <c:set var="message" value=""
						scope="session" /></p>
				</c:if></td>
			</tr>
			<tr>
				<td colspan="2">
				<p align="center"><a href="enterhours.htm">Click here</a> to add
				a new timesheet, or select one from the list below.</p>
				</td>
			</tr>
			<c:if
				test="${employee.employeeCode == 'M' || employee.employeeCode == 'E'}">
				<tr>
					<td colspan="2">
					<p align="center"><a href="staffhours.htm">Click here</a> to
					view a summary of a given week's hours for all employees.</p>
					</td>
				</tr>
			</c:if>
			<c:if test="${employee.employeeCode == 'E'}">
				<tr>
					<td colspan="2">
					<p align="center"><a href="overallsummary.htm">Click here</a>
					to view a summary of a given week's hours for all employees per
					Manager.</p>
					</td>
				</tr>
			</c:if>
			<c:if test="${employee.employeeCode == 'A'}">
				<tr>
					<td colspan="2">
					<p align="center"><a href="markpaid.htm">Click here</a> to view
					a list of timesheets that have to be paid.</p>
					</td>
				</tr>
			</c:if>
			<tr>
				<td>
				<p align="center">employee : <c:out value="${employee.name}" />
				</p>
				</td>
				<td></td>
			</tr>
			<tr>
				<td colspan="2">
				<table border="1" align="center" cellpadding="8" cellspacing="0">
					<tr>
						<th>Period Ending</th>
						<th>Hours</th>
						<th>Department</th>
						<th>Status</th>
						<th>Timesheet Id</th>
					</tr>
					<c:forEach items="${timesheets}" var="timesheet">
						<tr>
							<td align="center">
							<c:choose>
								<c:when test="${timesheet.pending}">
									<a
										href='enterhours.htm?tid=<c:out	value="${timesheet.timesheetId}"/>'>
									<fmt:formatDate value="${timesheet.periodEndingDate}"
										type="date" pattern="MM/dd/yyyy" /> </a>
								</c:when>
								<c:otherwise>
									<a
										href='printhours.htm?tid=<c:out	value="${timesheet.timesheetId}"/>'>
									<fmt:formatDate value="${timesheet.periodEndingDate}"
										type="date" pattern="MM/dd/yyyy" /> </a>
								</c:otherwise>
							</c:choose>
							<div></div>
							</td>
							<td align="center"><fmt:formatNumber
								value="${timesheet.totalMinutes / 60}" pattern="0.00" /></td>
							<td align="center"><c:out
								value="${timesheet.department.name}" />
							<div></div>
							</td>
							<td align="center"
								title="P: pending
A: approved
C: paid
S: submitted
D: disapproved"><c:out
								value="${timesheet.statusCode}" /></td>
							<td align="center"><c:out value="${timesheet.timesheetId}" />
							<div></div>
							</td>
						</tr>
					</c:forEach>
				</table>
				<p align="center">&nbsp;&nbsp; <input name="submit"
					type="button" id="submit" value="Print Timesheet List"
					onClick=
	window.print();
></p>
				</td>
			</tr>
			<tr>
				<td colspan="2" bgcolor="#C2DCEB">&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
</table>

</body>
</html>
