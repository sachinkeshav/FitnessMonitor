<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>My Profile</title>
</head>
<body>
	<div class="outer">
		<div class="middle">
			<div class="inner">
				<div>
					<h1>My Profile</h1>
				</div>
				<div>
					<form action="profile" method="POST">
						<div class="profile-separator">
							+ Name: <span class="name-separator">First:</span> <input
								type="text" name="firstName"
								value="<c:out value="${profile.firstName}"></c:out>"> <span
								class="name-separator">Last:</span> <input type="text"
								name="lastName"
								value="<c:out value="${profile.lastName}"></c:out>">
						</div>
						<div class="profile-separator">
							+ Dob: <input class="profile-dob" type="text" name="dob"
								value="<c:out value="${profile.dob}"></c:out>">
						</div>
						<div class="profile-separator">
							+ Gender: <input class="profile-gender" type="text" name="gender"
								value="<c:out value="${profile.gender}"></c:out>">
						</div>
						<div class="profile-separator">
							+ Height: <input class="profile-height" type="text" name="height"
								value="<c:out value="${profile.height}"></c:out>">
						</div>
						<div class="profile-separator">
							+ Weight: <input class="profile-height" type="text" name="weight"
								value="<c:out value="${profile.weight}"></c:out>">
						</div>
						<input class="submitButton" type="submit" value="Submit">
						<input id="cancel" class="submitButton" type="button"
							value="Cancel">
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$('#cancel').click(function() {
		window.location.href = "${pageContext.request.contextPath}/";
	});
</script>
</html>