// Step 1

// $(document).ready(function() {
//   $('.target').grandparent().addClass('highlight');
// });


// Step 2

// $(document).ready(function() {
//   var $target = $('.target');
//   $target.grandparent().addClass('highlight');
//   $target.hide();
// });


// Step 3

$(document).ready(function() {
  $('.target').grandparent().andSelf().addClass('highlight');
});
