<?php
include_once('IndexingClient.php');
include_once('Document.php');
include_once('Attribute.php');
include_once('Response.php');
require_once ('KLogger.php');

$log = new KLogger ( "log.txt" , KLogger::DEBUG );
$log->LogInfo("got Notification");
echo "running client\n";
$url = "http://c5157117-index.wdf.sap.corp:8080/documents/";	   

$user = "scc_media_share_client";
$pass = "qwe123";
$alias = "sci_media_share_index";
$client = new IndexingClient($url,$user,$pass,$alias);

$doc = new Document("mykey","/url1", "some content","sci_media_share");


$att = new Attribute("scm_a_site","TEXT","scm_v_Site145");
$doc->addAttribute($att);
$att = new Attribute("scm_a_authLevels","TEXT","scm_v_auth01");
$doc->addAttribute($att);
$att = new Attribute("scm_a_title","TEXT","Some title");
$doc->addAttribute($att);
$att = new Attribute("scm_a_description","TEXT","description...");
$doc->addAttribute($att);
$att = new Attribute("scm_a_author","TEXT","Ido Mosseri");
$doc->addAttribute($att);
$att = new Attribute("scm_a_userid","TEXT","i053314");
$doc->addAttribute($att);
$att = new Attribute("scm_a_creDate","TEXT","01/01/2009");
$doc->addAttribute($att);
$att = new Attribute("scm_a_modDate","TEXT","01/01/2009");
$doc->addAttribute($att);
$att = new Attribute("scm_a_resourceType","TEXT","scm_v_resType273");
$doc->addAttribute($att);
$att = new Attribute("scm_a_tags","TEXT","tag1");
$doc->addAttribute($att);
$att = new Attribute("Scm_a_channel","TEXT","chanel1");
$doc->addAttribute($att);
$att = new Attribute("scm_a_fileType","TEXT","scm_v_fileType07");
$doc->addAttribute($att);
$att = new Attribute("scm_a_duration","TEXT","111");
$doc->addAttribute($att);
$att = new Attribute("scm_a_size","TEXT","111");
$doc->addAttribute($att);




$client->add($doc);
// print_r($client);

//$client->toXML();
$res = $client->index();
$code = $res->getCode();
print("code=$code\n");
print( "code = $res->_code\t desc = $res->_description() \n");
$keys = array($doc->getKey());
//$res = $client->delete($keys);
//print( "code = $res->_code\t desc = $res->_description() \n");

echo "\nclient created\n"


?>