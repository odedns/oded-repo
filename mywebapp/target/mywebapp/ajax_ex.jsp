<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">

function $(id) { return document.getElementById(id); }

function updateChambers()
{
 var tool = exForm.tools.options[exForm.tools.selectedIndex];
 var chambers = exForm.chambers;
 
 	for(i=0; i < 5; ++i) {
 		chambers.options[i] = new Option("chm-" + tool.text + "-" + i, i,false,false);
 	}
	
}

function updateChambersAjax()
{
	var req = getNewXMLHTTP();
	 req.onreadystatechange  = function()
    { 
         if(req.readyState  == 4)
         {
              if(req.status  == 200) {
               		parseAndUpdate(req.responseText);
               } else { 
               		alert(req.status);
                }
         }
    }; 

   req.open("GET", "http://localhost:8080/myweb/chambers.txt",  true); 
   req.send(null); 
}

function parseAndUpdate(s)
{
	var chambers = exForm.chambers;
	var v = s.split(",");
	
	for(i=0; i < v.length; ++i) {
		chambers.options[i] = new Option(v[i], i,false,false);
	}
}

function getNewXMLHTTP(){
    var res=false;
    try {
       res = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e) {
       try {
           res = new ActiveXObject("Microsoft.XMLHTTP");
       } catch (E) {
       res = false;
       }
    }
    if (!res && typeof XMLHttpRequest!='undefined') {
       res = new XMLHttpRequest();
    }
    return res;
}


function handleInput()
{
	var s = exForm.input.value;
	alert(s);
	var list =document.createElement("select");
	for(i=0; i < 20; ++i) {
		var option = document.createElement("option");
		option.appendChild(document.createTextNode("Foo option"));
		list.appendChild(option);
	}
	${"autoCompListDiv"}.appendChild(list);

}


</script>

<p> Ajax Example

<form id="exForm">
	<br>
		<select id="tools" onclick="updateChambersAjax()">
			<option value="1">tool-1</option>
			<option value="2">tool-2</option>
			<option value="3">tool-3</option>
			
		</select>
	<br>
	<br>
	<br>
	<br>
		<select id="chambers" size="10">
			<option value="1">1</option>
			<option value="2">2</option>
			<option value="3">3</option>
		
		</select>
	
	<br>
	<div id="autoCompListDiv">
	AutoComplete example: <input type="text" name="input" onclick="handleInput()" />
	
	</div>
</form>

</body>
</html>