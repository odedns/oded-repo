

<html>
<body>

<h2>Sample Form</h2>


<form action="form.php" method="POST">

<p>Entry id: <input type="text" name="entry_id" /> 
<p>Name : <input type="text" name="name" />
<p>Tags : <input type="text" name="tags" />

<p> <input type="submit" name="submitButton" />
<p> <input type="reset" name="resetButton" />

</form>


<?php 

if($_SERVER['REQUEST_METHOD'] == "POST") {

	echo "<p>form submitted";
	echo "<br/>Entry id: $_POST[entry_id]";
}


?>
</body>


</html>

