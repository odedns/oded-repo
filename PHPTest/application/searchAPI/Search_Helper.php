<?php
include_once('Response.php');



function parseResponse($xmlStr,$log)
{
	$s = simplexml_load_string($xmlStr);
	$log->LogInfo('response xml:' . $xmlStr);
	$resp = new Response($s->code, $s->description);
	return($resp);
	
}



/*
$resp = parseResponse('<?xml version="1.0" encoding="UTF-8"?><response><code>0</code><description>added ok.</description></response>');

echo ("desc = $resp->_description\t code = $resp->_code");
*/
?>