<?php
require_once 'KLogger.php';

$log = new KLogger ( "log.txt" , KLogger::DEBUG );

// Do database work that throws an exception
$log->LogError("An exception was thrown in ThisFunction()");

// Print out some information
$log->LogInfo("Internal Query Time: $time_ms milliseconds");

// Print out the value of some variables
$log->LogDebug("User Count: $User_Count");

 