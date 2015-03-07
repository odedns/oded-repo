<?php
include_once('Document.php');
include_once('Attribute.php');
include_once('Response.php');
include_once('Search_Helper.php');
/*
 * class IndexingClient
 * Used to index documents on the search engine.
 */
class IndexingClient {
	private $_documents;
	private $_endpoint;
	private $_user;
	private $_pass;
	private $_alias;
	
	
	/*
	 * constructor. pass ewndpoint use/pass and alias.
	 */
	function __construct($endpoint, $user,$pass,$alias)
	{
		$this->_endpoint = $endpoint;
		$this->_user = $user;
		$this->_pass = $pass;
		$this->_alias = $alias;
		$this->_documents = array();
	}
	
	
	
	/*
	 * set the server endpoint.
	 */
	function setEndPoint($endPoint)
	{
		$this->_endpoint = $endPoint;
		
	}
	
	/*
	 * set the alias for the client.
	 */
	function setAlias($alias)
	{
		$this->_alias = $alias;
	}
	
	/*
	 * set credentials to connect to server.
	 */
	function setCredentials($user, $pass)
	{
		$this->_user = $user;
		$this->_pass = $pass;
	}
	
	
	/*
	 * add a document to the documents array.
	 */
	function add($document)
	{
		array_push($this->_documents,$document);
	}
	
	
	/* 
	 * generate the appropriate XML from the object.
	 */
	private function toXML()
	{
		$dom = new DOMDocument();
		$dom->formatOutput = true;
		
		$docs = $dom->createElement("documents");
		$dom->appendChild( $docs );
		$index = $dom->createElement("index");
		$name = $dom->createElement("name");
		$name->appendChild(  $dom->createTextNode($this->_alias ) );
		$index->appendChild($name);
		$docs->appendChild($index);
		foreach( $this->_documents  as $document ) 
		{
			$doc = $dom->createElement("document");
			$key = $dom->createElement("key");
			$key->appendChild($dom->createTextNode($document->getKey()));
			$doc->appendChild($key);
			$url = $dom->createElement("url");
			$url->appendChild($dom->createTextNode($document->getUrl()));
			$doc->appendChild($url);
			$content = $dom->createElement("content");
			$content->appendChild($dom->createTextNode($document->getContent()));
			$doc->appendChild($content);
			
			/*
			 * loop over attributes
			 */
			$attrs = $dom->createElement("attributes");
			
			foreach ($document->_attributes as $attribute) {
				$attr = $dom->createElement("attribute");
				$att_name = $dom->createElement("name");
				$att_name->appendChild($dom->createTextNode($attribute->getName()));
				$attr->appendChild($att_name);
				$att_type = $dom->createElement("type");
				$att_type->appendChild($dom->createTextNode($attribute->getType()));
				$attr->appendChild($att_type);
				
				$values = $dom->createElement("values");
				$valuesArr = $attribute->getValue() ;
				foreach ($valuesArr as $valueData) {
					$value = $dom->createElement("value");
					$value->appendChild($dom->createTextNode($valueData));
					$values->appendChild($value);
				}
				
				$attr->appendChild($values);
				$attrs->appendChild($attr);
			}
			$doc->appendChild($attrs);
		}
		$docs->appendChild($doc);
		return($dom->saveXML());
		
	}
	
	
	/*
	 * setup general curl_opts pass authentication header.
	 */
	function connectionInit($url)
	{
		$ch = curl_init($url);
		curl_setopt($ch, CURLOPT_HTTPAUTH, CURLAUTH_BASIC);
		$str = $this->_user . ":" . $this->_pass;
		curl_setopt($ch, CURLOPT_USERPWD, $str);
		curl_setopt( $ch, CURLOPT_RETURNTRANSFER, true );
		return($ch);
	}
	/*
	 * send the request.
	 * index the documents.
	 */
	function index($log)
	{
		$ch = $this->connectionInit($this->_endpoint);
		
		curl_setopt($ch, CURLOPT_POST, true);
		$payload = $this->toXML();
		$log->LogInfo("sending to server :". $this->_endpoint . ", the request:" . $payload);
		curl_setopt($ch, CURLOPT_POSTFIELDS, $payload);
		curl_setopt($ch, CURLOPT_HTTPHEADER, array(
		    'Content-Type: application/xml',                                                                                
		    'Content-Length: ' . strlen($payload))                                                                       
		);
		
		$result = curl_exec($ch);
		curl_close($ch);
		$resp = parseResponse($result,$log);
		return($resp);
		
	}
	
	/*
	* delete a document from the index.
	* loop over documents array and delete all documents from the index.
	*/
	function delete($docsKeys,$log)
	{
	
	
		foreach ($docsKeys as $key) {
			$docKey = "docKey=" . $key . "&";
				
		}
		$str = $this->_alias . "?" . $docKey;
		$url = $this->_endpoint . $str;
		$ch = $this->connectionInit($url);
		curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "DELETE");
		$result = curl_exec($ch);
		curl_close($ch);
		$resp = parseResponse($result,$log);
		return($resp);
	
	}
	
	function clear($log)
	{
	
	
		$str = "/index/" . $this->_alias  ;
		$url = $this->_endpoint . $str;
		$ch = $this->connectionInit($url);
		curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "DELETE");
		$result = curl_exec($ch);
		curl_close($ch);
		$resp = parseResponse($result,$log);
		return($resp);
	
	}
	

}




?>