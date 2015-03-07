
<html>
 <head>
 <title>Listing 9.1 Looping through the $GLOBALS array</title>
 </head>
 <body>
 <?php
 $user1 = "Bob";
 $user2 = "Harry";
 $user3 = "Mary";
 foreach ($GLOBALS as $key=>$value) {
 		if(is_array($value)) {
 			$vals = implode(",", $value);
 			print "\$GLOBALS[\"$key\"] ==  $vals 	 <br>";
 		} else {
	    	print "\$GLOBALS[\"$key\"] == $value<br>";
 		}
	 }
	 ?>
 </body>
 </html>
