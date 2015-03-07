<?php

/*
$json = '{ 
  "geoplugin_request":"195.93.234.9",
  "geoplugin_status":200,
  "geoplugin_credit": "Some of the returned data includes GeoLite data created by MaxMind, available from XX href=XXXXXXXXXXwww.maxmind.comXXXXXXXXXXXXwww.maxmind.comXX.",
  "geoplugin_city":"Tel Aviv",
  "geoplugin_region":"Tel Aviv",
  "geoplugin_areaCode":"0",
  "geoplugin_dmaCode":"0",
  "geoplugin_countryCode":"IL",
  "geoplugin_countryName":"Israel",
  "geoplugin_continentCode":"AS",
  "geoplugin_latitude":"32.0667",
  "geoplugin_longitude":"34.766701",
  "geoplugin_regionCode":"05",
  "geoplugin_regionName":"Tel Aviv",
  "geoplugin_currencyCode":"ILS",
  "geoplugin_currencySymbol":"&#8362;",
  "geoplugin_currencySymbol_UTF8":"\u20aa",
  "geoplugin_currencyConverter":3.4301
}';

*/
$url  = "http://www.geoplugin.net/json.gp?ip=195.93.234.9";

$ch = curl_init();
curl_setopt($ch, CURLOPT_PROXY, "proxy.wdf.sap.corp:8080");
curl_setopt($ch, CURLOPT_URL,$url);
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
$json = curl_exec($ch);
$http_status = curl_getinfo($ch, CURLINFO_HTTP_CODE);
curl_close($ch);
echo ("http status = $http_status\n");
echo "json = $json\n";


$json = iconv('UTF-8', 'UTF-8//IGNORE', utf8_encode($json));

$c = array("'","http","<a","\</a>","\/\/","<\/a>",">","\\");
$json = str_replace($c, "XX", $json);
echo $json;
$obj = json_decode($json);
switch (json_last_error()) {
	case JSON_ERROR_NONE:
		echo ' - No errors';
		break;
	case JSON_ERROR_DEPTH:
		echo ' - Maximum stack depth exceeded';
		break;
	case JSON_ERROR_STATE_MISMATCH:
		echo ' - Underflow or the modes mismatch';
		break;
	case JSON_ERROR_CTRL_CHAR:
		echo ' - Unexpected control character found';
		break;
	case JSON_ERROR_SYNTAX:
		echo ' - Syntax error, malformed JSON';
		break;
	case JSON_ERROR_UTF8:
		echo ' - Malformed UTF-8 characters, possibly incorrectly encoded';
		break;
	default:
		echo ' - Unknown error';
		break;
}

echo "\n\n";

//var_dump(unserialize($obj));
echo print_r($obj,true);
echo ("\n\ncountry = $obj->geoplugin_countryCode\n");

?>