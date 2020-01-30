<?php
// Taken from in class lecture notes

    // HTTPS redirect
    if ($_SERVER['HTTPS'] !== 'on') {
		$redirectURL = 'https://' . $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI'];
		header("Location: $redirectURL");
		exit;
	}
    
	// Every time we want to access $_SESSION, we have to call session_start()
	if(!session_start()) {
		header("Location: error.php");
		exit;
	}
	
	$loggedIn = empty($_SESSION['loggedin']) ? false : $_SESSION['loggedin'];
	if (!$loggedIn) {
		header("Location: login.php");
		exit;
	}
?>

<!DOCTYPE html>
<!-- Thomas Newman
     tjn2zf
     December 8, 2017
-->
<!--Functionality:
    allow user to access login restricted content
    character creator
-->

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Sample Combat</title>
    <link rel="stylesheet" type="text/css" href="common.css">
    <script src="jquery-3.2.1.js"></script>
    
<!--    <script src="app.js"></script>-->
    
<!--    taken from in class einstein quote example-->
    <script>
		function updateInfo(quoteID) {
			var xmlHttp = new XMLHttpRequest();
		
			xmlHttp.onload = function() {
				if (xmlHttp.status == 200) {
					var infoBox = document.getElementById('infoBox');
					infoBox.innerHTML = xmlHttp.responseText;
				  }
			}
		
			var reqURL = "updateInfo.php?infoId=" + quoteID;
		
			xmlHttp.open("GET", reqURL, true);
			xmlHttp.send();
		}
	</script>
	
    
    
</head>
<body>
    <h1> <img class="logo" src="images/logo.jpg" alt="logo"> </h1>
    
    
    
    <ul class="nav">
        <li class="nav"><a href="index.php">Home</a></li>
        <li class="nav"><a href="unprotected.php">Learn about Dungeons and Dragons</a></li>
        <li class="nav"><a href="protected.php">Create a Character</a></li>
        <li class="nav"><a class="active" href="combat.php">Sample Combat</a></li>
        <?php
if ($_SESSION['loggedin'] == true) {
?>
<li class="check" id="log">You Are Logged In</li>
<?php
} else {
?>
<li class="check" id="log">Not Currently Logged In</li>
<?php
}
?>
        <li class="nav" id="log"><a href="logout.php">Log out</a></li>
        <li class="nav" id="log"><a href="login.php">Log in</a></li>
    </ul>
    <img class="left" src="images/elf.jpg" alt="elf">
    <img class="right" src="images/enemy.jpg" alt="enemy">
    <div class="combat">
<!--        further use of einstein quote example from class notes-->
        <h1 id="combatTitle">Sample Combat</h1>
	<div class="fight">
	<input type="image" src="images/d20.jpg" class="die" value="Turn 1" onclick="updateInfo('quote1')">
	<input type="image" src="images/d20.jpg" class="die" value="Turn 2" onclick="updateInfo('quote2')">
	<input type="image" src="images/d20.jpg" class="die" value="Turn 3" onclick="updateInfo('quote3')">
	<input type="image" src="images/d20.jpg" class="die" value="Turn 4" onclick="updateInfo('quote4')">
	<input type="image" src="images/crit.png" class="die" id="crit" value="Turn 5" onclick="updateInfo('quote5')">
    </div>
	<div id="infoBox">Click one of the buttons above to experience a simulated round of combat.</div>
        

    
    </div>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
</body>
</html>