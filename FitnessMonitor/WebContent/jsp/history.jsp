<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link REL="stylesheet" href="${pageContext.request.contextPath}/css/custom.css" type="text/css">
<script src="http://code.jquery.com/jquery-1.9.1.js" type="text/javascript"></script>
<script src="http://code.highcharts.com/highcharts.js" type="text/javascript"></script>
<script src="http://code.highcharts.com/modules/exporting.js" type="text/javascript"></script>
<script type="text/javascript">$(function () {
    $('#container').highcharts({
        title: {
            text: 'Calories Burnt Over One Week',
            x: -20 //center
        },
        subtitle: {
            text: 'Source: Mustard Flower Fitness Tracker',
            x: -20
        },
        xAxis: {
            categories: ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat']
        },
        yAxis: {
            title: {
                text: 'Calories'
            },
            plotLines: [{
                value: 0,
                width: 1,
                color: '#808080'
            }]
        },
        tooltip: {
            valueSuffix: 'Cal'
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'middle',
            borderWidth: 0
        },
        series: [{
            name: 'Running',
            data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2]
        }, {
            name: 'Cycling',
            data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8]
        }, {
            name: 'Swiming',
            data: [-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6]
        }, {
            name: 'Walking',
            data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0]
        }]
    });
});
</script>
<title>Fitness Monitor System</title>
</head>
<body>
	<div class="outer">
		<div class="middle">
			<div>
				<h1>Progress Report</h1>
			</div>
			<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
		</div>
	</div>
</body>
</html>