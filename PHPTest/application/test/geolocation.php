<?php

$geolocationErrorMsg = null;

function geolocationError() {
	
	return($geolocationErrorMsg);
}




function getCountryByIp($ip) {
	$country = null;
	global $geolocationErrorMsg;
	$url  = "http://www.geoplugin.net/json.gp?ip=";
	$url = $url . $ip;
	$ch = curl_init();
	curl_setopt($ch, CURLOPT_PROXY, "proxy.wdf.sap.corp:8080");
	curl_setopt($ch, CURLOPT_URL,$url);
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
	$json = curl_exec($ch);
	$http_status = curl_getinfo($ch, CURLINFO_HTTP_CODE);
	curl_close($ch);
	echo ("http status = $http_status\n");
	if($http_status != 200) {
		$geolocationErrorMsg = "getCountryByIp - http error: ". $http_status;
		return($country);
	}
	$json = iconv('UTF-8', 'UTF-8//IGNORE', utf8_encode($json));
	
	$c = array("'","http","<a","\</a>","\/\/","<\/a>",">","\\");
	$json = str_replace($c, "XX", $json);
	$obj = json_decode($json);
	$jsonErr = json_last_error();
	
	if($jsonErr != JSON_ERROR_NONE) {
		$geolocationErrorMsg = " getCountryByIp - json_decode error: ". $jsonErr;
		return($country);		
	}
	$country = $obj->geoplugin_countryCode;
	return($country);
	
}


echo ("running test ");
$ip = "10.26.142.205";
//$ip = "195.93.234.9";

$country = getCountryByIp($ip);
echo("contry = $country\n\n");
