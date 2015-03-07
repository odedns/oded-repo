
<?php

$conn = mysql_connect("i070659-developer", "root", "root");

if (!$conn) {
    echo "Unable to connect to DB: " . mysql_error();
    exit;
}
  
if (!mysql_select_db("mysql")) {
    echo "Unable to select mydbname: " . mysql_error();
    exit;
}

$sql = "SELECT * 
        FROM   user";

$result = mysql_query($sql);

if (!$result) {
    echo "Could not successfully run query ($sql) from DB: " . mysql_error();
    exit;
}

if (mysql_num_rows($result) == 0) {
    echo "No rows found, nothing to print so am exiting";
    exit;
}

echo "printing results\n";

// While a row of data exists, put that row in $row as an associative array
// Note: If you're expecting just one row, no need to use a loop
// Note: If you put extract($row); inside the following loop, you'll
//       then create $userid, $fullname, and $userstatus
while ($row = mysql_fetch_assoc($result)) {
	print_r($row);
	/*
    echo $row["user"];
    echo $row["host"];
    echo $row["max_connections"];
    */
}

mysql_free_result($result);

?>
