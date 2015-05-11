<?php

$server = "db.f4.htw-berlin.de";
$username = "s0540017";
$password = "WebtechPasswort";
$link = mysql_connect($server, $username, $password);
$database_name = "_s0540017__poll";

if(!$link) 
{
    die("Verbindung konnte nicht hergestellt werden: ".mysql_error());
}

mysql_select_db($database_name, $link);
        

?>