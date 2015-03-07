jQuery.extend(jQuery.expr[':'], {
  'css': function(element, index, matches, set) {
    var parts = /([\w-]+)\s*([<>=]+)\s*(\d+)/
      .exec(matches[3]);
    var value = parseFloat(jQuery(element).css(parts[1]));

    switch (parts[2]) {
      case '<':
        return value < parseInt(parts[3]);
      case '<=':
        return value <= parseInt(parts[3]);
      case '=':
      case '==':
        return value == parseInt(parts[3]);
      case '>=':
        return value >= parseInt(parts[3]);
      case '>':
        return value > parseInt(parts[3]);
    }
  }
});