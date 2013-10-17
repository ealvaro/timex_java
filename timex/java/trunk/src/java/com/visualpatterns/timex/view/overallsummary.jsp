<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>TimeX - Report: Overall Summary</title>
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
				<td width="90%" height="60" valign="middle" bgcolor="#C2DCEB">
				<div align="right" class="style1 style17">
				<div align="left"><span class="style28 style19">Report:
				Overall Summary </span><br>
				</div>
				</div>
				</td>
				<td align="right" nowrap="nowrap"><a href="signout.htm">Sign
				out</a></td>
			</tr>
			<tr>
				<td colspan="2"><br>
				<table border="1" align="center" cellpadding="8" cellspacing="0">
					<tr>
						<th scope="row">
						<div align="center" class="style18">Manager</div>
						</th>
						<th>
						<div align="center" class="style18">Hours For Week</div>
						</th>
						<th>
						<div align="center" class="style18">Status</div>
						</th>
					</tr>
					<c:forEach items="${command.summaries}" var="summary"
						varStatus="tStatus">
						<tr valign="top">
							<td scope="row">
							<div align="left" class="style18"><c:out
								value="${summary.manager.name}" /></div>
							</td>
							<td nowrap>
							<div align="right" class="style18"><fmt:formatNumber
								value="${summary.totalMinutes / 60}" pattern="0.00" /> <c:set
								var="incrementor"
								value="${incrementor + (summary.totalMinutes / 60)}" /></div>
							</td>
							<td nowrap><span class="style18"><c:out
								value="${summary.paid}" /> paid<br>
							<c:out value="${summary.unpaid}" /> unpaid<br>
							<c:out value="${summary.approved}" /> approved<br>
							<c:out value="${summary.disapproved}" /> disapproved</span></td>
						</tr>
					</c:forEach>
					<tr bgcolor="#CCCCCC">
						<td scope="row">
						<div align="right" class="style18">Average Hours</div>
						</td>
						<td nowrap>
						<div align="right" class="style18"><fmt:formatNumber
							value="${incrementor / command.size}" pattern="0.00" /></div>
						</td>
						<td nowrap>&nbsp;</td>
					</tr>
					<tr bgcolor="#CCCCCC">
						<td scope="row">
						<div align="right" class="style18"><strong>TOTAL</strong></div>
						</td>
						<td nowrap>
						<div align="right" class="style18"><strong><c:out
							value="${incrementor}" /></strong></div>
						</td>
						<td nowrap>&nbsp;</td>
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
