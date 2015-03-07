<?php

$array = array('Buck','Jerry','Tomas');

$arrayObject = new ArrayObject($array);
// Add new element
$arrayObject->append('Tweety');

// We are getting the iterator of the object
$iterator = $arrayObject->getIterator();

// Simple while loop
while ($iterator->valid()) {
    echo $iterator->current() . "\n";
    $iterator->next();
}


class Dog {
	var $_name;
	var $_id;
	
	function __construct($id , $name)
	{
		$this->_name = $name;
		$this->_id = $id;
	}
	
}

$dogs = array();

$dog1 = new Dog(1,"Bobby");
$dog2 = new Dog(2,"Scooby");

array_push($dogs, $dog1);
array_push($dogs, $dog2);

print_r($dogs);

/* Outputs */
/*
Buck
Jerry
Tomas
Tweety
*/
?>