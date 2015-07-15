<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link REL="stylesheet"
	href="${pageContext.request.contextPath}/css/custom.css"
	type="text/css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Fitness Monitor System</title>
</head>
<body>
	<div class="outer">
		<div class="middle">
			<div class="inner">
				<div>
					<h1>Fitness Monitor System</h1>
				</div>
				<div>
					<a id="profile" class="profile"
						href="${pageContext.request.contextPath}/profile">+ My Profile</a>
					<br> <a id="activity" class="activity"
						href="${pageContext.request.contextPath}/newActivity">+ Add
						activity</a> <br> <a id="history" class="history"
						href="${pageContext.request.contextPath}/showHistory">+ Show
						History</a>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	var updated = "${savedActivity}";
	if (updated == "true")
		alert("Activity successfully updated.");
</script>
</html>