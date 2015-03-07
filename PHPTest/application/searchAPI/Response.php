<?php

class Response {
	public $_code;
	public $_description;
	
	
	
	function __construct($code, $desc)
	{
		$this->_code = $code;
		$this->_description = $desc;
	}
	
	
	function getCode()
	{
		return($this->_code);
	}
	
	
	function getDescription()
	{
		return($this->_description);
	}
	
} 


?>



