<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>TimeX - Print Hours</title>
<link href="includes/timex.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.style21 {
	font-size: 32px;
	font-family: Verdana, Arial, Helvetica, sans-serif;
	color: #667667;
}

.style22 {
	font-family: Verdana, Arial, Helvetica, sans-serif
}

.style25 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px
}

.style28 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12
}

.style29 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 10px
}

.style30 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 10
}

.style31 {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 12px;
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
			<tr>
				<td width="90%" height="60" valign="middle" bgcolor="#C2DCEB">
				<div align="right" class="style1 style17">
				<div align="left"><a href="javascript:history.go(-1)">Previous
				page</a><br>
				</div>
				</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
				<div align="center"><span class="style31">Employee: <c:out
					value="${command.employee.name}" />
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Period
				Ending: <fmt:formatDate value="${command.periodEndingDate}"
					type="date" pattern="MMMM dd, yyyy" /></span><br>
				</div>
				<table border="1" align="center" cellpadding="8" cellspacing="0">
					<tr>
						<th scope="row"><span class="style25">Department</span></th>
						<th><span class="style25">Mo</span></th>
						<th><span class="style25">Tu</span></th>
						<th><span class="style25">We</span></th>
						<th><span class="style25">Th</span></th>
						<th><span class="style25">Fr</span></th>
						<th><span class="style25">Sa</span></th>
						<th><span class="style25">Su</span></th>
						<th><span class="style25">Total</span></th>
					</tr>
					<tr>
						<td scope="row">
						<div align="center" class="style25"><c:out
							value="${command.department.name}" /></div>
						</td>
						<td>
						<div align="center" class="style25"><c:choose>
							<c:when test="${command.minutesMon > 0}">
								<fmt:formatNumber value="${command.minutesMon / 60}"
									pattern="0.0" />
							</c:when>
							<c:otherwise>&nbsp;</c:otherwise>
						</c:choose></div>
						<td>
						<div align="center" class="style25"><c:choose>
							<c:when test="${command.minutesTue > 0}">
								<fmt:formatNumber value="${command.minutesTue / 60}"
									pattern="0.0" />
							</c:when>
							<c:otherwise>&nbsp;</c:otherwise>
						</c:choose></div>
						<td>
						<div align="center" class="style25"><c:choose>
							<c:when test="${command.minutesWed > 0}">
								<fmt:formatNumber value="${command.minutesWed / 60}"
									pattern="0.0" />
							</c:when>
							<c:otherwise>&nbsp;</c:otherwise>
						</c:choose></div>
						<td>
						<div align="center" class="style25"><c:choose>
							<c:when test="${command.minutesThu > 0}">
								<fmt:formatNumber value="${command.minutesThu / 60}"
									pattern="0.0" />
							</c:when>
							<c:otherwise>&nbsp;</c:otherwise>
						</c:choose></div>
						</td>
						<td>
						<div align="center" class="style25"><c:choose>
							<c:when test="${command.minutesFri > 0}">
								<fmt:formatNumber value="${command.minutesFri / 60}"
									pattern="0.0" />
							</c:when>
							<c:otherwise>&nbsp;</c:otherwise>
						</c:choose></div>
						</td>
						<td>
						<div align="center" class="style24"><c:choose>
							<c:when test="${command.minutesSat > 0}">
								<fmt:formatNumber value="${command.minutesSat / 60}"
									pattern="0.0" />
							</c:when>
							<c:otherwise>&nbsp;</c:otherwise>
						</c:choose></div>
						</td>
						<td>
						<div align="center" class="style24"><c:choose>
							<c:when test="${command.minutesSun > 0}">
								<fmt:formatNumber value="${command.minutesSun / 60}"
									pattern="0.0" />
							</c:when>
							<c:otherwise>&nbsp;</c:otherwise>
						</c:choose></div>
						</td>
						<td>
						<div align="center" class="style25"><c:choose>
							<c:when test="${command.totalMinutes > 0}">
								<fmt:formatNumber value="${command.totalMinutes / 60}"
									pattern="0.0" />
							</c:when>
							<c:otherwise>&nbsp;</c:otherwise>
						</c:choose></div>
						</td>
					</tr>
				</table>
				<p align="center">&nbsp;&nbsp; <input name="submit"
					type="button" id="submit" value="Print Timesheet"
					onClick="window.print()"></p>
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
