<?php
include_once('IndexingClient.php');
include_once('Document.php');
include_once('Attribute.php');
include_once('Response.php');
require_once '../logger/KLogger.php';
require_once('../lib/KalturaClient.php');
include_once('IndexingConfig.php');

class SearchService {
	
	public static function getConfigFromIni ($configKey, $indexingConfig){
		return $indexingConfig->prop_arr[$configKey];
	}
	
	public static function getLogger ($indexingConfig){
		return $indexingConfig->log;
	}
	
	public static function indexEntry($entry,$indexingConfig, $action_type, $key) {
	
		try {
			if (isset ($entry) && (! $entry instanceof KalturaMediaEntry|| (isset($action_type) && $action_type == 'entry_add' ))) return;
			$action_type = isset ($action_type) ?  $action_type : 'entry_update';
			// Check for required parameters
			if (isset($key) ) {
				// Put parameters into local variables
				$url = $indexingConfig->media_space_prefix_url . '/' . $key . '/' ;
				$endPointUrlDocs = $indexingConfig->endpointurl .  '/documents/' ;
				$indexingClient = new IndexingClient($endPointUrlDocs,$indexingConfig->user,$indexingConfig->pass,$indexingConfig->alias);
				$doc = new Document($key,$url, '');
				if (strcmp ($action_type,'entry_update') == 0 ) 	self::indexCreateOrUpdate($indexingClient,$doc,$entry,$indexingConfig);
				elseif (strcmp ($action_type,'entry_delete') == 0)  self::indexDelete($indexingClient,$doc,$indexingConfig);
				return true;
					
			}//if
			else {
				$indexingConfig->log->LogInfo('The mandatorry fields for the request was not sent ');
				return false;
			}//else
		}//try
		catch(Exception $e) {
			$indexingConfig->log->LogError ('Message : ' . $e->getMessage());
			$indexingConfig->log->LogError ('Code : ' . $e->getCode());
		}//catch
	}
	
	public static function getCategoriesWithPrefix ($entry , $indexingConfig, $prefix){
		$result = array();
		$entryCatsArray =  explode(',',$entry->categories);
		foreach($entryCatsArray as $cat) {
			if (SearchService::startsWithIgnoreCae ($cat, $prefix)){
				$catShortName = SearchService::removePrefixIgnoreCae ($cat, $prefix) ;
				if (isset($catShortName) && strlen ($catShortName)>0) {
					array_push($result, $catShortName);
				}
			}
		}
		return $result;
	}
	
	public static function indexCreateOrUpdate($client,$doc,$entry,$indexingConfig){
		$publish = false;
		$topics = self::getCategoriesWithPrefix($entry, $indexingConfig, 'video>Topics>');
		$chanells = self::getCategoriesWithPrefix($entry, $indexingConfig, 'video>Channels>');
		if (isset($topics) && sizeof($topics)>0) $publish = true;
		if (isset($chanells) && sizeof($chanells)>0) $publish = true;
		// if document has not been published we can try to delete it.
		// this will take care of the case of unpublish.
		if (! $publish) {
			$indexingConfig->log->LogInfo('try to delete document from index since it has not benn published doc:' . $entry->name);
			self::indexDelete($client,$doc,$indexingConfig);
			return ; 
		}
		$indexingConfig->log->LogInfo('Prepare document for indexing (create/update Content)');
		$mediaType = isset($entry->mediaType) ? $entry->mediaType : 1;
		self::addAttributeIfExist($doc,'scm_a_site','scm_v_Site145');
		self::addAttributeIfExist($doc,'scm_a_authLevels','scm_v_auth01');
		self::addAttributeIfExist($doc,'scm_a_title',$entry->name);
		self::addAttributeIfExist($doc,'scm_a_description',$entry->description);
		self::addAttributeIfExist($doc,'scm_a_author',$indexingConfig->getUserName($entry->userId)); 
		self::addAttributeIfExist($doc,'scm_a_userid',$entry->userId);
		self::addAttributeIfExist($doc,'scm_a_creDate',date('d/m/Y', $entry->createdAt));
		//self::addAttributeIfExist($doc,'scm_a_modDate',date('d/m/Y', $entry->updatedAt)); // since we want the search will do filtering + sorting according to the create date, i also send the create date as modify date
		self::addAttributeIfExist($doc,'scm_a_modDate',date('d/m/Y', $entry->createdAt));
		switch ($mediaType) {
			case KalturaMediaType::VIDEO : //Video
				self::addAttributeIfExist($doc,'scm_a_resourceType','scm_v_resType273');
				self::addAttributeIfExist($doc,'scm_a_fileType','scm_v_fileType07');
				self::addAttributeIfExist($doc,'scm_a_viewCount',isset($entry->views) ? $entry->views : 0);
				break;
			case KalturaMediaType::IMAGE : //IMAGE
				self::addAttributeIfExist($doc,'scm_a_resourceType','scm_v_resType117');
				self::addAttributeIfExist($doc,'scm_a_fileType','scm_v_fileType06');
				break;
			case KalturaMediaType::AUDIO : //Audio
				self::addAttributeIfExist($doc,'scm_a_resourceType','scm_v_resType15');
				self::addAttributeIfExist($doc,'scm_a_fileType','scm_v_fileType09');
				self::addAttributeIfExist($doc,'scm_a_viewCount',$entry->views);
				break;
		}
	
		self::addAttributeIfExist($doc,'scm_a_tags',self::getTags($entry));
		if (isset($entry->msDuration) ) self::addAttributeIfExist($doc,'scm_a_duration',self::convertFromMilisecToTimeFormat($entry->msDuration));
		self::addAttributeIfExist($doc,'scm_a_likes',$entry->votes);//should change to scm_a_likes
		self::addAttributeIfExist($doc,'scm_a_topic',$topics);
		self::addAttributeIfExist($doc,'scm_a_channel',$chanells);
		$client->add($doc);
		$res = $client->index($indexingConfig->log);
		$code = $res->getCode();
		$indexingConfig->log->LogInfo('doc had been sent with response code :' . $code);
	
	}
	public static function indexDelete($client,$doc,$indexingConfig){
		$indexingConfig->log->LogInfo('prepare request for delete index');
		$keys = array($doc->getKey());
		$res = $client->delete($keys,$indexingConfig->log);
		$code = $res->getCode();
		$indexingConfig->log->LogInfo('removal rindex request had been sent with response code :' . $code);
	}
	public static function getTags($entry) {
		$arr = explode(',',$entry->tags);
		$prefix_not_allowd = 'displayname_';
		return array_filter($arr,
				function($elem) use($prefix_not_allowd){
					return ! (SearchService::startsWith($elem,$prefix_not_allowd));
				}
			);		
	}
	
	
	
	public static  function addAttributeIfExist($doc,$key,$value){
		if ($value) {
			if (is_array($value) ) $att = new Attribute($key,'TEXT',$value);
			else $att = $att = new Attribute($key,'TEXT',array($value));
			$doc->addAttribute($att);
		}
	}
	
	
	
	public static function convertFromMilisecToTimeFormat($milisec){
		$time = round ($milisec /1000);
		$time_string = '';
		$hours = (int)($time/(60*60));
		if(strlen($hours) > 1){
			$time_string = $hours.':';
		}else{
			$time_string = '0'.$hours.':';
		}
	
		$minutes = (int)(($time%(60*60))/(60));
		if($minutes >= 1){
			if(strlen($minutes) > 1){
				$time_string .= $minutes.':';
			}else{
				$time_string .= '0'.$minutes.':';
			}
	
			$seconds = ($time%(60*60))%(60);
			if(strlen($seconds) > 1){
				$time_string .= $seconds;
			}else{
				$time_string .= '0'.$seconds;
			}
		}else{
			if(strlen($time) > 1){
				$time_string .= '00:'.$time;
			}else{
				$time_string .= '00:0'.$time;
			}
		}
		return $time_string;
	
	
	}	
	public static function sendResponse($status = 200, $body = '', $content_type = 'text/html')
	{
		$status_header = 'HTTP/1.1 ' . $status . ' ' . $body;
		header($status_header);
		header('Content-type: ' . $content_type);
		echo $body;
	}
	
	public static function getClient ($indexingConfig)
	{		
			$kaltura_partner_id = 100;
			//define session variables
			$config           = new KalturaConfiguration($kaltura_partner_id);
			$config->serviceUrl = self::getConfigFromIni('serviceUrl',$indexingConfig);
			$client           = new KalturaClient($config);
			$ks = $client->generateSession(self::getConfigFromIni('initialAdminSecret',$indexingConfig), $kaltura_partner_id, KalturaSessionType::ADMIN, $kaltura_partner_id);
			//Set the generated KS as the default actions KS to use by the client library
			$client->setKs($ks);
			return $client;
	}
	
	public static function getEntryById ($entryId,$indexingConfig){
		$client = SearchService::getClient($indexingConfig);
		return  $client->baseEntry->get($entryId, null);
			
	}
	
	public static function clearIndex($indexingConfig){
		$endPointUrlindex =$indexingConfig->endpointurl . '/index/' ;
		$indexingClient = new IndexingClient($endPointUrlindex,$indexingConfig->user,$indexingConfig->pass,$indexingConfig->alias);
		$res = $indexingClient->clear($indexingConfig->log);
		return $res;
		
	}
	
	public static function startsWith($haystack, $needle)
	{
		$length = strlen($needle);
		return (substr($haystack, 0, $length) === $needle);
	}
	public static function startsWithIgnoreCae($haystack, $needle)
	{
		return SearchService::startsWith(strtolower($haystack),strtolower($needle));
	}
	public static function removePrefixIgnoreCae($haystack, $needle)
	{
		$h =strtolower($haystack);
		$n =strtolower($needle);
		$nsize = strlen($needle);
		$hsize = strlen($haystack);
		return substr($haystack, $nsize , $hsize - $nsize);
	}
	
	
}//class

if (isset($_GET['indexEvent'])) {
	$entryId = $_POST['entry_id'];
	$action_type= $_POST['notification_type'];
	$indexingConfig = new IndexingConfig();
	$indexingConfig->log->LogInfo('got notification, entry_id:' . $entryId . ', action_type:' . $action_type);	
	if (strcmp ($action_type,'entry_delete') == 0)  {
		$entry  = null ;
	}
	else {
		$entry = SearchService::getEntryById($entryId,$indexingConfig);
	}
	SearchService::indexEntry($entry,$indexingConfig,$action_type,$entryId);
	SearchService::sendResponse(200, 'ok');
}


function getAtPos($tmpArray,$pos) {
	return array_splice($tmpArray,$pos-1,1);
}




		
?>