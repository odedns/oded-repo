// Step 1

// jQuery.fn.grandparent = function() {
//   var grandparents = [];
//   this.each(function() {
//     grandparents.push(this.parentNode.parentNode);
//   });
//   grandparents = jQuery.unique(grandparents);
//   return this.setArray(grandparents);
// };


// Step 2

jQuery.fn.grandparent = function() {
  var grandparents = [];
  this.each(function() {
    grandparents.push(this.parentNode.parentNode);
  });
  grandparents = jQuery.unique(grandparents);
  return this.pushStack(grandparents);
};
