// Step 1
// $(document).ready(function() {
//   $('h1').shadow();
// });

// Step 2
// $(document).ready(function() {
//   $('h1').shadow(10, 0.1, -1);
// });

// Step 3
// $(document).ready(function() {
//   $('h1').shadow({
//     slices: 5,
//     opacity: 0.25,
//     zIndex: -1
//   });
// });

// Step 4
// $(document).ready(function() {
//   $('h1').shadow({
//     opacity: 0.05
//   });
// });

// Step 5
// $(document).ready(function() {
//   $('h1').shadow({
//     sliceOffset: function(i) {
//       return {x: -i, y: -2*i};
//     }
//   });
// });

// Step 6
$(document).ready(function() {
  $.fn.shadow.defaults.slices = 10;

  $('h1').shadow({
    sliceOffset: function(i) {
      return {x: -i, y: i};
    }
  });
});
