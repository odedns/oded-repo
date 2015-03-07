// Step 1

// jQuery.fn.swapClass = function(class1, class2) {
//   if (this.hasClass(class1)) {
//     this.removeClass(class1).addClass(class2);
//   }
//   else if (this.hasClass(class2)) {
//     this.removeClass(class2).addClass(class1);
//   }
// };


// Step 2

// jQuery.fn.swapClass = function(class1, class2) {
//   this.each(function() {
//     var $element = jQuery(this);
//     if ($element.hasClass(class1)) {
//       $element.removeClass(class1).addClass(class2);
//     }
//     else if ($element.hasClass(class2)) {
//       $element.removeClass(class2).addClass(class1);
//     }
//   });
// };


// Step 3

jQuery.fn.swapClass = function(class1, class2) {
  return this.each(function() {
    var $element = jQuery(this);
    if ($element.hasClass(class1)) {
      $element.removeClass(class1).addClass(class2);
    }
    else if ($element.hasClass(class2)) {
      $element.removeClass(class2).addClass(class1);
    }
  });
};
