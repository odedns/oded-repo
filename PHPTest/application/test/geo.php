<?php
//echo var_export(unserialize(file_get_contents('http://www.geoplugin.net/php.gp?ip="195.93.234.9"')));

$url  = "http://www.geoplugin.net/php.gp?ip=195.93.234.9";

$ch = curl_init();
curl_setopt($ch, CURLOPT_URL,$url);
$result = curl_exec($ch);
curl_close($ch);
$result = iconv('UTF-8', 'UTF-8//IGNORE', utf8_encode($result));
$result = urlencode($result);
echo ("serialize data = \n\n$result");

$geo = unserialize(base64_decode($result));

//var_dump($geo);
//print_r($geo);
if(!is_array($geo)) {
	echo ("\n\nnot an array\n");
	
}
echo ("\n". $geo['geoplugin_city'] ."\n");
$country = $geo['geoplugin_countryCode'];
echo ("\n\ncountry = $country\n\n");




?>