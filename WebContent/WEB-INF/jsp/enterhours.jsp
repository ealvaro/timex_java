<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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

	function validateTotalHours() {
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

		<jsp:include flush="false" page="../../header.jsp"/>
		
	<div id="page">
		<div align="center">
			<form method="post" onSubmit="javascript:return validateTotalHours();">
				<table width="85%" border="1" align="center" cellpadding="0"
					cellspacing="0">
					<tr>
						<td>
							<table width="100%" border="0" align="center" cellpadding="60"
								cellspacing="5">
								<tr valign="middle">
									<td width="90%" height="60">
										<h1>Enter Hours</h1> <br /></td>
									<td align="right" nowrap="nowrap"><a href="signout.htm">Sign
											out</a>
									</td>
								</tr>
								<tr>
									<td colspan="2" align="center"><center> <spring:bind
											path="command.*">
											<c:if test="${not empty status.errorMessages}">
												<c:forEach var="error" items="${status.errorMessages}">
													<font color="red"> <c:out value="${error}"
														escapeXml="false" /> </font>
													<br />
												</c:forEach>
											</c:if>
										</spring:bind> <!-- status messages --> <c:if test="${not empty message}">
											<font color="green"> <c:out value="${message}" /></font>
											<c:set var="message" value="" scope="session" />
										</c:if></center>
										<p>
											Employee Name:
											<c:out value="${command.employee.name}"/>
											Period Ending:
											<fmt:formatDate value="${command.periodEndingDate}"
												type="date" pattern="MMMM dd, yyyy" />
										</p></td>
								</tr>
								<tr>
									<td colspan="2" align="center">
										<table align="center" cellpadding="60" cellspacing="10">
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
												<td><spring:bind path="command.department_id">
														<select name='<c:out value="${status.expression}"/>'
															onChange='javascript:enabledButtons(this.name)'>
															<option value=""></option>
															<c:forEach items="${departments}" var="department">
																<option
																	value='<c:out value="${department.id}"/>'
																	<c:if test="${department.id == status.value}">SELECTED</c:if>>
																	<c:out value="${department.name}" />
																</option>
															</c:forEach>
														</select>
													</spring:bind>
												</td>
												<td><spring:bind path="command.minutesMon">
														<input name='<c:out value="${status.expression}"/>'
															value='<c:out value="${status.value}"/>' type="text"
															size="4" maxlength="6" onBlur="checkMaxVal(this.name)">
													</spring:bind>
												</td>
												<td><spring:bind path="command.minutesTue">
														<input name='<c:out value="${status.expression}"/>'
															value='<c:out value="${status.value}"/>' type="text"
															size="4" maxlength="6" onBlur="checkMaxVal(this.name)">
													</spring:bind>
												</td>
												<td><spring:bind path="command.minutesWed">
														<input name='<c:out value="${status.expression}"/>'
															value='<c:out value="${status.value}"/>' type="text"
															size="4" maxlength="6" onBlur="checkMaxVal(this.name)">
													</spring:bind>
												</td>
												<td><spring:bind path="command.minutesThu">
														<input name='<c:out value="${status.expression}"/>'
															value='<c:out value="${status.value}"/>' type="text"
															size="4" maxlength="6" onBlur="checkMaxVal(this.name)">
													</spring:bind>
												</td>
												<td><spring:bind path="command.minutesFri">
														<input name='<c:out value="${status.expression}"/>'
															value='<c:out value="${status.value}"/>' type="text"
															size="4" maxlength="6" onBlur="checkMaxVal(this.name)">
													</spring:bind>
												</td>
												<td><spring:bind path="command.minutesSat">
														<input name='<c:out value="${status.expression}"/>'
															value='<c:out value="${status.value}"/>' type="text"
															size="4" maxlength="6" onBlur="checkMaxVal(this.name)">
													</spring:bind>
												</td>
												<td><spring:bind path="command.minutesSun">
														<input name='<c:out value="${status.expression}"/>'
															value='<c:out value="${status.value}"/>' type="text"
															size="4" maxlength="6" onBlur="checkMaxVal(this.name)">
													</spring:bind>
												</td>
												<td id="total"><spring:bind path="command.totalMinutes">
														<c:out value="${status.value}" />
													</spring:bind>
												</td>
											</tr>
										</table> <input type="hidden" id="sendEmail" name="sendEmail" value="no" /><br></br>
										<p align="center">
											<input name="save" id="save" type="submit" value="Save">
											<input name="saveAndEmail" id="saveAndEmail" type="submit" value="Submit"
												title="Submit and send an email to manager about timesheet" onClick="javascript:document.getElementById('sendEmail').value='yes';">
											<input name="cancel" type="button" value="Cancel" onClick="javascript:window.location='timesheetlist.htm'">
														<br> <br>
										</p></td>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
								<tr>
									<td colspan="2">&nbsp;</td>
								</tr>
							</table></td>
					</tr>
				</table>
			</form>
		</div>
		<!-- end #content -->
		
		<div style="clear: both; height: 1px;"></div>
	</div>
<!-- end #page -->

		<jsp:include page="../../footer.jsp"/>
		
</body>
</html>
