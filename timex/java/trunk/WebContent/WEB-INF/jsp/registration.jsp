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
			function enabledButtons(name1,name2,name3) {
				if (document.forms[0].elements[name1].selectedIndex == 0 ||
					document.forms[0].elements[name2].selectedIndex == 0 ||
					document.forms[0].elements[name3].selectedIndex == 0) {
					document.getElementById('register').disabled = true;
				} else {
					if (document.forms[0].elements[name1].value == 'M'){
						document.getElementById('payrate').setAttribute("value", "100000");
						document.getElementById('taxrate').setAttribute("value", "30.00");
					}
					document.getElementById('register').disabled = false;
				}
			}

			function validate() {
				if (document.forms[0].elements['username'].value == "") {
					alert('Must input a User Name');
					return false;
				} else if (document.forms[0].elements['password'].value != document.forms[0].elements['reenterpassword'].value) {
					alert('Password and Re-enter Password must be the same');
					return false;
				} else if (document.forms[0].elements['employeeType'].selectedIndex == 0) {
					alert('Please select your Employee Role');
					return false;
				} else if (document.forms[0].elements['manager'].selectedIndex == 0) {
					alert('Please select your Manager name');
					return false;
				} else if (document.forms[0].elements['state'].selectedIndex == 0) {
					alert('Please select State');
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
		<div id="content">
			<form method="post" onSubmit="javascript:return validate();" action="registration.htm" enctype="multipart/form-data">
				<table width="85%" cellspacing="0" cellpadding="0" border="1"
					align="center">
					<tbody>
						<tr>
							<td>
								<table width="100%" cellspacing="5" cellpadding="60" border="0"
									align="center">
									<tbody>
										<tr valign="middle">
											<td width="90%" valign="middle" height="60">
												<h1 class="title">Registration</h1> <br>
											</td>
											<td nowrap="nowrap" align="right"></td>
										</tr>
										<tr>
											<td colspan="2" align="center"><center> <spring:bind path="command.*">
													<c:if test="${not empty status.errorMessages}">
														<c:forEach var="error" items="${status.errorMessages}">
															<font color="red"> <c:out value="${error}"
																escapeXml="false" /> </font>
															<br />
														</c:forEach>
													</c:if>
												</spring:bind>
												 <!-- status messages --> <c:if test="${not empty message}">
													<font color="green"> <c:out value="${message}" /></font>
													<c:set var="message" value="" scope="session" />
												</c:if></center>
											</td>
										</tr>
										<tr>
											<td align="center" colspan="2">
												<p>Please provide the following information:</p>
											</td>
										</tr>
										<tr>
											<td align="center" colspan="2">
												<table cellspacing="10" cellpadding="20" align="center">
													<tbody>
														<tr>
															<td>Employee Id :</td>
															<td><spring:bind path="command.username">
															<input type="text" value="" maxlength="11"
																size="11" id="username" name="username">
																	xxx-xx-xxxx
															</input></spring:bind>
															</td>
														</tr>
														<tr>
															<td>Employee Full Name :</td>
															<td><spring:bind path="command.name">
															<input type="text" value="" maxlength="30"
																size="30" id="name" name="name">
																	firstname lastname 
															</input></spring:bind></td>

														</tr>
														<tr>
															<td>Password :</td>
															<td><spring:bind path="command.password">
															<input type="password" maxlength="15" size="15"
																id="password" name="password" />
																</spring:bind>
															</td>
														</tr>
														<tr>
															<td>Re-enter Password :</td>
															<td><input type="password" maxlength="15" size="15"
																id="reenterpassword" name="reenterpassword">
															</td>
														</tr>
														<tr>
															<td>Email :</td>
															<td><spring:bind path="command.email">
															<input type="text" value="" maxlength="25"
																size="25" id="email" name="email">
																	name@domain.com 
															</input></spring:bind>
															</td>

														</tr>
														<tr>
															<td>Employee Type :</td>
															<td><spring:bind path="command.employeeType">
															<select
																onchange="javascript:enabledButtons('employeetype','managerEmployeeId', 'state')"
																id="employeetype" name="employeetype">
																	<option selected="" value=""></option>
																	<option value="H">Hourly</option>
																	<option value="M">Manager</option>
																	<option value="E">Executive</option>
																	<option value="A">Administrative</option>
															</select></spring:bind>
															</td>
														</tr>
														<tr>
															<td>Manager :</td>
															<td><spring:bind path="command.managerEmployeeId">
															<select
																onchange="javascript:enabledButtons('employeetype','managerEmployeeId', 'state')"
																id="managerEmployeeId" name="managerEmployeeId">
																	<option selected="" value=""></option>
																	<option value="3">Teresa Walker</option>
																	<option value="4">Tom Brady</option>
																	<option value="5">Alvaro Escobar</option>
															</select></spring:bind>
															</td>
														</tr>
													</tbody>
												</table>
												<table cellspacing="10" cellpadding="20" align="center">
													<tbody>
														<tr>
															<td colspan="6">
																<hr>
															</td>
														</tr>
														<tr>
															<td colspan="3">Picture :</td>
															<td colspan="3">
															<input type="file" name="file" maxlength="75" size="55" id="file" />
															</td>
														</tr>

														<tr>
															<td colspan="3">Address :</td>
															<td colspan="3"><spring:bind path="command.address">
															<input type="text" value=""
																maxlength="40" size="40" id="address" name="address" />
															</spring:bind>
															</td>
														</tr>

														<tr>
															<td colspan="3">City :</td>
															<td colspan="2"><spring:bind path="command.city">
															<input type="text" value=""
																maxlength="20" size="20" id="city" name="city" />
															</spring:bind>
															</td>
														</tr>

														<tr>
															<td colspan="3">State :</td>
															<td><spring:bind path="command.state">
															<select
																onchange="javascript:enabledButtons('employeetype','managerEmployeeId', 'state')"
																id="state" name="state">
																	<option selected="" value=""></option>
																	<option value="FL">Florida</option>
																	<option value="GA">Georgia</option>
																	<option value="NY">New York</option>
																	<option value="CA">California</option>
															</select>
															</spring:bind></td>
															<td>Zipcode :</td>
															<td><spring:bind path="command.zipcode">
															<input type="text" value="" maxlength="5"
																size="5" id="zipcode" name="zipcode" />
															</spring:bind>
															</td>
														</tr>
													</tbody>
												</table> <br> <br>
														<p align="center">
														<input type="hidden" id="payrate" name="payrate" value="15.00" />
														<input type="hidden" id="taxrate" name="taxrate" value="15.00" />
														<input type="submit" id="register" name="register" disabled="" value="Register" />
														 <br> <br>
														</p>
											</td>
										</tr>
									</tbody>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
		<!-- end #content -->

		<jsp:include page="../../sidebar_home.jsp"/>
		
		<div style="clear: both; height: 1px;"></div>
	</div>
<!-- end #page -->

		<jsp:include page="../../footer.jsp"/>
		
</body>
</html>
