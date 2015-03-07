<?php


class T {
	var $sVar;
	static $my_static = 'foo';
	
	function __construct()
	{
		$this->sVar = 20;
	}
	
	
	static function foo()
	{
		print "in T foo()\n";
	}
	
}




$t = new T;
echo ("svar = $t->sVar\n");

print T::$my_static . "\n";

T::foo();




?>