<!DOCTYPE html>
<html>
	<head>
		<title>Stock</title>
		<link rel="stylesheet" type="text/css" href="css/index.css">
		<link rel="stylesheet" type="text/css" href="css/template.css">
		<script src="js/stock.js"></script>
		<script src="js/template.js"></script>
	</head>
	<body>
		<div id="top-bar">
			<nav>
				<ul>
					<li><a href="index.html"> Home </a></li>
					<li><a href="stock.html"> Stock </a></li>
					<li><a href="reference.php"> Market </a></li>
					<li><a href="logout.php"> Contact </a></li>
				</ul>
			</nav>
		</div>	
		<div id = "side-navagate-bar">
			<div class = "toggle-btn" onclick="toggleSideBar()">
			<div class = "icon">
				<span></span>
				<span></span>
				<span></span>	
			</div>
			<div class = "mssg">
				<p>STOCK LEARNING MADE EASY</p>	
			</div>
							
			</div>
		
			<ul>
				<li id = "slogo"><h2 id="logo">Beginner Investor</h2></li>
				<li class = "sopt"><a href="#mini_nav">Welcome</a></li>
				<li class = "sopt"><a href="#Product_Panel">Tutorial</a></li>
			</ul>
		</div>
		<div id="block">
			<div id="main-area">
				<form action="" method="post">
					<div id = "inputArea">
						<input id = "searchField1" type="text" name="stockSearch" placeholder="Enter a stock symbol or company name" required />	
						<button name="search" type="submit">search</button>
						<button name="compare" type="button" onclick="compare()">compare another stock</button>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>