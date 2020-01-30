<!DOCTYPE html>
<!-- Thomas Newman
     tjn2zf
     December 8, 2017
-->
<!-- Based on in class lecture code -->
<html>
<head>
	<title>User Login</title>
	<link href="common.css" rel="stylesheet" type="text/css">
    <script src="jquery-3.2.1.js"></script>
    <script>
        $(function(){
            $("input[type=submit]").button();
        });
    </script>
</head>
<body>
     <h1> <img class="logo" src="images/logo.jpg" alt="logo"> </h1>
    <ul class="nav">
        <li class="nav"><a href="index.php">Home</a></li>
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
        <li class="nav" id="log"><a class="active"  href="login.php">Log in</a></li>
    </ul>
    
    <div id="loginWidget" class="ui-widget content">
        <h1 class="ui-widget-header">Login</h1>
        
        <?php
            if ($error) {
                print "<div class=\"ui-state-error\">$error</div>\n";
            }
        ?>
        
        <form action="login.php" method="POST">
            
            <input type="hidden" name="action" value="do_login">
            
            <div class="stack">
                <label for="username">User name:</label>
                <input type="text" id="username" name="username" class="ui-widget-content ui-corner-all" autofocus value="<?php print $username; ?>">
            </div>
            
            <div class="stack">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" class="ui-widget-content ui-corner-all">
            </div>
            
            <div class="stack">
                <input type="submit" value="Submit">
            </div>
        </form>
        
        <br>
        <a href="createUser_form.php">Not a User?</a>
        
    </div>
</body>
</html>
