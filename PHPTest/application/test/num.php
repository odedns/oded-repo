<?php
$num1 = "32.45";
$num2 = "33.94";

// If the scale argument (the third argument) is not
// given then all of the bcmath functions will use
// an accuracy of 10 decimal places
bcscale (10);

// $num1 + $num2 accurate to four decimal places
$sum = bcadd ($num1, $num2, 4);

// $num1 - $num2 accurate to 10 decimal places
// (from the default scale, set by bcscale()).
$difference = bcsub ($num1, $num2);

// $num1 * $num2 accurate to 6 decimal places
$multi = bcmul ($num1, $num2, 6);

// $num1 / $num2 accurate to 7 decimal places
$div = bcdiv ($num1, $num2, 7);

// square root of $num1 accurate to 12 decimal places
$sqrt1 = bcsqrt ($num1, 12);

// $num12 accurate to 20 decimal places
// This only works when the power is a whole number
$exp = bcpow ($num1, 2, 20);

// modulus of $num1 and $num2 accurate to 20 decimal places
$mod = bcmod($num1, $num2);

// Compare $num1 and $num2 accurate to 4 decimal places
if (!bccomp ($num1, $num2, 4))
    print '$num1 == $num2';
else
    print '$num1 != $num2';
?>

