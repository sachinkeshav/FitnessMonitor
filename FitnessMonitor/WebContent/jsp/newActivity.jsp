<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link REL="stylesheet"
	href="${pageContext.request.contextPath}/css/custom.css"
	type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add New Activity</title>
</head>
<body>
	<div class="outer">
		<div class="middle">
			<div class="inner">
				<div>
					<h1>+ Add new activity</h1>
				</div>
				<!-- <input name="newActivity" id="newActivity" type="text" size="20"> -->
				<select name="newActivity" id="newActivity">
					<c:forEach items="${activityList}" var="activities" varStatus="act">
						<option value="${act.index}">${activities}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>
</body>
</html>