<?php

$a = "global value";




function foo() {
	
	global $a;
	
	echo ("a is : $a\n");
	
	$a = "another value";
	echo ("a is : $a\n\n");
}



foo();

echo "now a :  $a";
?>