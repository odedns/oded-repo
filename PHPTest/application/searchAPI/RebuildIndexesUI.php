<script>
function getXmlHttp() {
    var XMLHttpFactories = [
        function() {return new XMLHttpRequest();},
        function() {return new ActiveXObject("Msxml2.XMLHTTP");},
        function() {return new ActiveXObject("Msxml3.XMLHTTP");},
        function() {return new ActiveXObject("Microsoft.XMLHTTP");},
        function() {return new ActiveXObject("Msxml2.XMLHTTP.3.0");},
        function() {return new ActiveXObject("Msxml2.XMLHTTP.4.0");}
    ];
    var xmlhttp = false, xhr = null;
    for (var i = 0, ii = XMLHttpFactories.length; i < ii; i++) {
        try {
            xhr = XMLHttpFactories[i];
            xmlhttp = xhr();
        }
        catch(e) {
            continue;
        }
        break;
    }
    arguments.callee = function() {
        return xhr();
    };
    return xmlhttp;
}
	function getTagContent(xmlDoc, tagName)
     {
        try
        {
			var value=xmlDoc.getElementsByTagName(tagName)[0].firstChild.nodeValue 
			alert (value);
            return value;
        }
        catch(err)
        {
	        return null;
        }     
    }

	function reloadProgressBar()
	{
		if (window.XMLHttpRequest)
			{
			xmlhttp=new XMLHttpRequest();
			}
		else // for older IE 5/6
		  {
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		  }
		var url="RebuildStatus.xml";
		xmlhttp.open("GET",url,false);
		xmlhttp.send();
		onStateChange();
	}


	 
	
	function  onStateChange()  {
	  if (xmlhttp.readyState==4) {                                                                                      // 4 => loaded complete
		if (xmlhttp.status==200) {                                                                                    // HTTP status code  ( 200 => OK )
	  			var xmlDoc=xmlhttp.responseXML                                                                    // "xmlDoc" the returned xml object
				var resp=xmlDoc;   // The <Date> element's node value  (the sent date)
																										 taskStatusCode = 
	               parseInt (getTagContent (resp, "statusCode"));
	           document.getElementById("taskCurrentStatus").innerHTML = 
	               getTagContent(resp, "name");
	           // Elapsed time
	           document.getElementById("taskElapsedTime").innerHTML = 
	               getTagContent (resp, "currentStatus");
	           // Remaining time
	           document.getElementById("taskTimeRemaining").innerHTML = 
	              getTagContent (resp, "elapsed");
	           // Total pages
	        

			}
	  }

}




	repeatingThingy = window.setInterval("reloadProgressBar()", 5000);	
</script>
<body>
	<div id="reindexstatus" class="functionbox" style="width: 550px">
	  <table width="100%">
	    <tr>
	      <td><b>Status:</b></td>
	      <td><span id="taskCurrentStatus"></span></td>
	    </tr>
	    <tr>
	      <td><b>Time elapsed:</b></td>
	      <td><span id="taskElapsedTime"></span></td>
	    </tr>
	    <tr>
	      <td><b>Time remaining:</b></td>
	      <td><span id="taskTimeRemaining"></span></td>
	    </tr>
	    <tr>
	      <td><b>Completion:</b></td><td><span id="percentComplete"></span></td>
	    </tr>
	    <tr>
	      <td><b>Total pages to index:</b></td>
	      <td><span id="totalPages"></span></td>
	    </tr>
	    <tr>
	      <td><b>Done pages:</b></td>
	      <td><span id="donePages"></span></td>
	    </tr>
	    <tr>
	      <td>&nbsp;</td>
	      <td align="right">
	        <form method="POST" name="$formAction" action="$formAction"
	              onSubmit="return submitClicked (this)">
	          <input id="reindexButton" type="submit" value="Start"
	              onclick="return clickedButton = 'reindexButton'"/>
	          <input id="resumeButton" type="submit" value="Resume"
	              onclick="return clickedButton = 'resumeButton'"/>
	          <input type="hidden" name="action" value="reindex"/> 
	          <input type="hidden" name="start" value="false"/>
	        </form>
	      </td>
	    </tr>
	  </table>
	  
	</div>
</body>
<?php
?>