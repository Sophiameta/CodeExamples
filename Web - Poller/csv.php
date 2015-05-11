<?php
    header('Content-type: text/csv');
    $fid = $_GET['fid'];
    header('Content-disposition: attachement;filename='.$fid.'.csv');
    require_once 'db_conn.php';
    
    echo "Stimmen; Antwort";
    $antworten_results = mysql_query("SELECT Antwort, Stimmen FROM Antworten WHERE Fid = $fid", $link);
    if(!$antworten_results)
    {
        die(mysql_error());
    }
    while ($result = mysql_fetch_object($antworten_results)) {
        $antwort = $result->Antwort;
        $stimmen = $result->Stimmen;
        echo "\n".$stimmen." ;".$antwort;
    }
  
  

