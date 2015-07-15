<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link REL="stylesheet"
	href="${pageContext.request.contextPath}/css/custom.css"
	type="text/css">
<script src="http://code.jquery.com/jquery-1.9.1.js"
	type="text/javascript"></script>
<script src="http://code.highcharts.com/highcharts.js"
	type="text/javascript"></script>
<script src="http://code.highcharts.com/modules/exporting.js"
	type="text/javascript"></script>

<script type="text/javascript">
	var date = "${date}".replace("[", "").replace("]", "").split(", ");
	var weight = "${weight}".replace("[", "").replace("]", "").split(", ");
	var treadMill = "${treadMill}".replace("[", "").replace("]", "").split(", ");
	var dumbBells = "${dumbBells}".replace("[", "").replace("]", "").split(", ");
	var cycling = "${cycling}".replace("[", "").replace("]", "").split(", ");
	var pullUp = "${pullUp}".replace("[", "").replace("]", "").split(", ");
	var benchPress = "${benchPress}".replace("[", "").replace("]", "").split(", ");
	var total = "${total}".replace("[", "").replace("]", "").split(", ");

	$(function() {
		var calorieChart;
		$(document).ready(function() {
			calorieChart = new Highcharts.Chart({
				chart : {
					renderTo : 'calorieBurnt',
					type : 'line'
				},
				title : {
					text : 'Calories Burnt Over One Week',
					x : -20
				//center
				},
				subtitle : {
					text : 'Source: Fitness Monitor',
					x : -20
				},
				xAxis : {
					categories : date
				},
				yAxis : {
					title : {
						text : 'Calories'
					},
					plotLines : [ {
						value : 0,
						width : 1,
						color : '#808080'
					} ]
				},
				tooltip : {
					valueSuffix : ' Cal'
				},
				legend : {
					layout : 'vertical',
					align : 'right',
					verticalAlign : 'middle',
					borderWidth : 0
				},
				series : [ {
					name : 'Tread Mill',
					data : JSON.parse("[" + treadMill + "]")

				}, {
					name : 'Dumb Bells',
					data : JSON.parse("[" + dumbBells + "]")
				}, {
					name : 'Cycling',
					data : JSON.parse("[" + cycling + "]")
				}, {
					name : 'Pullups',
					data : JSON.parse("[" + pullUp + "]")
				}, {
					name : 'Bench Press',
					data : JSON.parse("[" + benchPress + "]")
				}, {
					name : 'Total',
					data : JSON.parse("[" + total + "]")
				} ]
			});
		});
	});

	$(function() {
		var calorieChart;
		$(document).ready(function() {
			calorieChart = new Highcharts.Chart({
				chart : {
					renderTo : 'weightProgress',
					type : 'line'
				},
				title : {
					text : 'Weight Progress Over One Week',
					x : -20
				//center
				},
				subtitle : {
					text : 'Source: Fitness Monitor',
					x : -20
				},
				xAxis : {
					categories : date
				},
				yAxis : {
					title : {
						text : 'Weight'
					},
					plotLines : [ {
						value : 0,
						width : 1,
						color : '#808080'
					} ]
				},
				tooltip : {
					valueSuffix : ' Pound'
				},
				legend : {
					layout : 'vertical',
					align : 'right',
					verticalAlign : 'middle',
					borderWidth : 0
				},
				series : [ {
					name : 'Weight',
					data : JSON.parse("[" + weight + "]")

				} ]
			});
		});
	});
</script>

<title>Fitness Monitor System</title>
</head>
<body>
	<div class="outer">
		<div class="middle">
			<h1 style="margin-top: 3cm; cursor: pointer"
				onclick="window.location='${pageContext.request.contextPath}/'">Fitness
				Monitor System</h1>
			<h2 style="margin-top: 1cm">Progress Report</h2>
			<div id="calorieBurnt"
				style="min-width: 310px; height: 400px; margin: 0 auto; margin-top: 1cm"></div>

			<div class="spacer"></div>

			<div id="weightProgress"
				style="min-width: 310px; height: 400px; margin: 0 auto; margin-top: 1cm"></div>
		</div>
	</div>
</body>
</html>