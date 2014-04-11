<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
									<h1>Report: Overall Summary</h1> <br />
								</td>
								<td align="right" nowrap="nowrap"><a href="signout.htm">Sign
										out</a>
								</td>
							</tr>
							<tr>
								<td colspan="2"><br>
									<table border="0" align="center" cellpadding="8"
										cellspacing="20">
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
													<div align="left" class="style18">
														<c:out value="${summary.manager.name}" />
													</div>
												</td>
												<td nowrap>
													<div align="right" class="style18">
														<fmt:formatNumber value="${summary.totalMinutes / 60}"
															pattern="0.00" />
														<c:set var="incrementor"
															value="${incrementor + (summary.totalMinutes / 60)}" />
													</div>
												</td>
												<td nowrap><span class="style18"><c:out
															value="${summary.paid}" /> paid<br> <c:out
															value="${summary.unpaid}" /> unpaid<br> <c:out
															value="${summary.approved}" /> approved<br> <c:out
															value="${summary.disapproved}" /> disapproved</span></td>
											</tr>
										</c:forEach>
										<tr bgcolor="#CCCCCC">
											<td scope="row">
												<div align="right" class="style18">Average Hours</div>
											</td>
											<td nowrap>
												<div align="right" class="style18">
													<fmt:formatNumber value="${incrementor / command.size}"
														pattern="0.00" />
												</div>
											</td>
											<td nowrap>&nbsp;</td>
										</tr>
										<tr bgcolor="#CCCCCC">
											<td scope="row">
												<div align="right" class="style18">
													<strong>TOTAL</strong>
												</div>
											</td>
											<td nowrap>
												<div align="right" class="style18">
													<strong><c:out value="${incrementor}" /> </strong>
												</div>
											</td>
											<td nowrap>&nbsp;</td>
										</tr>
									</table>
							<p align="center">
								&nbsp;&nbsp; <input name="submit" type="button" id="submit"
									value="Print" onClick="window.print()"> &nbsp; <input
									name="reset" type="reset" id="reset" value="Cancel"
									onClick="javascript:window.location='timesheetlist.htm'">
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
		</div>
		<!-- end #content -->

		<jsp:include page="../../sidebar_home.jsp"/>
		
		<div style="clear: both; height: 1px;"></div>
	</div>
<!-- end #page -->

		<jsp:include page="../../footer.jsp"/>
		
</body>
</html>
</body>
</html>
