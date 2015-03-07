<?php

/*
 * Document class document to be indexed;
 */
class Document {
	private $_key;
	private $_url;
	private $_content;
	public  $_attributes;
	
	
	
	/*
	 * constructor - pass document key, url and content.
	 */
	function __construct($key, $url, $content)
	{
		$this->_key = $key;
		$this->_url = $url;
		$this->_content = $content;
		$this->_attributes = array();
	}
	
	
	
	function getContent()
	{
		return($this->_content);
	}	
	
	
	function getUrl()
	{
		return($this->_url);
	}
	
	
	function getKey()
	{
		return($this->_key);
	}
	
	
	/*
	 * add document attribute.
	 */
	function addAttribute($attr)
	{
		array_push($this->_attributes,$attr);
	}
	
}

?>