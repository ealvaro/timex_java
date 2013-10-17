<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>Time Expression - Enter Hours</title>
<link href="includes/timex.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function enabledButtons(name) {
	if (document.forms[0].elements[name].selectedIndex == 0) {
		document.getElementById('save').disabled = true;
		document.getElementById('saveAndEmail').disabled = true;
	} else {
		document.getElementById('save').disabled = false;
		document.getElementById('saveAndEmail').disabled = false;
	}
}

function checkMaxVal(name) {
	if (document.forms[0].elements[name].value > 16) {
		document.forms[0].elements[name].value = 16;
		alert('Allowed maximum is 16 hours per day');
	}
	document.getElementById('total').innerText = getTotal();
}

function getTotal() {
	var mon = new Number(document.forms[0].elements['minutesMon'].value);
	var tue = new Number(document.forms[0].elements['minutesTue'].value);
	var wed = new Number(document.forms[0].elements['minutesWed'].value);
	var thu = new Number(document.forms[0].elements['minutesThu'].value);
	var fri = new Number(document.forms[0].elements['minutesFri'].value);
	var sat = new Number(document.forms[0].elements['minutesSat'].value);
	var sun = new Number(document.forms[0].elements['minutesSun'].value);
	return mon + tue + wed + thu + fri + sat + sun;
}

function validate() {
	if (getTotal() > 96) {
		alert('Maximum allowed total is 96');
		return false;
	} else if (document.forms[0].elements[name].selectedIndex == 0) {
		alert('Please select department');
		return false;
	} else {
		return true;
	}
}
</script>
</head>

<body>
<form method="post" onSubmit="return validate()">
<table width="85%" border="1" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td>
		<table width="100%" border="0" align="center" cellpadding="4"
			cellspacing="0" bordercolor="#CCCCCC">
			<tr bgcolor="#C2DCEB" valign="middle">
				<td width="90%" height="60">
				<h1>Enter Hours</h1>
				</td>
				<td align="right" nowrap="nowrap"><a href="signout.htm">Sign
				out</a></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<center><spring:bind path="command.*">
					<c:if test="${not empty status.errorMessages}">
						<c:forEach var="error" items="${status.errorMessages}">
							<font color="red"><c:out value="${error}"
								escapeXml="false" /> </font>
							<br />
						</c:forEach>
					</c:if>
				</spring:bind> <!-- status messages --> <c:if test="${not empty message}">
					<font color="green"><c:out value="${message}" /></font>
					<c:set var="message" value="" scope="session" />
				</c:if></center>
				<p>Period Ending: <spring:bind path="command.periodEndingDate">
					<select name='<c:out value="${status.expression}"/>'>
						<c:forEach items="${periods}" var="period">
							<option
								value='<fmt:formatDate value="${period}" type="date"
								pattern="MMMM dd, yyyy" />'
								<c:if test="${period ge command.periodEndingDate}">selected</c:if>>
							<fmt:formatDate value="${period}" type="date"
								pattern="MMMM dd, yyyy" /></option>
						</c:forEach>
					</select>
				</spring:bind></p>
				<table border="1" align="center" cellpadding="8" cellspacing="0">
					<tr>
						<th>Deparment</th>
						<th>Mo</th>
						<th>Tu</th>
						<th>We</th>
						<th>Th</th>
						<th>Fr</th>
						<th>Sa</th>
						<th>Su</th>
						<th>Total</th>
					</tr>
					<tr>
						<td><spring:bind path="command.departmentCode">
							<select name='<c:out value="${status.expression}"/>'
								onChange='javascript:enabledButtons(this.name)'>
								<option value=""></option>
								<c:forEach items="${departments}" var="department">
									<option value='<c:out value="${department.departmentCode}"/>'
										<c:if test="${department.departmentCode == status.value}">SELECTED</c:if>>
									<c:out value="${department.name}" /></option>
								</c:forEach>
							</select>
						</spring:bind></td>
						<td><spring:bind path="command.minutesMon">
							<input name='<c:out value="${status.expression}"/>'
								value='<c:out value="${status.value}"/>' type="text" size="4"
								maxlength="6" onBlur="checkMaxVal(this.name)">
						</spring:bind></td>
						<td><spring:bind path="command.minutesTue">
							<input name='<c:out value="${status.expression}"/>'
								value='<c:out value="${status.value}"/>' type="text" size="4"
								maxlength="6" onBlur="checkMaxVal(this.name)">
						</spring:bind></td>
						<td><spring:bind path="command.minutesWed">
							<input name='<c:out value="${status.expression}"/>'
								value='<c:out value="${status.value}"/>' type="text" size="4"
								maxlength="6" onBlur="checkMaxVal(this.name)">
						</spring:bind></td>
						<td><spring:bind path="command.minutesThu">
							<input name='<c:out value="${status.expression}"/>'
								value='<c:out value="${status.value}"/>' type="text" size="4"
								maxlength="6" onBlur="checkMaxVal(this.name)">
						</spring:bind></td>
						<td><spring:bind path="command.minutesFri">
							<input name='<c:out value="${status.expression}"/>'
								value='<c:out value="${status.value}"/>' type="text" size="4"
								maxlength="6" onBlur="checkMaxVal(this.name)">
						</spring:bind></td>
						<td><spring:bind path="command.minutesSat">
							<input name='<c:out value="${status.expression}"/>'
								value='<c:out value="${status.value}"/>' type="text" size="4"
								maxlength="6" onBlur="checkMaxVal(this.name)">
						</spring:bind></td>
						<td><spring:bind path="command.minutesSun">
							<input name='<c:out value="${status.expression}"/>'
								value='<c:out value="${status.value}"/>' type="text" size="4"
								maxlength="6" onBlur="checkMaxVal(this.name)">
						</spring:bind></td>
						<td id="total"><spring:bind path="command.totalMinutes">
							<c:out value="${status.value}" />
						</spring:bind></td>
					</tr>
				</table>
				<input type="hidden" id="sendEmail" name="sendEmail" value="no" />
				<p align="center">
				<input name="save" id="save" type="submit" value="Save">
				<input name="saveAndEmail" id="saveAndEmail" type="submit" value="Submit" title="Submit and send an email to manager about timesheet"
					onClick="javascript:document.getElementById('sendEmail').value='yes';">
				<input name="cancel" type="button" value="Cancel"
					onClick="javascript:window.location='timesheetlist.htm'"> <br>
				<br>
				</p>
				</td>
			</tr>
			<tr>
				<td colspan="2" bgcolor="#C2DCEB">&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form>
</body>
</html>
