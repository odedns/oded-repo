<?php

// create curl resource

$endPoint = "https://searchq.wdf.sap.corp/opensearch?query=*&format=rss&filter=scm_a_site(value=scm_v_Site145/operator=EQ)";

$ch = curl_init($endPoint);

// set url
//curl_setopt($ch, CURLOPT_URL, "https://searchq.wdf.sap.corp");

//return the transfer as a string
curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);


// $output contains the output string
$output = curl_exec($ch);
$err     = curl_errno( $ch );
$errmsg  = curl_error( $ch );
// close curl resource to free up system resources
curl_close($ch);

print "output = ". trim($output);
print "err = ". $errmsg;

?>