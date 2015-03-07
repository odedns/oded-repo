<?php

$arr[0] = "one";
$arr[1] = "two";

print(implode(",", $arr));


$url = "http://localhost:8081/dev/TestPHP/public/tests/newfile.php";
$ch = curl_init($url);
$data = curl_exec($ch);

print $data;

curl_close($ch);





?>