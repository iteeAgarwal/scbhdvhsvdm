<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>

<%@ page import="java.sql.*"%>



<%@ page import="org.dqms.db.Department"%>

<%@ page import="com.dan.dqms.dcu.DocSummaryBean"%>
<%
	if (session.getAttribute("user") != null) {
%>
<%
	response.setHeader("Cache-Control",
		"no-cache, no-store, must-revalidate");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Expires", 0);
%>

<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Cache-Control"
	content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Report</title>
<style type="text/css">
.custom-date-style {
	background-color: red !important;
}
.table-striped tbody tr:nth-child(odd) td {
    background-color: #81DAF5;
}

.table-striped tbody tr:nth-child(even) td {
    background-color: #CEECF5;
}

.table-striped tbody tr.highlight td {
    background-color: #F2F5A9;
}
.table-striped tbody tr:first-child td {
    background-color: #007E9C;
}

</style>
<link rel="icon" href="./images_dqms/title.gif" type="image/gif" />
<link rel="stylesheet" href="css_dqms/style.css" type="text/css">
<link href="css_dqms/page.css" type="text/css" rel="stylesheet" />

<link rel="stylesheet" type="text/css"
	href="css_dqms/jquery.datetimepicker.css" />




				<script>
				function setDates(){
				<%
				if(request.getParameter("from")==null || request.getParameter("from").equals(""))
				{
				%>
			 	var today = new Date();
			 	var yyyy = today.getFullYear();
			 	var mm = today.getMonth()+1;
			 	var dd = today.getDate();
			    if(dd<10){
			        dd='0'+dd
			    } 
			    if(mm<10){
			        mm='0'+mm
			    }  
			    var f = yyyy+'/'+mm+'/'+dd+' 00:00';
			    var e1 = document.getElementById("datetimepicker_mask");
			    e1.value = f;
			    var t = yyyy+'/'+mm+'/'+dd+' 23:00';
			    var e2 = document.getElementById("datetimepicker_mask1");
			    e2.value = t;
			    <%
			    }
				else{
					%>
					abc();
					<%
				}
			    %>
				}
				function abc(){
					var node = document.createElement("i");
					var e = document.getElementById("deptID");
					var strUser = e.options[e.selectedIndex].text;
					if(strUser=='select department'){
						strUser='All Tokens';
						document.getElementById("deptID").selectedIndex = "1";
					}
					var frDt = document.getElementById("datetimepicker_mask").value;
					var toDt = document.getElementById("datetimepicker_mask1").value;
				    var textnode = document.createTextNode('Department: '+strUser+' From: '+frDt+' To: '+toDt);
				    node.appendChild(textnode);
				    document.getElementById("R_SUM").appendChild(node);
				}
				</script>
<script src="js_dqms/jquery.min.js" type="text/javascript"></script>
<script>
$(document).ready(function(){
	$('#IP_tableTH').on('click', 'tbody tr', function(event) {
	    $(this).addClass('highlight').siblings().removeClass('highlight');
	});
});
</script>
</head>
<body onload='setDates();initTable("IP_tableTH");'>
<script src="js_dqms/sortTable.js" type="text/javascript" ></script>
<%!String from="";%>
<%!String to="";%>

<%
from=request.getParameter("from");
to=request.getParameter("to");

%>



	<div id="header">
		<div id="headerlogo">

			<div id="headerleft1">
				<%@include file="topmenu.jsp"%>

			</div>





		</div>
	</div>
	<div id="container">


		<!--  report table start -->
		<div id="report_table1">

			<TABLE BORDER="0" WIDTH="100%">

				<TH COLSPAN="5"
					style="background-color: #007E9C; height: 65px; color: #FFFFFF; font: normal 16px arial;">

					<b id="aa">Department Wise Report</b><div id="R_SUM"></div>

				</TH>
			</TABLE>


			<div id="cont_shadow">



				<form name="doctorForm" method="post" action="#">

					<table>
						<tr>
							<td>Select Department:</td>
							<td><select class="dropdown_conn_class" id="deptID"
								name="depart" required>
									<option value="">select department</option>
									<option value="-1">All Tokens</option>

									<%
										@SuppressWarnings("unchecked")
															List<Department> deptList = (ArrayList<Department>) request	.getAttribute("deptList");

													String options = request.getParameter("depart");

															if (deptList.size() > 0) {

																		for (Department listdept : deptList) {
																	if (options != null) {
																																																																													if (Integer.parseInt(options) == listdept
																																																																															.getDepart_id()) {
									%>
									<option value="<%=listdept.getDepart_id()%>"
										selected="selected"><%=listdept.getDepart_name()%></option>

									<%
										} else {
									%>

									<option value="<%=listdept.getDepart_id()%>"><%=listdept.getDepart_name()%></option>

									<%
										}

																																																																					} else {
																																																																						//else add it as it is.
									%>
									<option value="<%=listdept.getDepart_id()%>"><%=listdept.getDepart_name()%></option>
									<%
										}
																																																																				}
																																																																			}
									%>



							</select></td>
							<td>From</td>
							<td><input type="text" value="" name="fromDate"
								id="datetimepicker_mask" required /></td>
							<td>To</td>
							<td><input type="text" value="" name="toDate"
								id="datetimepicker_mask1" required /></td>

							<td><input type="submit" name="tokenStatus" value="Submit"
								id="formButton"></td>
						</tr>

					</table>
				</form>
				
				<div id="status">
					<table id="IP_tableTH" width="100%" align="center" 	color:#3b485f;="" line-height:1.6em;=""
						border-collapse:collapse;="" class="table-striped">
						<tbody>
							<tr	style="background: #007E9C; height: 40px; font-size: 16px; font-family: Arial, Helvetica, sans-serif;color:#ffffff;text-align: center;font-weight:bold;">

								<th  scope="col">Department Name</th>

								<th   scope="col">Total Patient</th>


								<th scope="col">Patient Treated</th>

								<th  scope="col">Patient Skipped</th>

								<th  scope="col">Patient Cancelled</th>

							</tr>

							<%
								@SuppressWarnings("unchecked")
																													ArrayList<DocSummaryBean> allDeptReport = (ArrayList<DocSummaryBean>) request
																										.getAttribute("allDeptReport");
																												if (allDeptReport.size() > 0) {

																									for (DocSummaryBean listDeptReport : allDeptReport) {
							%>
							<tr
								style="height: 25px; font-size: 14px; text-align: center"">

								<td><%=listDeptReport.getDeptName()%></td>
								<td><%=listDeptReport.getTotalPatients()%></td>
								<td><%=listDeptReport.getTotalTreat()%></td>
								<td><%=listDeptReport.getTotalSkip()%></td>
								<td><%=listDeptReport.getTotalCancel()%></td>

							</tr>




							<%
								}
																												}
																									else if(allDeptReport==null)
																									{
																										%>
																										<tr>

								<td></td>
								<td></td>


								<td ></td>
							</tr>
																										
																								<% 	}
																																																						 
																												
																												else {
							%>

							<tr>

								<td></td>
								<td></td>


								<td id="response_mess" align="center"><%="Token status list not found !!"%></td>
							</tr>





							<%
								}
							%>



						</tbody>
					</table>
					<table align="center">
					<tr>
					<td>
					<%-- Using JSP EL to get message attribute value from request scope --%>
										<p id="response_mess">${requestScope.message}</p>
					</td>
					</tr>
					
					</table>



				</div>






			</div>
		</div>
	</div>
	<%@include file="footer_pg.jsp"%>

<% 
if((from!=null && !from.equals("")) && (to!=null && !to.equals(""))){
%>
<script type="text/javascript">
var elem1 = document.getElementById("datetimepicker_mask");
var elem2 = document.getElementById("datetimepicker_mask1");
elem1.value = '<%=from%>';
elem2.value = '<%=to%>';
</script>
<%	
}
%>


</body>


<script src="js_dqms/jquery.js"></script>
<script src="js_dqms/jquery.datetimepicker.js"></script>
<script>
	$('#datetimepicker_mask').datetimepicker({
		mask : '9999/19/39 29:59'
	});

	$('#datetimepicker_mask1').datetimepicker({
		mask : '9999/19/39 29:59'
	});
</script>







<head>
<meta http-equiv="Cache-Control"
	content="no-cache, no-store, must-revalidate" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
</head>
</html>
<%
	} else {
		response.sendRedirect("login_pg.jsp");
	}
%>