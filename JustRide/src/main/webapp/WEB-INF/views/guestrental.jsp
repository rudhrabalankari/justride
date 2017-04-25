<!DOCTYPE html>
<html lang="en">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Booking Info</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel=stylesheet
	href="http://localhost:8080/justride/resources/css/BookingInfo2.css">



<script>
	function goBack() {
		window.history.back();
	}
</script>
</head>

<body>

	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a href="#" class="navbar-brand" style="font-size: 40px">JustRide</a>
			</div>
			<ul class="nav navbar-nav navbar-right">
				<strong>Hello Guest</strong>


				<a href="#" button type="submit" class="btn btn-primary"
					style="margin: 13px 12px 12px 10px;">LogIn</a>
			</ul>
		</div>
	</nav>

	<div class="container" id="titleHeader">
		<h2>
			<img
				src="http://localhost:8080/justride/resources/images/JustRideLogo.png"
				id="logo"> BOOKING CONFIRMATION
		</h2>
	</div>


	<div class="container text-center" id="mainCon">
		<div class="row">
			<div class="col-sm-10 col-sm-offset-0 well" id="mainCol">
				<div class="panel panel-primary">
					<div class="panel-heading" align="left">CAR DETAILS</div>
					<div class="panel-body">
						<div class="container">
							<div class="row">
								<div class="col-sm-4">
									<img src="${car.imgName}" class="img-responsive left"
										style="width: 100%">
								</div>
								<div class="col-sm-5">
									<ul class="type" style="height: 20;">
										<li
											style="font-size: 15px; padding: 0px; float: left; font-weight: bold;">FEATURES</li>
										<br>
										<li class="icon"><span class="glyphicon glyphicon-user"
											id="span01"
											style="padding: 0px; font-size: 17px; word-spacing: -10px;">
												${car.seats} Seats</span></li>
										<li><span class="glyphicon glyphicon-ok" id="span01"
											style="padding: 0px; font-size: 17px; word-spacing: -10px;">
												AC</span></li>
									</ul>
								</div>
							</div>
						</div>

						<div class="container-fluid" id="C-table">
							<table class="table" border="0">
								<tr>
									<td><strong>START DATE: ${startDate}</strong></td>
									<td><strong>PICKUP TIME: ${startTime}</strong></td>
								</tr>
								<tr>
									<td><strong>END DATE: ${endDate}</strong></td>
									<td><strong>DROPOFF TIME: ${endTime}</strong></td>
								</tr>
								<tr>
									<td><strong>PICKUP LOCATION: ${car.location}</strong></td>
								</tr>
								<tr>
									<td><strong>DROP OFF LOCATION: ${car.location}</strong></td>
								</tr>
							</table>
						</div>

					</div>

					<div class="panel-footer" align="right">
						<h3>
							<span class="label label-info">PRICE:${totalPrice} $</span>
						</h3>
					</div>

				</div>

				<div class="panel panel-primary">
					<div class="panel-heading" align="left">Please Login to
						reserve a car. Thank You!</div>


				</div>


				<button type="submit" class="btn btn-info pull-right"
					onclick="goBack()">Back</button>
			</div>
		</div>
	</div>

</body>

</html>