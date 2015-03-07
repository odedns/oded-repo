<?php


function connectionInit($url)
{
	$ch = curl_init($url);
	curl_setopt($ch, CURLOPT_HTTPGET, true);	
	curl_setopt( $ch, CURLOPT_RETURNTRANSFER, true );
	return($ch);
}

echo "calling search...\n";
//$endPoint = "https://searchq.wdf.sap.corp/opensearch?query=*&format=rss&filter=scm_a_site (value=scm_v_Site145/operator=EQ)&filter=scm_a_userurl (value=https://mediashare-qa.wdf.sap.corp/public/media/0_0dyx8orl//operator=EQ)";
//$endPoint = "https://searchq.wdf.sap.corp/opensearch?query=*&format=rss&filter=scm_a_site (value=scm_v_Site145/operator=EQ)";
$endPoint = "http://www.google.com";

$ch = connectionInit($endPoint);

$result = curl_exec($ch);
curl_close($ch);
echo "result = ". $result;



?>