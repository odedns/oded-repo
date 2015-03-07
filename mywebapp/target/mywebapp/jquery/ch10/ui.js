// COLOR ANIMATE

$(document).ready(function() {
  $('#color-animate').click(function() {
 
    // $('#mydiv').animate({
    //   color: '#fff',
    //   backgroundColor: '#000'
    // }, 'slow');

    // $('#mydiv').animate({
    //   color: '#fff',
    //   backgroundColor: '#000'
    // }, 'slow', 'easeInQuart');

    $('#mydiv').animate({
      color: '#fff',
      backgroundColor: '#000'
    }, {
      duration: 'slow', 
      easing: 'easeInQuart'
    });

  });  
});

// ANIMATED TOGGLE CLASS
$(document).ready(function() {
  $('#do-toggle').click(function() {
    $('#toggle-class').toggleClass('highlight', 'slow');
  });
});

// EXPLODE EFFECT
$(document).ready(function() {
  $('#do-explode').click(function() {
    $('#explode').effect('explode', {pieces: 16}, 800);
  });
});

// SORTABLE LIST
$(document).ready(function() {
  // $('#sort-container').sortable();
  
  $('#sort-container').sortable({
    opacity: .5,
    axis: 'y',
    cursor: 'move'
  });
  
});

// DIALOG
$(document).ready(function() {
  // $('#do-dialog').click(function() {
  //   $('#dlg').dialog();
  // });
  var $dlg = $('#dlg');
  var dlgText = $dlg.text();
  $dlg.dialog({
    autoOpen: false,
    title: dlgText,
    open: function() {
        $dlg.empty();
    },
    buttons: {
      'add message': function() {
        $dlg.append('<p>Inserted message</p>');
      },
      'erase messages': function() {
        $('p', $dlg).remove();
      }
    }
  });
  $('#do-dialog').click(function() {
    $dlg.dialog('open');    
  });
    
});
