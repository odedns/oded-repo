<?php
require_once '../logger/KLogger.php';
require_once 'LDAPUtils.php';
class IndexingConfig {
	public  $log ;
	public  $endpointurl ;
	public  $user ;
	public  $pass ;
	public  $alias ;
	public  $prop_arr ;
	public  $media_space_prefix_url ;
	public  $doneinit=false;
	public  $topics ;
	public  $ldapUtils;
	
	public function __construct() {
		$this->prop_arr = parse_ini_file('mediashare_extension_properties.ini');
		$this->log = new KLogger ( '/monsoon/log/mediashare_extension_log.txt' , $this->prop_arr['debugLevel'] );
		$this->endpointurl = $this->prop_arr['url'] ;
		$this->user = $this->prop_arr['user'];
		$this->pass = $this->prop_arr['pass'];
		$this->alias = $this->prop_arr['alias'];
		$this->media_space_prefix_url = $this->prop_arr['media_space_prefix_url'];
		//SELECT id,name FROM `kaltura`.`category` where parent_id = 17
		$this->topics = array( 'Business Analytics', 'Business Process', 'Ecosystem & Partners', 'Events', 'Learning & Education', 'Mobile', 'Cloud', 'People', 'Research & Innovation', 'Selling SAP', 'Services', 'Technology & Database');
		$this->ldapUtils = new LDAPUtils($this->prop_arr['ldapUrl'], $this->prop_arr['ldapPort'], $this->prop_arr['ldapBaseDn'], $this->prop_arr['ldapUser'], $this->prop_arr['ldapPass']);
		$this->log->LogInfo('getting data from ini file (mediashare_extension_properties.ini): url='. $this->endpointurl . ',user=' . $this->user . ',password =' . $this->pass . ',alias=' . $this->alias);
		$this->doneinit=true;
	}
	public function getUserName ($userId) {
		$userName = $this->ldapUtils->getUserName($userId) ;
		return isset($userName) ? $userName : '[unknown]';
	}
}