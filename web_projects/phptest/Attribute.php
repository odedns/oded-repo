<?php

/*
 * Attribute class - document attribute.
 */
class Attribute {
	 public $_name;
	 public $_type;
	 public $_value;
	
	/*
	 * constructor - pass attribute name, type and value.
	 */
	function __construct($name, $type,$value)
	{
		$this->_name = $name;
		$this->_type = $type;
		$this->_value = $value;
	}
	
	
	function getName()
	{
		return($this->_name);
	}
	
	
	function getType()
	{
		return($this->_type);
	}
	
	function getValue()
	{
		return($this->_value);
	}
	
	
}

?>
