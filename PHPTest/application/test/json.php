<?php

require_once 'Attribute.php';
echo "json test";

$att = new Attribute("post",1,"my post");
echo("json ".json_encode((array)$att) ."\n");

$arr = array('a' => 1, 'b' => 2, 'c' => 3, 'd' => 4, 'e' => 5);

echo json_encode($arr) ."\n";

class Emp {
	public $name = "";
	public $hobbies  = "";
	public $birthdate = "";
	public $att;
	public $url;
}
$e = new Emp();
$e->name = "sachin";
$e->hobbies  = "sports";
$e->birthdate = "8/5/1974 12:20:03 ";
$e->att = $att;
$e->url  = 'http://sap.com/aaa/';

$emps = array($e);
$arr = array("emps" => $emps);
echo json_encode($arr,JSON_UNESCAPED_SLASHES);
echo("\nurl = $e->url");
printf("\n\nuniqid(): %s\r\n", uniqid());
$timestamp = time()+date("Z");
echo gmdate("Y-m-d H:i:s\n\n",$timestamp);



print gmdate("Y-m-d\TH:i:s\Z\n\n");
$t = strtotime('2014-07-01 08:22:22');
//echo date('d/m/y H:i:s',$t);
print gmdate("Y-m-d\TH:i:s\Z\n\n",$t);




?>