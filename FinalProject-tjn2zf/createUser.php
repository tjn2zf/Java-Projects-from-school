<?php
// Taken from in class notes on createUser
// values were changed to fit my required code

	

    // HTTPS redirect
    if ($_SERVER['HTTPS'] !== 'on') {
		$redirectURL = 'https://' . $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI'];
		header("Location: $redirectURL");
		exit;
	}
	
	
	if(!session_start()) {
		// If the session couldn't start, present an error
		header("Location: error.php");
		exit;
	}
	
	
	// Check to see if the user has already logged in
	$loggedIn = empty($_SESSION['loggedin']) ? false : $_SESSION['loggedin'];
	
	if ($loggedIn) {
		header("Location: protected.php");
		exit;
	}
	
	
	$action = empty($_POST['action']) ? '' : $_POST['action'];
	
	if ($action == 'do_create') {
		create_user();
	} else {
		login_form();
	}
	
	function create_user() {
        $firstName = empty($_POST['firstName']) ? '' : $_POST['firstName'];
        $lastName = empty($_POST['lastName']) ? '' : $_POST['lastName'];
		$username = empty($_POST['username']) ? '' : $_POST['username'];
		$password = empty($_POST['password']) ? '' : $_POST['password'];
        $confirmPassword = empty($_POST['confirmPassword']) ? '' : $_POST['confirmPassword'];
      
        
        // We added the test user to our users table
        // INSERT INTO users (username, password, addDate, changeDate) VALUES ('test', 'pass', NOW(), NOW());
        
        if( strcmp($password, $confirmPassword) == 0){
            
        
            // Require the credentials
            require_once 'db.conf';

            // Connect to the database
            $mysqli = new mysqli($dbhost, $dbuser, $dbpass, $dbname);

            // Check for errors
            if ($mysqli->connect_error) {
                $error = 'Error: ' . $mysqli->connect_errno . ' ' . $mysqli->connect_error;
                require "login_form.php";
                exit;
            }

            // http://php.net/manual/en/mysqli.real-escape-string.php
            $username = $mysqli->real_escape_string($username);
            $password = $mysqli->real_escape_string($password);

            //more secure password storing for website
            $password = sha1($password); 

            // Build query
//            $query = "SELECT id FROM users WHERE userName = '$username' AND password = '$password'";
            $query = "insert into users (firstName, lastName, username, password) values ('$firstName', '$lastName', '$username', '$password')";

            // Sometimes it's nice to print the query. That way you know what SQL you're working with.
            //print $query;
            //exit;


            // If there was a result...
            if ($mysqli->query($query) == TRUE) {
                
                // Close the DB connection
                $mysqli->close();
                
                $error = "New User Created Successfully";
                require "login_form.php";
                exit;
                }
                
            else {
                $error = "Insert Error: " . $query . "<br>" . $mysqli->error;
                require "createUser_form.php";
                exit;
            }

            
        }
            // Else, there was no result
            else {
              $error = 'Error: Passwords do not match!';
              require "createUser_form.php";
              exit;
            }
        }
	
	function login_form() {
		$username = "";
		$error = "";
		require "login_form.php";
        exit;
	}
	
?>