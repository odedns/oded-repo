<?php 

class LDAPUtils {
	
	private $_url;
	private $_baseDn;
	private $_user;
	private $_password;
	private $_port;
	private $_conn;	
	/*
	 * constructor - pass attribute name, type and value.
	*/
	function __construct($url,$port,$baseDn,$user,$pass)
	{
		$this->_url = $url;
		$this->_port = $port;
		$this->_baseDn = $baseDn;
		$this->_user = $user;
		$this->_password = $pass;
		$this->_conn = ldap_connect($this->_url,$this->_port);
		if(!$this->_conn) {
			close();
			throw new Exception("Error: ". $ldap_error($this->_conn));
		}			 
		ldap_set_option($this->_conn, LDAP_OPT_PROTOCOL_VERSION, 3);
		ldap_set_option($this->_conn, LDAP_OPT_REFERRALS, 0);
		$r=ldap_bind($this->_conn,$user,$pass);
		if(!$r) {
			close();
			throw new Exception("Error: ". $ldap_error($this->_conn));
		}
		
		
	}
	
	
	function getUser($samAccountName)
	{
		$filter = "(&(objectclass=user)(samAccountName=". $samAccountName ."))";
		$sr=ldap_search($this->_conn,$this->_baseDn,$filter);
		
		
		$info = ldap_get_entries($this->_conn, $sr);
		//var_dump($info);
		return($info);
	}
	
	
	function getUserName($samAccountName)
	{
		$info = $this->getUser($samAccountName);
		if (isset($info) && sizeof($info) > 1) $userName = $info[0]["displayname"][0];
		else $userName = null;
		return $userName;
		
	}
	
	function close()
	{
		ldap_close($this->_conn);
	}
	
	
}	
	

?>