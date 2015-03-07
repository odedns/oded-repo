$(document).ready(function() {
  var myArray = [52, 97, 0.5, -22];
  
  $.each(myArray, function(index, value) {
    $('#array-contents').append('<li>' + value + '</li>');
  });
  
  $('#array-sum').append($.sum(myArray));
});
