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
		var_dump($info);
		return($info);
	}
	
	
	function getUserName($samAccountName)
	{
		$info = $this->getUser($samAccountName);
		$userName = $info[0]["displayname"][0];
		return($userName);
		
	}
	
	function close()
	{
		ldap_close($this->_conn);
	}
	
	
}	
	$user  = "Adquery086@SAP.CORP";
	$pass = "TwiceX21";  // associated password
	$port = 398;
	//$host = "sapwdf12.wdf.sap.corp";
	//$url = "ldap://10.17.120.218";
	//$url = "ldap://10.17.121.218";
	$url = "ldap://sapwdf11.wdf.sap.corp";
	//$user = "Adquery086@SAP.CORP";
	//$pass =  "This14You";
	
	
	$baseDn =  "DC=sap,DC=corp";
	echo "testing ldap....";
	$ldapUtils = new LDAPUtils($url, $port, $baseDn, $user, $pass);
	//$userName = $ldapUtils->getUserName("C5173251");
	$userName = $ldapUtils->getUserName("EPTest001");
	echo "got user: ". $userName;
	
	






?>