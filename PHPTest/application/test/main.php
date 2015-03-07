<?php

function __autoload($class_name) {
	include $class_name . '.php';
}

echo( "in main\n");

$att = new Wrapper("foo","TEXT","bar");

printf("name = %s\n",$att->getName());



?>