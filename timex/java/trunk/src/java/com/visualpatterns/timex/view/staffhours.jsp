<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>TimeX - Report: Staff Hours</title>
<link href="includes/timex.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.style17 {
	font-family: Verdana, Arial, Helvetica, sans-serif
}

.style18 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
}

.style19 {
	font-size: 32px;
	color: #667667;
}

.style21 {
	font-size: 12px
}

.style24 {
	font-size: 12
}

.style28 {
	color: #667667
}
-->
</style>
</head>

<body>
<table width="85%" border="1" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td>
		<table width="100%" border="0" align="center" cellpadding="4"
			cellspacing="0" bordercolor="#CCCCCC">
			<tr bgcolor="#C2DCEB">
				<td width="90%" height="60" valign="middle">
				<div align="right" class="style1 style17">
				<div align="left"><span class="style28 style19">Report:
				Staff Hours </span><br>
				</div>
				</div>
				</td>
				<td align="right" nowrap="nowrap"><a href="signout.htm">Sign
				out</a></td>
			</tr>
			<tr>
				<td colspan="2">
				<p align="center"><a href="approvetimesheets.htm">Click here</a>
				to approve your employee's timesheets.</p>
				</td>
			</tr>
			<tr>
				<td colspan="2"><br>
				<table border="1" align="center" cellpadding="8" cellspacing="0">
					<tr>
						<th scope="row">
						<div align="center" class="style18">Employee</div>
						</th>
						<th><span class="style18">Type</span></th>
						<th>
						<div align="center" class="style18">Hours For Week</div>
						</th>
					</tr>
					<c:forEach items="${command.timesheets}" var="timesheet"
						varStatus="tStatus">
						<tr>
							<td scope="row">
							<div align="left" class="style18"><a
								href='printhours.htm?tid=<c:out	value="${timesheet.timesheetId}"/>'><c:out
								value="${timesheet.employee.name}" />, <c:out
								value="${timesheet.periodEndingDate}" /></a></div>
							</td>
							<td nowrap><span class="style18"><c:out
								value="${timesheet.employee.type}" /></span></td>
							<td nowrap>
							<div align="right" class="style18"><fmt:formatNumber
								value="${timesheet.totalMinutes / 60}" pattern="0.00" /> <c:set
								var="incrementor"
								value="${incrementor + (timesheet.totalMinutes / 60)}" /></div>
							</td>
						</tr>
					</c:forEach>
					<tr bgcolor="#CCCCCC">
						<td colspan="2" scope="row">
						<div align="right" class="style18">Average Hours</div>
						</td>
						<td nowrap>
						<div align="right" class="style18"><fmt:formatNumber
							value="${incrementor / command.size}" pattern="0.00" /></div>
						</td>
					</tr>
					<tr bgcolor="#CCCCCC">
						<td colspan="2" scope="row">
						<div align="right" class="style18"><strong>TOTAL</strong></div>
						</td>
						<td nowrap>
						<div align="right" class="style18"><strong><c:out
							value="${incrementor}" /></strong></div>
						</td>
					</tr>
				</table>
				<p align="center">&nbsp;&nbsp; <input name="submit"
					type="button" id="submit" value="Print" onClick="window.print()">
				&nbsp; <input name="reset" type="reset" id="reset" value="Cancel"
					onClick="javascript:window.location='timesheetlist.htm'"></p>
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
