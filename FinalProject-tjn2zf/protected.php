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
	
	$loggedIn = empty($_SESSION['loggedin']) ? false : $_SESSION['loggedin'];
	if (!$loggedIn) {
		header("Location: login.php");
		exit;
	}
?>
<!--Functionality:
    allow user to access login restricted content
    character creator
-->

<!DOCTYPE html>
<!-- Thomas Newman
     tjn2zf
     December 8, 2017
-->

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<title>Character Creation</title>
    <link rel="stylesheet" type="text/css" href="common.css">
    <script src="jquery-3.2.1.js"></script>
    
    <script src="app.js"></script>
	
</head>
<body>
    <h1> <img class="logo" src="images/logo.jpg" alt="logo"> </h1>
    
    
    
    <ul class="nav">
        <li class="nav"><a href="index.php">Home</a></li>
        <li class="nav"><a href="unprotected.php">Learn about Dungeons and Dragons</a></li>
        <li class="nav"><a class="active" href="protected.php">Create a Character</a></li>
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
        
        <h1>Character Creation</h1>
        <h2 class="play">These are the playable races</h2>
        
        <!--      Taken from in class photo viewer code -->
        <div id="stage">
            
            <div id="photoMenu">
                <ul id="menuItems"></ul>
            </div>
            
            <h1 id="photoTitle">Test</h1>
            
            <div id="photoDisplay">
                <img id="imageHolder" src="images/character.jpg">
            </div>
            
        </div>
        <br>
        <br>
    
<?php
// define variables and set to empty values
$nameErr = $raceErr = $classErr = "";
$name = $race = $class = "";

if ($_SERVER["REQUEST_METHOD"] == "POST") {
  if (empty($_POST["name"])) {
    $nameErr = "Name is required";
  } else {
    $name = test_input($_POST["name"]);
    // check if name only contains letters and whitespace
    if (!preg_match("/^[a-zA-Z ]*$/",$name)) {
      $nameErr = "Only letters and white space allowed"; 
    }
  }

  if (empty($_POST["comment"])) {
    $comment = "";
  } else {
    $comment = test_input($_POST["comment"]);
  }
    
    if (empty($_POST["race"])) {
    $raceErr = "Race is required";
  } else {
    $race = test_input($_POST["race"]);
  }
    
     if (empty($_POST["class"])) {
    $classErr = "Class is required";
  } else {
    $class = test_input($_POST["class"]);
  }
}


function test_input($data) {
  $data = trim($data);
  $data = stripslashes($data);
  $data = htmlspecialchars($data);
  return $data;
}
?>

<h2>Create your DnD character</h2>
<p>Use the attributes below to help guide your character creation</p>
<p><span class="error">* required field.</span></p>
<form method="post" action="<?php echo htmlspecialchars($_SERVER["PHP_SELF"]);?>">  
    
  Name: <input type="text" name="name" value="<?php echo $name;?>">
    
  <span class="error">* <?php echo $nameErr;?></span>
    
    <br><br>
    
  Race:
  <input type="radio" name="race" <?php if (isset($race) && $race=="human") echo "checked";?> value="human">Human
  <input type="radio" name="race" <?php if (isset($race) && $race=="elf") echo "checked";?> value="elf">Elf
  <input type="radio" name="race" <?php if (isset($race) && $race=="dwarf") echo "checked";?> value="dwarf">Dwarf
  <input type="radio" name="race" <?php if (isset($race) && $race=="half-elf") echo "checked";?> value="half-elf">Half-Elf
  <input type="radio" name="race" <?php if (isset($race) && $race=="gnome") echo "checked";?> value="gnome">Gnome
  <input type="radio" name="race" <?php if (isset($race) && $race=="halfling") echo "checked";?> value="halfling">Halfling
  <input type="radio" name="race" <?php if (isset($race) && $race=="tiefling") echo "checked";?> value="tiefling">Tiefling
  <input type="radio" name="race" <?php if (isset($race) && $race=="dragonborn") echo "checked";?> value="dragonborn">Dragonborn
  <input type="radio" name="race" <?php if (isset($race) && $race=="half-org") echo "checked";?> value="half-org">Half-Orc
    
  <span class="error">* <?php echo $raceErr;?></span>
  <br><br>  
    
    <br><br>
    
   Class:
    <input type="radio" name="class" <?php if (isset($class) && $class=="barbarian") echo "checked";?> value="barbarian">Barbarian
    <input type="radio" name="class" <?php if (isset($class) && $class=="bard") echo "checked";?> value="bard">Bard
    <input type="radio" name="class" <?php if (isset($class) && $class=="cleric") echo "checked";?> value="cleric">Cleric
    <input type="radio" name="class" <?php if (isset($class) && $class=="druid") echo "checked";?> value="druid">Druid
    <input type="radio" name="class" <?php if (isset($class) && $class=="fighter") echo "checked";?> value="fighter">Fighter
    <input type="radio" name="class" <?php if (isset($class) && $class=="monk") echo "checked";?> value="monk">Monk
    <input type="radio" name="class" <?php if (isset($class) && $class=="paladin") echo "checked";?> value="paladin">Paladin
    <input type="radio" name="class" <?php if (isset($class) && $class=="ranger") echo "checked";?> value="ranger">Ranger
    <input type="radio" name="class" <?php if (isset($class) && $class=="rogue") echo "checked";?> value="rogue">Rogue
    <input type="radio" name="class" <?php if (isset($class) && $class=="sorcerer") echo "sorcerer";?> value="barbarian">Sorcerer
    <input type="radio" name="class" <?php if (isset($class) && $class=="warlock") echo "checked";?> value="warlock">Warlock
    <input type="radio" name="class" <?php if (isset($class) && $class=="wizard") echo "checked";?> value="wizard">Wizard
    
    <span class="error">* <?php echo $classErr;?></span>
    <br><br>
    Additional Info: <textarea name="comment" rows="5" cols="40"><?php echo $comment;?></textarea>
    
    <br><br>
    
    <input type="submit" name="submit" value="Submit">
</form>

<?php
echo "<h2>Your Character:</h2>";
echo $name;
echo ": A(n) ";
echo $race;
echo " ";
echo $class;
echo "<br>Additionally: ";
echo $comment;
echo "<br>";
//echo $gender;
?>

<p>Race and Class are the two most important decisions when building a character. They determine who you are and what you're able to do.</p>
<a href ="combat.php">Now that you've got a character, check out a sample combat!</a>

    
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