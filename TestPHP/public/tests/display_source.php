
<?php // display source code
$lines = file('http://google.com/');
foreach ($lines as $line_num => $line) {
	// loop thru each line and prepend line numbers
	echo "Line #<b>{$line_num}</b> : " . htmlspecialchars($line) . "<br>\n";
}

?>