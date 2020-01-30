<?php
// This code is taken from the in class notes

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
?>

<!DOCTYPE html>
<!-- Thomas Newman
     tjn2zf
     December 8, 2017
-->
<!-- Functionality:
    Should display homepage
    Include link to protected content, redirect to login if not logged in
    Include link to non-login content
    Include visual for login, option to logout
    Include Header and basic elements that will appear on each page
-->

<html>
<head>
    <meta charset="utf-8">
	<title>Final Project Home</title>
    <link rel="stylesheet" type="text/css" href="common.css">
    <script src="jquery-3.2.1.js"></script>
	
</head>
<body>

    <h1> <img class="logo" src="images/logo.jpg" alt="logo"> </h1>
    
    <!--    coded based on example on w3 school-->
    <ul class="nav">
        <li class="nav"><a class="active" href="index.php">Home</a></li>
        <li class="nav"><a href="unprotected.php">Learn about Dungeons and Dragons</a></li>
        <li class="nav"><a href="protected.php">Create a Character</a></li>
        <li class="nav"><a href="combat.php">Sample Combat</a></li>
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
    

        <div class="content">
            <h1>Dungeons and Dragons: For New Players</h1>
            <h2>Welcome! This site is intended to serve as an introduction to DnD for prospective players!</h2>
            <br>
            <div class="container">
                <h1>WHAT IS DND?</h1>
                <a href="unprotected.php">
                    <img class="learn" src="images/learn.jpg" alt="learn">
                </a>
                <p class="dragon">Learn what Dungeons and Dragons is all about</p>
            </div>
            <br>
            <div class="container">
                <h1>CHARACTER CREATION</h1>
                <p class="character"><a href="protected.php">
                    <img class="create" src="images/character.jpg" alt="create">
                </a><br><br>Learn what goes into creation of a DnD character</p>
                <br>
                <br>
                <br>
            </div>
            <br>
            <br>
            <br>
            
        </div>
</body>
</html>