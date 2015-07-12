<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/core" prefix="c"%> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link REL="stylesheet"
	href="${pageContext.request.contextPath}/css/custom.css"
	type="text/css">
<script src="http://code.jquery.com/jquery-1.9.1.js"
	type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add New Activity</title>
</head>
<body>
	<form action="addActivity" method="POST">
		<div class="outer">
			<div class="middle">
				<div class="inner">
					<div>
						<h1>+ Add new activity</h1>
					</div>
					<div id="newActivityList" class="new-activity">
						+ Activity: <select name="newActivity" id="newActivity">
							<c:forEach items="${activityList}" var="activities"
								varStatus="act">
								<option>${activities}</option>
							</c:forEach>
						</select>
					</div>
					<div id="level" class="level">
						+ Difficulty Level: <input type="radio" name="level" value="low"
							checked> Low <input type="radio" name="level"
							value="medium"> Medium <input type="radio" name="level"
							value="high"> High
					</div>
					<div class="measurement-type">
						<label for="type">+ Choose type: </label> <select name="measType"
							id="measType">
							<option>Choose type</option>
							<option value="distance">Distance</option>
							<option value="time">Time</option>
						</select>
						<div id="exerciceTypeDistance" class="sub-measurement-type"
							style="display: none;">
							<label for="specify">+ Miles: </label> <input type="text"
								name="specifiedMiles" placeholder="Specify miles" />
						</div>
						<div id="exerciceTypeTime" class="sub-measurement-type"
							style="display: none;">
							<label for="specify">+ Time : </label> <input type="text"
								name="specifiedTime" placeholder="Specify time" />
						</div>
					</div>
					<input class="submitButton" type="submit" value="Submit"> <input
						id="cancel" class="submitButton" type="button" value="Cancel">
				</div>
			</div>
		</div>
	</form>
</body>
<script type="text/javascript">
	$('#measType').change(function() {
		selection = $(this).val();
		switch (selection) {
		case 'distance':
			$('#exerciceTypeTime').hide();
			$('#exerciceTypeDistance').show();
			break;
		case 'time':
			$('#exerciceTypeDistance').hide();
			$('#exerciceTypeTime').show();
			break;
		default:
			$('#exerciceTypeDistance').hide();
			$('#exerciceTypeTime').hide();
			break;
		}
	});

	$('#newActivity').change(function() {
		selection = $(this).val();
		switch (selection) {
		case 'Swimming':
			$('#level').hide();
			break;
		default:
			$('#level').show();
			break;
		}
	});
	$('#cancel').click(function() {
		window.location.href = "${pageContext.request.contextPath}/";
	});
</script>
</html>