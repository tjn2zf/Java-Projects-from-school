<?php
// From in class notes by Wergeles

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
	
	
?>
<!--Functionality:
    Give user access to unrestricted content
    This may include:
        About DND
        Videos on DND
-->
<!DOCTYPE html>
<!-- Thomas Newman
     tjn2zf
     December 8, 2017
-->
<html>
<head>
    <meta charset="utf-8">
	<title>About DnD</title>
    <link rel="stylesheet" type="text/css" href="common.css">
    <script src="jquery-3.2.1.js"></script>
	
</head>
<body>
    <h1> <img class="logo" src="images/logo.jpg" alt="logo"> </h1>
    <ul class="nav">
        <li class="nav"><a href="index.php">Home</a></li>
        <li class="nav"><a class="active" href="unprotected.php">Learn about Dungeons and Dragons</a></li>
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
        <iframe width="560" height="315" src="https://www.youtube.com/embed/UdAwX8JB66E" frameborder="0" gesture="media" allow="encrypted-media" allowfullscreen></iframe>
        <iframe width="560" height="315" src="https://www.youtube.com/embed/Eo_oR7YO-Bw" frameborder="0" gesture="media" allow="encrypted-media" allowfullscreen></iframe>
        <h1>Dungeons and Dragons: An Overview</h1>
        <p>Dungeons and Dragons was created as a way to make fantasy wargames more intimate. It was created so that players could roleplay as an individual character in an ongoing fantasy story. It has grown immensely since its inception and remains the number one fantasy role-playing game to this day.</p>
        
        <p>At its core, Dungeons and Dragons is a game about storytelling. With every session of the game, an interactable story is guided and crafted by the players. Typically, a DnD game might include elements such as: slaying mythical creatures, deposing corrupt lords, pillaging perilous tombs, and convincing an innkeeper to let you stay a night for free because you're oh so terribly weak and weary and poor (roll bluff).</p>
        
        <p>The game is prepared and mastermined by a single player known as the Dungeon Master. This player is responsible for creating the world in which the story takes place. With each session, it is their job to create intriguing storylines for the other players to enjoy. This player will also be in control of any monsters, villians, or common folk the players might encounter. It's tough work being Dungeon Master, but having fun with your friends and seeing them enjoy your adventures makes it all worth it.</p>
        
        <p>If you're not the Dungeon Master, that would make you one of the games Player Characters (PC's). You are responsible for creating your own unique hero (or villian). You could be anything from a human wizard to an orc rougue to a gnome barbarian! Whatever you play, it is your role to cooperate with your other players in order to interact with the world and objectives your Dungeon Master has created for you.<a href ="protected.php"> If you are interested in a taste of what goes into building a character, CLICK HERE!</a></p>
       
    <br>
    <br>
    <br>
    </div>
</body>
</html>