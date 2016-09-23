<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
	<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="http://maxcdn.bootstrapcdn.com/bootstrap//js/bootstrap.min.js"></script>
</head>


<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
		<a href="/" class="navbar-brand"><i class="fa fa-home"></a>
		</div>
		<ul class="nav navbar-nav">
			<li><a href="/">About us</a></li>
			<li><a href="/tables">Book a table</a></li>
			<li><a href="/employees">Our team</a></li>
			<li><a href="/contact">Contact</a></li>
		</ul>

		<ul class="nav navbar-nav navbar-right">
			<li><button type="button" class="btn btn-default navbar-btn" onclick="location.href='/login'">Sign in</button>
		</ul>

	</div>
</nav>
