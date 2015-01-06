<?php
	$uploaddir = '/var/www/uploads/';
	$uploadfile = $uploaddir . basename($_FILES['userfile']['name']);

	echo "<p>";

	if (move_uploaded_file($_FILES['userfile']['tmp_name'], $uploadfile)) {
	 	$o = shell_exec("/var/www/html/traitement.sh");
		$output = shell_exec("/var/www/html/cld2.sh");
		echo $output;
	} else {
	   echo "Upload failed";
	}

?>
