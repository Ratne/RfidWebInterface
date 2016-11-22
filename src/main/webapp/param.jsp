
<!DOCTYPE html">
<html>
	<head>
		<meta charset="utf-8">
		<title>RFid</title>
		<meta name="author" content="madrojudi">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	    <link href="css/bootstrap.css" rel="stylesheet">
	    <link href="css/bootstrap.min.css" rel="stylesheet">
		<link rel="stylesheet" href="css/style.css" />		
	</head>
<body>
	<header>
		<div class="row">						
				<div class="col-md-12">
						<nav class="navbar navbar-inverse	">
							<div class="navbar-form pull-right">
								<a href="/Rfid3/">
									<button  class="btn btn-primary btn-sm">
										Home
									</button>
								</a>
							</div> 									
						</nav>
					</div>														
			</div>	
	</header>
	<div class ="container ">	
		<div class="panel panel-info" id="conn">
			<br>
			<form action="/Rfid3/param" method="post">
				<div class="row">
					<label class="col-md-2 col-md-offset-1" >Address :</label> <input class="col-md-3" type="text" name="address" value = "${address }"  />
				</div>
				<div class="row">
					<label class="col-md-2 col-md-offset-1" >Port : </label><input class="col-md-3" type="text" name="port" value = "${port }"/>
				</div>
				<div class="row">
					<label class="col-md-2 col-md-offset-1" >Address IP : </label><input class="col-md-3" type="text" name="addressIp" value = "${addressIp }"/>
				</div>
				<div class="row">
					<label class="col-md-2 col-md-offset-1" >PassWord : </label><input class="col-md-3" type="text" name="password" value = "${password }"/>
				</div>
				<div class="row">
					<input class="btn btn-primary col-md-offset-10" type="submit" value="Save" name="action"/>
				</div>
			</form>
			<br>
		</div>
	</div>
</body>
</html>