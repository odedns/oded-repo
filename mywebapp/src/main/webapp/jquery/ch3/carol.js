// Step 1
// $(document).ready(function() {
//   $('#switcher-large').bind('click', function() {
//     $('body').addClass('large');
//   });
// });

// Step 2
// $(document).ready(function() {
//   $('#switcher-default').bind('click', function() {
//     $('body').removeClass('narrow');
//     $('body').removeClass('large');
//   });
//   $('#switcher-narrow').bind('click', function() {
//     $('body').addClass('narrow');
//     $('body').removeClass('large');
//   });
//   $('#switcher-large').bind('click', function() {
//     $('body').removeClass('narrow');
//     $('body').addClass('large');
//   });
// });

// Step 3
// $(document).ready(function() {
//   $('#switcher-default').bind('click', function() {
//     $('body').removeClass('narrow');
//     $('body').removeClass('large');
//     $('#switcher .button').removeClass('selected');
//     $(this).addClass('selected');
//   });
//   $('#switcher-narrow').bind('click', function() {
//     $('body').addClass('narrow');
//     $('body').removeClass('large');
//     $('#switcher .button').removeClass('selected');
//     $(this).addClass('selected');
//   });
//   $('#switcher-large').bind('click', function() {
//     $('body').removeClass('narrow');
//     $('body').addClass('large');
//     $('#switcher .button').removeClass('selected');
//     $(this).addClass('selected');
//   });
// });

// Step 4
// $(document).ready(function() {
//   $('#switcher-default').bind('click', function() {
//     $('body').removeClass('narrow').removeClass('large');
//   });
//   $('#switcher-narrow').bind('click', function() {
//     $('body').addClass('narrow').removeClass('large');
//   });
//   $('#switcher-large').bind('click', function() {
//     $('body').removeClass('narrow').addClass('large');
//   });
// 
//   $('#switcher .button').bind('click', function() {
//     $('#switcher .button').removeClass('selected');
//     $(this).addClass('selected');
//   });
// });

// Step 5
// $(document).ready(function() {
//   $('#switcher-default').bind('click', function() {
//     $('body').removeClass();
//   });
//   $('#switcher-narrow').bind('click', function() {
//     $('body').removeClass().addClass('narrow');
//   });
//   $('#switcher-large').bind('click', function() {
//     $('body').removeClass().addClass('large');
//   });
// 
//   $('#switcher .button').bind('click', function() {
//     $('#switcher .button').removeClass('selected');
//     $(this).addClass('selected');
//   });
// });

// Step 6
// $(document).ready(function() {
//   $('#switcher .button').bind('click', function() {
//     $('body').removeClass();
//     $('#switcher .button').removeClass('selected');
//     $(this).addClass('selected');
//   });
// 
//   $('#switcher-narrow').bind('click', function() {
//     $('body').addClass('narrow');
//   });
//   $('#switcher-large').bind('click', function() {
//     $('body').addClass('large');
//   });
// });

// Step 7
// $(document).ready(function() {
//   $('#switcher .button').bind('click', function() {
//     $('body').removeClass();
//     if (this.id == 'switcher-narrow') {
//       $('body').addClass('narrow');
//     }
//     else if (this.id == 'switcher-large') {
//       $('body').addClass('large');
//     }
//     
//     $('#switcher .button').removeClass('selected');
//     $(this).addClass('selected');
//   });
// });

// Step 8
// $(document).ready(function() {
//   $('#switcher .button').click(function() {
//     $('body').removeClass();
//     if (this.id == 'switcher-narrow') {
//       $('body').addClass('narrow');
//     }
//     else if (this.id == 'switcher-large') {
//       $('body').addClass('large');
//     }
//     
//     $('#switcher .button').removeClass('selected');
//     $(this).addClass('selected');
//   });
// });

// Step 9
// $(document).ready(function() {
//   $('#switcher h3').toggle(function() {
//     $('#switcher .button').addClass('hidden');
//   }, function() {
//     $('#switcher .button').removeClass('hidden');
//   });
// });

// Step 10
// $(document).ready(function() {
//   $('#switcher h3').click(function() {
//     $('#switcher .button').toggleClass('hidden');
//   });
// });

// Step 11
// $(document).ready(function() {
//   $('#switcher .button').hover(function() {
//     $(this).addClass('hover');
//   }, function() {
//     $(this).removeClass('hover');
//   });
// });

// Step 12
// $(document).ready(function() {
//   $('#switcher').click(function() {
//     $('#switcher .button').toggleClass('hidden');
//   });
// });

// Step 13
// $(document).ready(function() {
//   $('#switcher').click(function(event) {
//     if (event.target == this) {
//       $('#switcher .button').toggleClass('hidden');
//     }
//   });
// });

// Step 14
// $(document).ready(function() {
//   $('#switcher').click(function() {
//     $('#switcher .button').toggleClass('hidden');
//   });
// });
// 
// $(document).ready(function() {
//   $('#switcher .button').click(function(event) {
//     $('body').removeClass();
//     if (this.id == 'switcher-narrow') {
//       $('body').addClass('narrow');
//     }
//     else if (this.id == 'switcher-large') {
//       $('body').addClass('large');
//     }
//     
//     $('#switcher .button').removeClass('selected');
//     $(this).addClass('selected');
//     event.stopPropagation();
//   });
// });

// Step 15
// $(document).ready(function() {
//   $('#switcher').click(function(event) {
//     if (!$(event.target).is('.button')) {
//       $('#switcher .button').toggleClass('hidden');
//     }
//   });
// });
// 
// $(document).ready(function() {
//   $('#switcher').click(function(event) {
//     if ($(event.target).is('.button')) {
//       $('body').removeClass();
//       if (event.target.id == 'switcher-narrow') {
//         $('body').addClass('narrow');
//       }
//       else if (event.target.id == 'switcher-large') {
//         $('body').addClass('large');
//       }
//     
//       $('#switcher .button').removeClass('selected');
//       $(event.target).addClass('selected');
//     }
//   });
// });

// Step 16
// $(document).ready(function() {
//   $('#switcher').click(function(event) {
//     if (!$(event.target).is('.button')) {
//       $('#switcher .button').toggleClass('hidden');
//     }
//   });
//   
//   $('#switcher-narrow, #switcher-large').click(function() {
//     $('#switcher').unbind('click');
//   });
// });

// Step 17
// $(document).ready(function() {
//   $('#switcher').bind('click.collapse', function(event) {
//     if (!$(event.target).is('.button')) {
//       $('#switcher .button').toggleClass('hidden');
//     }
//   });
//   
//   $('#switcher-narrow, #switcher-large').click(function() {
//     $('#switcher').unbind('click.collapse');
//   });
// });

// Step 18
// $(document).ready(function() {
//   var toggleStyleSwitcher = function(event) {
//     if (!$(event.target).is('.button')) {
//       $('#switcher .button').toggleClass('hidden');
//     }
//   };
//   
//   $('#switcher').bind('click.collapse', toggleStyleSwitcher);
//   
//   $('#switcher-narrow, #switcher-large').click(function() {
//     $('#switcher').unbind('click.collapse');
//   });
// });

// Step 19
// $(document).ready(function() {
//   var toggleStyleSwitcher = function(event) {
//     if (!$(event.target).is('.button')) {
//       $('#switcher .button').toggleClass('hidden');
//     }
//   };
//   
//   $('#switcher').bind('click.collapse', toggleStyleSwitcher);
//   
//   $('#switcher-narrow, #switcher-large').click(function() {
//     $('#switcher').unbind('click.collapse');
//   });
//   $('#switcher-default').click(function() {
//     $('#switcher').bind('click.collapse', toggleStyleSwitcher);
//   });
// });

// Step 20
// $(document).ready(function() {
//   var toggleStyleSwitcher = function(event) {
//     if (!$(event.target).is('.button')) {
//       $('#switcher .button').toggleClass('hidden');
//     }
//   };
//   
//   $('#switcher').click(toggleStyleSwitcher);
//   
//   $('#switcher-narrow, #switcher-large').click(function() {
//     $('#switcher').unbind('click', toggleStyleSwitcher);
//   });
//   $('#switcher-default').click(function() {
//     $('#switcher').click(toggleStyleSwitcher);
//   });
// });

// Step 21
// $(document).ready(function() {
//   $('#switcher').trigger('click');
// });

// Step 22
// $(document).ready(function() {
//   $('#switcher').click();
// });

// Step 22
// $(document).ready(function() {
//   $(document).keyup(function(event) {
//     switch (String.fromCharCode(event.keyCode)) {
//       case 'D':
//         $('#switcher-default').click();
//         break;
//       case 'N':
//         $('#switcher-narrow').click();
//         break;
//       case 'L':
//         $('#switcher-large').click();
//         break;
//     }
//   });
// });

// Finished product

$(document).ready(function() {
  // Enable hover effect on the style switcher buttons.
  $('#switcher .button').hover(function() {
    $(this).addClass('hover');
  }, function() {
    $(this).removeClass('hover');
  });

  // Allow the style switcher to expand and collapse.
  var toggleStyleSwitcher = function(event) {
    if (!$(event.target).is('.button')) {
      $('#switcher .button').toggleClass('hidden');
    }
  };
  $('#switcher').click(toggleStyleSwitcher);

  // Simulate a click so we start in a collaped state.
  $('#switcher').click();
  
  // The setBodyClass() function changes the page style.
  // The style switcher state is also updated.
  var setBodyClass = function(className) {
    $('body').removeClass();
    $('body').addClass(className);
    $('#switcher .button').removeClass('selected');
    $('#switcher-' + className).addClass('selected');
    
    if (className == 'default') {
      $('#switcher').click(toggleStyleSwitcher);
    }
    else {
      $('#switcher').unbind('click', toggleStyleSwitcher);
      $('#switcher .button').removeClass('hidden');
    }
  };

  // Invoke setBodyClass() when a button is clicked.
  $('#switcher').click(function(event) {
    if ($(event.target).is('.button')) {
      if (event.target.id == 'switcher-default') {
        setBodyClass('default');
      }
      if (event.target.id == 'switcher-narrow') {
        setBodyClass('narrow');
      }
      else if (event.target.id == 'switcher-large') {
        setBodyClass('large');
      }
    }
  });

  // Invoke setBodyClass() when a key is pressed.
  $(document).keyup(function(event) {
    switch (String.fromCharCode(event.keyCode)) {
      case 'D':
        setBodyClass('default');
        break;
      case 'N':
        setBodyClass('narrow');
        break;
      case 'L':
        setBodyClass('large');
        break;
    }
  });
});

