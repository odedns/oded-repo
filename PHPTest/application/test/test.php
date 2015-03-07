<?php

 $undecided = 3.14;
 print gettype( $undecided ); // double
 print " is $undecided\n";  // 3.14
 settype( $undecided, 'string' );
 print gettype( $undecided ); // string
 print " is $undecided\n";  // 3.14
 settype( $undecided, 'integer' );
 print gettype( $undecided ); // integer
 print " is $undecided\n";  // 3
 settype( $undecided, 'double' );
 print gettype( $undecided ); // double
 print " is $undecided\n";  // 3.0
 settype( $undecided, 'boolean' );
 print gettype( $undecided ); // boolean
 print " is $undecided\n";  // 1
 
 
 
 ?>
