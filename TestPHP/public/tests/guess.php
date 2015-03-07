<?php
$num_to_guess = 42;
$message = "";
if (!isset($_POST[guess])) {
    $message = "Welcome to the guessing machine!";
} elseif ($_POST[guess] > $num_to_guess) {
	 $message = "$_POST[guess] is too big! Try a smaller number";
	} elseif ($_POST[guess] < $num_to_guess) {
    	$message = "$_POST[guess] is too small! Try a larger number";
 } else { // must be equivalent
		    $message = "Well done!";
}
 ?>
 <html>
 <head>
 <title>Listing 9.7 A PHP number guessing script</title>
 </head>
 <body>
 <h1>
 <?php print $message ?>
 </h1>
 <form action="<?php print $_SERVER[PHP_SELF] ?>" method="POST">
 Type your guess here: <input type="text" name="guess">
 </form>
 </body>
 </html>
