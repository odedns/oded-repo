/**
 *
 */
var mysql      = require('mysql');
var connection = mysql.createConnection({
  host     : 'localhost',
  user     : 'root',
  password : '',
  database : 'test'
});

connection.connect();

connection.query('SELECT 1 + 1 AS solution', function(err, rows, fields) {
  if (err) throw err;

  console.log('The solution is: ', rows[0].solution);
});

connection.query('SELECT * from departments', function(err, rows, fields) {
	  if (err) throw err;

	  console.log("rows = " + rows);
	  for(var i =0; i < rows.length; ++i) {
		  var r = rows[i];

		  console.log("dept_no=" +r.dept_no);
		  console.log("dept name=" + r.dept_name);


	  }
	});

connection.end();
