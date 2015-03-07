
<?php
class XMLWriterTest extends XMLWriter
{

    /**
     * Constructor.
     * @param string $prm_rootElementName A root element's name of a current xml document
     * @param string $prm_xsltFilePath Path of a XSLT file.
     * @access public
     * @param null
     */
      var $_phrase_id=1;
    public function __construct(){
        $this->openMemory();
        $this->setIndent(true);
        $this->setIndentString(' ');
        $this->startDocument('1.0', 'UTF-8');
        /*
        if($prm_xsltFilePath){
            $this->writePi('xml-stylesheet', 'type="text/xsl" href="'.$prm_xsltFilePath.'"');
        }
        */
        $this->startElement('xliff');
        $this->writeAttribute('version', '1.0');
        $this->startElement('file');
        $this->writeAttribute('original', 'global');
        $this->writeAttribute('source-language', 'es');
        $this->writeAttribute('datatype', 'plaintext');
        $this->writeAttribute('date', date('c'));
        $this->startElement('body');
    }
    public function addPhrase($source, $target){
        $this->startElement('trans-unit');
      $this->writeAttribute('id', $this->_phrase_id++);
        $this->startElement('source');
        $this->text($source);
      $this->endElement();
        $this->startElement('target');
      $this->text($target);
      $this->endElement();
      $this->endElement();
    }
    public function getDocument(){
        $this->endElement();
        $this->endElement();
        $this->endElement();
        $this->endDocument();
        return $this->outputMemory();
    }
    public function output(){
        header('Content-type: text/xml');
        echo $this->getDocument();
    }
}
?>