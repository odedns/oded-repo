<?php
require_once('../lib/KalturaClient.php');
require_once('../lib/KalturaTypes.php');
require_once('SearchService.php');
require_once '../logger/KLogger.php';
include_once('IndexingConfig.php');


if (! isset($_GET['startRebuild'])) {
	echo '<form  method="get"> <input type="hidden" name="startRebuild" value="true" /><input type="submit" value="Start Rebuild" /></form>';	
} 
else {
	$indexingConfig = new IndexingConfig();
	$log = $indexingConfig->log;
	$log->LogError('start rebuilding task') ;
	$fetchSize =  SearchService::getConfigFromIni('fetchSize',$indexingConfig);
	$response = SearchService::clearIndex($indexingConfig);
	echo '<br/>Clear indexing - done with response :' . $response->getCode() . ',' . $response->getDescription() . ' </br>';
	$log->LogError('clear indexing - done') ;
	$client = SearchService::getClient($indexingConfig);
	$entryFilter = new KalturaBaseEntryFilter();
	$entryFilter->statusEqual = KalturaEntryStatus::READY;
	$entryFilter->orderBy = KalturaBaseEntryOrderBy::CREATED_AT_DESC;
	$entryFilter->typeIn =  KalturaMediaType::VIDEO . ',' .  KalturaMediaType::AUDIO . ',' .  KalturaMediaType::IMAGE;
	
	$kalturaPager = new KalturaFilterPager();
	$kalturaPager->pageSize = $fetchSize;
	$kalturaPager->pageIndex = 1;
	$alreadyIndexCount = 0;
	$results = $client->baseEntry->listAction($entryFilter, $kalturaPager);
	echo '<br/>total size of documents:' . $results->totalCount;
	echo '<br/>take the first ' . $kalturaPager->pageSize;
	$log->LogError('start rebuilding ' . $results->totalCount . 'videos') ;
	while ($results->totalCount >= $alreadyIndexCount  && count($results->objects) >0 ) {
		foreach($results->objects as $entry){ 
		 echo '<div><span>' . $alreadyIndexCount . ') Name: </span>' . $entry->name . ': index done. </div>' ;
		 SearchService::indexEntry($entry,$indexingConfig,null,$entry->id);
		 $alreadyIndexCount ++;
		 }
		 $kalturaPager->pageIndex = $kalturaPager->pageIndex + 1 ;
		 $results = $client->baseEntry->listAction($entryFilter, $kalturaPager); 
		 echo '<br/>take the next ' . $kalturaPager->pageSize;
		 $log->LogError('index ' . $alreadyIndexCount . ' videos from total ' . $results->totalCount . ' videos') ;
	}                
	$log->LogError('<br/><br/><h1>frinish indexing  ' . $alreadyIndexCount . ' videos </h1>');
	echo 'frinish indexing  ' . $alreadyIndexCount . ' videos';
	# http://mediashare-dev-demo1.wdf.sap.corp:8082/searchAPI/b.php?p=2
	# http://mediashare-dev-demo1.wdf.sap.corp:8082/searchAPI/b.php?entryType=1 <- images
}//else
?>
