<?php

$myvar = "my global var";


echo ("myvar = $myvar");


function foo()
{
	global $myvar;
	
	echo("my var in foo= $myvar");
	print_r(expression)
	
}


foo();

?>
