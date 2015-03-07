$(document).ready(function() {
  $('#out').click(function() {
    $('p').slideFadeOut('slow');
    return false;
  });
  $('#in').click(function() {
    $('p').slideFadeIn('slow');
    return false;
  });
  $('#toggle').click(function() {
    $('p').slideFadeToggle('slow');
    return false;
  });
});
