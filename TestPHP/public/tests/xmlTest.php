
<?php
require 'XMLWriterTest.php';


$xliff = new XMLWriterTest();
$xliff->addPhrase('source','target');
$xliff->addPhrase('add','añadir');
$xliff->addPhrase('open','abrir');
$xliff->addPhrase('change','cambiar');
$xliff->addPhrase('new','nuevo');
$xliff->addPhrase('save','guardar');

echo $xliff->getDocument();

?>

