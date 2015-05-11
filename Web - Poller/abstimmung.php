<?php

require_once 'db_conn.php';
$url = $_GET['url_id'];

$fragen_results = mysql_query("SELECT Fid, Frage FROM Fragen WHERE URL = '$url'", $link);
while ($result = mysql_fetch_object($fragen_results)) {
    $fid = $result->Fid;
    $frage = $result->Frage;
}
if (empty($_COOKIE[$fid]) && empty($_POST['antwort'])):

    $output = "<!DOCTYPE html>
            <html>
                <head>
                    <meta http-equiv='content-type' content='text/html; charset=utf-8'>
                    <meta name='author' content='Sophia Mühling'>
                    <link type='text/css' rel='stylesheet' href='style.css'/>
                    <link href='//fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>
                    <link href='//fonts.googleapis.com/css?family=Droid+Sans:400,700' rel='stylesheet' type='text/css'>
                    <title>Poller</title>
                </head>
                <body>
                    <h1>" . $frage . "</h1>
                    <form method='POST' action='abstimmung.php?url_id=" . $url . "'>";
    $antworten_results = mysql_query("SELECT Aid, Antwort FROM Antworten WHERE Fid = '$fid'", $link);
    if (!$antworten_results) {
    die(mysql_error());
    }
    while ($result = mysql_fetch_object($antworten_results)) {
        $antwort = $result->Antwort;
        $aid = $result->Aid;
        $output .= "<br><label class='antwort'><input type='radio' name='antwort' value='" . $aid . "'/>" . $antwort . "</label>";
    }
    $output .= '<br><input type="submit" value="Abstimmen"/></form></body></html>';
    echo $output;

elseif(!empty($_POST['antwort'])):
    $aid = $_POST['antwort'];
    $stimmen_results = mysql_query("SELECT Fid, Stimmen FROM Antworten WHERE Aid = $aid", $link);
    if (!$stimmen_results) {
        die(mysql_error());
    }
    while ($result = mysql_fetch_object($stimmen_results)) {
        $fid = $result->Fid;
        $stimmen = $result->Stimmen + 1;
    }
    if (empty($_COOKIE[$fid])) {
        mysql_query("UPDATE Antworten SET Stimmen = $stimmen WHERE Aid = $aid", $link);
    }
    setcookie($fid, "true");

    $output = "<!DOCTYPE html>
            <html>
                <head>
                    <meta http-equiv='content-type' content='text/html; charset=utf-8'>
                    <meta name='author' content='Sophia Mühling'>
                    <link type='text/css' rel='stylesheet' href='style.css'/>
                    <link href='//fonts.googleapis.com/css?family=Lobster' rel='stylesheet' type='text/css'>
                    <link href='//fonts.googleapis.com/css?family=Droid+Sans:400,700' rel='stylesheet' type='text/css'>
                    <title>Poller</title>
                </head>
                <body>
                    <h1>" . $frage . "</h1>";
    $antworten_results = mysql_query("SELECT Antwort, Stimmen FROM Antworten WHERE Fid = $fid", $link);
    if (!$antworten_results) {
    die(mysql_error());
    }
    $anzahl = 0;
    $output2 = "";
    while ($result = mysql_fetch_object($antworten_results)) {
        $stimmen = $result->Stimmen;
        $antwort = $result->Antwort;
        $anzahl += $stimmen;
        $output2 .= "<p>" . $antwort . " mit " . $stimmen . " Stimme(n)</p>";

    }
    if ($anzahl >= 5) {
    $output .= $output2 . "<a class='link-button' href='csv.php?fid=" . $fid . "'>Ergebnisse als CSV-Datei herunterladen</a>";
    } else {
    $output .= "<p>Die Ergebnisse können erst ab 5 Stimmen angezeigt werden. Bitte stimmen Sie nur einmalig ab. </a></body></html>";
    }
    echo $output;
else:
    echo "Fehler beim Neuladen der Seite. Bitte stimmen Sie nur einmalig ab.";
endif;


