// Example A: Loading HTML files
// Step 1
// $(document).ready(function() {
//   $('#letter-a a').click(function() {
//     $('#dictionary').load('a.html');
//     return false;
//   });
// });

// Step 2
$(document).ready(function() {
  $('#letter-a a').click(function() {
    $('#dictionary').hide().load('a.html', function() {
      $(this).fadeIn();
    });
    return false;
  });
});


// Example B: Loading JSON files
// Step 1
// $(document).ready(function() {
//   $('#letter-b a').click(function() {
//     $.getJSON('b.json');
//     return false;
//   });
// });

// Step 2
// $(document).ready(function() {
//   $('#letter-b a').click(function() {
//     $.getJSON('b.json', function(data) {
//       $('#dictionary').empty();
//       $.each(data, function(entryIndex, entry) {
//         var html = '<div class="entry">';
//         html += '<h3 class="term">' + entry['term'] + '</h3>';
//         html += '<div class="part">' + entry['part'] + '</div>';
//         html += '<div class="definition">';
//         html += entry['definition'];
//         html += '</div>';
//         html += '</div>';
//         $('#dictionary').append(html);
//       });
//     });
//     return false;
//   });
// });

// Step 3
$(document).ready(function() {
  $('#letter-b a').click(function() {
    $.getJSON('b.json', function(data) {
      $('#dictionary').empty();
      $.each(data, function(entryIndex, entry) {
        var html = '<div class="entry">';
        html += '<h3 class="term">' + entry['term'] + '</h3>';
        html += '<div class="part">' + entry['part'] + '</div>';
        html += '<div class="definition">';
        html += entry['definition'];
        if (entry['quote']) {
          html += '<div class="quote">';
          $.each(entry['quote'], function(lineIndex, line) {
            html += '<div class="quote-line">' + line + '</div>';
          });
          if (entry['author']) {
            html += '<div class="quote-author">' + entry['author'] + '</div>';
          }
          html += '</div>';
        }
        html += '</div>';
        html += '</div>';
        $('#dictionary').append(html);
      });
    });
    return false;
  });
});


// Example C: Loading JavaScript files
$(document).ready(function() {
  $('#letter-c a').click(function() {
    $.getScript('c.js');
    return false;
  });
});


// Example D: Loading XML files
// Step 1
// $(document).ready(function() {
//   $('#letter-d a').click(function() {
//     $.get('d.xml', function(data) {
//       
//     });
//     return false;
//   });
// });

// Step 2
// $(document).ready(function() {
//   $('#letter-d a').click(function() {
//     $.get('d.xml', function(data) {
//       $('#dictionary').empty();
//       $(data).find('entry').each(function() {
//         var $entry = $(this);
//         var html = '<div class="entry">';
//         html += '<h3 class="term">' + $entry.attr('term')
//           + '</h3>';
//         html += '<div class="part">' + $entry.attr('part')
//           + '</div>';
//         html += '<div class="definition">';
//         html += $entry.find('definition').text();
//         var $quote = $entry.find('quote');
//         if ($quote.length) {
//           html += '<div class="quote">';
//           $quote.find('line').each(function() {
//             html += '<div class="quote-line">'
//               + $(this).text() + '</div>';
//           });
//           if ($quote.attr('author')) {
//             html += '<div class="quote-author">'
//               + $quote.attr('author') + '</div>';
//           }
//           html += '</div>';
//         }
//         html += '</div>';
//         html += '</div>';
//         $('#dictionary').append($(html));
//       });
//     });
//     return false;
//   });
// });

// Step 3
$(document).ready(function() {
  $('#letter-d a').click(function() {
    $.get('d.xml', function(data) {
      $('#dictionary').empty();
      $(data).find('entry:has(quote[author])').each(function() {
        var $entry = $(this);
        var html = '<div class="entry">';
        html += '<h3 class="term">' + $entry.attr('term') + '</h3>';
        html += '<div class="part">' + $entry.attr('part') + '</div>';
        html += '<div class="definition">';
        html += $entry.find('definition').text();
        var $quote = $entry.find('quote');
        if ($quote.length) {
          html += '<div class="quote">';
          $quote.find('line').each(function() {
            html += '<div class="quote-line">' + $(this).text() + '</div>';
          });
          if ($quote.attr('author')) {
            html += '<div class="quote-author">' + $quote.attr('author') + '</div>';
          }
          html += '</div>';
        }
        html += '</div>';
        html += '</div>';
        $('#dictionary').append($(html));
      });
    });
    return false;
  });
});

// Example E: Sending data to the server
// Step 1
// $(document).ready(function() {
//   $('#letter-e a').click(function() {
//     $.get('e.php', {'term': $(this).text()}, function(data) {
//       $('#dictionary').html(data);
//     });
//     return false;
//   });
// });

// Step 2
// $(document).ready(function() {
//   $('#letter-e a').click(function() {
//     $.post('e.php', {'term': $(this).text()}, function(data) {
//       $('#dictionary').html(data);
//     });
//     return false;
//   });
// });

// Step 3
$(document).ready(function() {
  $('#letter-e a').click(function() {
    $('#dictionary').load('e.php', {'term': $(this).text()});
    return false;
  });
});

// Example F: Submitting forms
// Step 1
// $(document).ready(function() {
//   $('#letter-f form').submit(function() {
//     $('#dictionary').load('f.php',
//       {'term': $('input[name="term"]').val()});
//     return false;
//   });
// });

// Step 2
$(document).ready(function() {
  $('#letter-f form').submit(function() {
    $.get('f.php', $(this).serialize(), function(data) {
      $('#dictionary').html(data);
    });
    return false;
  });
});

// Example: Monitoring the request
$(document).ready(function() {
  $('<div id="loading">Loading...</div>')
    .insertBefore('#dictionary')
    .ajaxStart(function() {
      $(this).show();
    }).ajaxStop(function() {
      $(this).hide();
    });
});

// Example: AJAX and Events
// Step 1
// $(document).ready(function() {
//   $('.term').click(function() {
//     $(this).siblings('.definition').slideToggle();
//   });
// });

// Step 2
$(document).ready(function() {
  $('.term').live('click', function() {
    $(this).siblings('.definition').slideToggle();
  });
});

// Example G: JSONP
$(document).ready(function() {
  var url = 'http://examples.learningjquery.com/jsonp/g.php';
  $('#letter-g a').click(function() {
    $.getJSON(url + '?callback=?', function(data) {
      $('#dictionary').empty();
      $.each(data, function(entryIndex, entry) {
        var html = '<div class="entry">';
        html += '<h3 class="term">' + entry['term']
          + '</h3>';
        html += '<div class="part">' + entry['part']
          + '</div>';
        html += '<div class="definition">';
        html += entry['definition'];
        if (entry['quote']) {
          html += '<div class="quote">';
          $.each(entry['quote'], function(lineIndex, line) {
            html += '<div class="quote-line">' + line
              + '</div>';
          });
          if (entry['author']) {
            html += '<div class="quote-author">'
              + entry['author'] + '</div>';
          }
          html += '</div>';
        }
        html += '</div>';
        html += '</div>';
        $('#dictionary').append(html);
      });
    });
    return false;
  });
});

// Example H: Loading of page fragmemts
// Step 1
// $(document).ready(function() {
//   $('#letter-h a').click(function() {
//     $('#dictionary').load('h.html');
//     return false;
//   });
// });

// Step 2
$(document).ready(function() {
  $('#letter-h a').click(function() {
    $('#dictionary').load('h.html .entry');
    return false;
  });
});
