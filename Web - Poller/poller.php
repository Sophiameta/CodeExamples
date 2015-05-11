<?php
require_once 'db_conn.php';
$frage = $_POST['frage'];
$result_db = mysql_query("INSERT INTO Fragen (Frage) VALUES ('$frage')", $link);
if (!$result_db) 
{
    die("fehler: " . mysql_error());
}

$Fid_results = mysql_query("SELECT MAX(Fid) AS id FROM Fragen", $link);
while ($result = mysql_fetch_object($Fid_results)) 
{
    $fid = $result->id;
}
$antworten = explode("\n", $_POST['antworten']);
foreach ($antworten as $antwort)
{
    mysql_query("INSERT INTO Antworten (Fid, Antwort) VALUES ($fid,'$antwort')", $link);
}
$url = md5($frage . $fid);
$result_db = mysql_query("UPDATE Fragen SET URL = '$url' WHERE Fid = $fid", $link);

header("Location: abstimmung.php?url_id=" . $url);

?>





