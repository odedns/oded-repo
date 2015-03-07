<?php

$myvar = "my global var";


echo ("myvar = $myvar");


function foo()
{
	global $myvar;
	
	echo("my var in foo= $myvar");
	
}


foo();

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

$c = array("'","http","<a","\</a>","\/\/","<\/a>",">");
$s = str_replace($c, "XX", $json);
echo ("\ns = $s\n");

$obj = json_decode($s);
var_dump($obj);
echo print_r($obj,true);
echo ("\n\ncountry = $obj->geoplugin_countryCode\n");



?>
