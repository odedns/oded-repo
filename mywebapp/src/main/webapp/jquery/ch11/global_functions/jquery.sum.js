jQuery.sum = function(array) {
  var total = 0;
  
  jQuery.each(array, function(index, value) {
    total += value;
  });
  
  return total;
};
