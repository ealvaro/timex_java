<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Time Expression - Approve Timesheets</title>
<link href="includes/timex.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.style21 {
	font-size: 32px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	color: #667667;
}

.style30 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
}

.style31 {
	font-size: 12px
}
-->
</style>
</head>

<body>
<form method="post">
<table width="85%" border="1" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td>
		<table width="100%" border="0" align="center" cellpadding="4"
			cellspacing="0" bordercolor="#CCCCCC">
			<tr bgcolor="#C2DCEB">
				<td width="90%" height="60" valign="middle">
				<div align="right" class="style1 style17">
				<div align="left"><span class="style21">Approve
				Timesheets </span><br>
				</div>
				</div>
				</td>
				<td align="right" nowrap="nowrap"><a href="signout.htm">Sign
				out</a></td>
			</tr>
			<tr>
				<td colspan="2">
				<center><spring:bind path="command.*">
					<c:if test="${not empty status.errorMessages}">
						<c:forEach var="error" items="${status.errorMessages}">
							<font color="red"><c:out value="${error}"
								escapeXml="false" /> </font>
							<br />
						</c:forEach>
					</c:if>
				</spring:bind> <!-- status messages --> <c:if test="${not empty message}">
					<font color="green"><c:out value="${message}" /> </font>
					<c:set var="message" value="" scope="session" />
				</c:if></center>
				<br>
				<table border="1" align="center" cellpadding="8" cellspacing="0">
					<tr>
						<th scope="row">
						<div align="center" class="style30">Employee</div>
						</th>
						<th>
						<div align="center" class="style30">Hours For Week</div>
						</th>
						<th>
						<div align="center" class="style30">Status</div>
						</th>
						<th>
						<div align="center" class="style30">Approve</div>
						</th>
					</tr>
					<c:forEach items="${command.timesheets}" var="timesheet"
						varStatus="tStatus">
						<tr>
							<td scope="row">
							<div align="left" class="style30"><a
								href='printhours.htm?tid=<c:out value="${timesheet.timesheetId}"/>'>
							<c:out value="${timesheet.employee.name}" />, <c:out
								value="${timesheet.periodEndingDate}" /></a></div>
							</td>
							<td nowrap>
							<div align="right" class="style30"><fmt:formatNumber
								value="${timesheet.totalMinutes / 60}" pattern="0.00" /> <c:set
								var="incrementor"
								value="${incrementor + (timesheet.totalMinutes / 60)}" /></div>
							</td>
							<td align="center"
								title="P: pending
A: approved
C: paid
S: submitted
D: disapproved"><c:out
								value="${timesheet.statusCode}" /></td>
							<td nowrap>
							<div align="left" class="style30"><spring:bind
								path="command.timesheets[${tStatus.index}].statusCode">
								<input name='${status.expression}' type="radio" value="Yes"
									<c:if test="${status.value == 'Yes'}">checked</c:if>>
							    Yes&nbsp;&nbsp;
							    <input name='${status.expression}' type="radio" value="No"
									<c:if test="${status.value == 'No'}">checked</c:if>>
								 No&nbsp;&nbsp;
							</spring:bind></div>
							</td>
						</tr>
					</c:forEach>
					<tr bgcolor="#CCCCCC">
						<td scope="row"><span class="style30"><strong>TOTAL</strong></span></td>
						<td>
						<div align="center" class="style30"><strong><c:out
							value="${incrementor}" /></strong></div>
						</td>
						<td></td>
						<td></td>
					</tr>
				</table>
				</td>
			</tr>
			<tr>
				<td colspan="2" bgcolor="#C2DCEB">&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<div align="center"><input name="SaveChanges" type="submit"
	value="Send"> <input name="Reset" type="Reset" value="Reset"
	onclick="javascript:document.forms[0].reset()"> <input
	name="Cancel" type="button" value="Cancel"
	onclick="javascript:window.location='staffhours.htm'"></div>
<!-- timesheetlist.htm --></form>
</body>
</html>
