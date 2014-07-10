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
}
$e = new Emp();
$e->name = "sachin";
$e->hobbies  = "sports";
$e->birthdate = "8/5/1974 12:20:03 ";
$e->att = $att;

$emps = array($e);
$arr = array("emps" => $emps);
echo json_encode($arr);
?>