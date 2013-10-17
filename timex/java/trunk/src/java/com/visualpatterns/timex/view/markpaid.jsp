<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="com.visualpatterns.timex.model.Timesheet"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>TimeX - Mark Paid</title>
<link href="includes/timex.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.style21 {
	font-size: 32px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	color: #666666;
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
<table width="85%" border="1" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td>
		<table width="100%" border="0" align="center" cellpadding="4"
			cellspacing="0" bordercolor="#CCCCCC">
			<tr>
				<td width="90%" height="60" valign="middle" bgcolor="#C2DCEB">
				<div align="right" class="style1 style17">
				<div align="left"><span class="style21">Mark Paid </span><br>
				</div>
				</div>
				</td>
			</tr>
			<tr>
				<td colspan="2"><br>
				<form name="markpaid" method="post">
				<table border="1" align="center" cellpadding="8" cellspacing="0">
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
							<td align="center" nowrap>
							<div align="left" class="style30">
							<div align="center"><spring:bind
								path="command.timesheets[${tStatus.index}].statusCode">
								<input name='${status.expression}' type="checkbox" value=""
									onClick="timesheetPaid(this.name)">
							</spring:bind></div>
							</div>
							</td>
						</tr>
					</c:forEach>
					<tr bgcolor="#CCCCCC">
						<td scope="row"><span class="style30"><strong>TOTAL</strong></span></td>
						<td nowrap>
						<div align="right" class="style30"><strong><fmt:formatNumber
							value="${incrementor}" pattern="0.00" /></strong></div>
						</td>
						<td nowrap>&nbsp;</td>
					</tr>
				</table>
				<p align="center">&nbsp;&nbsp; <input name="submit" id="submit"
					type="submit" value="Save Changes"> &nbsp; <input
					name="reset" id="reset" type="reset" value="Reset"> <input
					name="Cancel" type="button" value="Cancel"
					onclick="javascript:window.location='timesheetlist.htm'"></p>
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
</body>
</html>
