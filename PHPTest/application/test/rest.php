<?php
//$url = "https://coneskywalker.hana.ondemand.com/api/v1/activity";

//$url = "http://echo.jsontest.com/title/ipsum/content/blah";
$url = "http://localhost/dump.php";
$jsonStr = '{"items":[{"published":"2014-06-11 08:43:37","generated":{"url":"http:\/\/blogs.sap.com","id":"blogs.sap.com","displayName":"SAP Blogs"},"provider":{"url":"http:\/\/blogs.sap.com","id":"blogs.sap.com","displayName":"SAP Blogs"},"actor":{"image":{"url":"http:\/\/scn.sap.com\/people","width":"200","height":"200"},"url":"http:\/\/scn.sap.com\/people","id":"admin","displayName":"Oded Nissan","objectType":"person"},"verb":"post","object":{"objectType":"blogpost","url":"http:\/\/localhost\/wordpress\/?p=38","id":38,"displayName":""},"target":{"objectType":"blogpost","url":"http:\/\/localhost\/wordpress\/?p=38","id":38,"displayName":""}}]} ';
$proxy = 'proxy.wdf.sap.corp:8080';



	echo("sending  to $url\n");
	$ch = curl_init($url);
	//curl_setopt($ch, CURLOPT_PROXY, $proxy);
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, true );
	curl_setopt($ch, CURLOPT_FOLLOWLOCATION, true);
	curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
	curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, false);

	curl_setopt($ch, CURLOPT_HEADER, true); // return headers 0 no 1 yes

	curl_setopt($ch, CURLOPT_HTTPHEADER, array(
	'X-Apple-Tz: 0',
	'Authorization: Bearer 44ada9f7d8143b4a85caea1529f6f98',
	'Content-Type: application/json')
	
	
	);
	//curl_setopt($ch, CURLOPT_HTTPGET,true);
	curl_setopt($ch, CURLOPT_POST,true);
	curl_setopt($ch,CURLOPT_TIMEOUT,1000);
	curl_setopt($ch,CURLINFO_HEADER_OUT,true );
	//	curl_setopt($ch, CURLOPT_POSTFIELDS, $jsonStr);
	curl_setopt($ch,CURLOPT_VERBOSE,true);




	$result = curl_exec($ch);
	$err_no = curl_errno($ch);
	if($err_no > 0) {
		$errMsg = curl_error($ch);
		echo("err no: $err_no\t $errMsg\n");
	}
	$info = curl_getinfo($ch,CURLINFO_HTTP_CODE );
	echo("got code: $info\n" );
	echo("result = \n $result\n");
	$headerinfo = curl_getinfo($ch,CURLINFO_HEADER_OUT);
	curl_close($ch);
	echo("#### header info  = ####\n $headerinfo");


?>