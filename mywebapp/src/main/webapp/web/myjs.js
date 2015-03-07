// myjs.js


// submit a form with a parameter
// indicating the operation to be performed.
function submitForm(form,command)
{
	form.operation.value=command;
	form.submit();
} 

// move an entry from a list to a list.
function moveList(leftBox,rightBox)
{
	var pos = rightBox.length;
	//alert('in moveListRight\n selected=' + leftBox.selectedIndex + '\tpos=' + pos);
	if(leftBox.selectedIndex > -1) {
		var optionName=leftBox.options[leftBox.selectedIndex];
		rightBox.options[pos] = new Option(optionName.text,optionName.value,false,false);
		leftBox.options[leftBox.selectedIndex] = null;
	}
}

// open a small selection window.

//function openWin(url)
//{
//	window.open(url,"selectWindow","status,width=400,height=300");

//}
var myWindow;
function openCenteredWindow(url) {
    var width = 400;
    var height = 300;
    var left = parseInt((screen.availWidth/2) - (width/2));
    var top = parseInt((screen.availHeight/2) - (height/2));
    var windowFeatures = "width=" + width + ",height=" + height + 
        ",status,resizable,left=" + left + ",top=" + top + 
        ",screenX=" + left + ",screenY=" + top;
    myWindow = window.open(url, "subWind", windowFeatures);
}

function openWin(url)
{
	var topPos = window.screenY + 30;
	var left = (window.outerWidth - window.screenX) / 2;
	if(left < 0) 
		left = 0;
	if(topPos < 0) 
		topPos = 0;
	alert('top='+ topPos + ' left=' + left);	
	window.open(url,"selectWindow",'status,width=400,height=300,top=' + topPos + 'left=' + left+',center=yes');

}


// get the value of the selcted radio button.
function getSelectedRadio(radio)
{	
	var obj = radio;
	var val = "";
	for(var i=0; i < obj.length; ++i) {
		if(obj[i].checked == true) {
			val = obj[i].value;
			break;
		}
	}
	return(val);
}

// update a parent variable with the 
// selected radio button value.
function updateParent(radio,parentVar)
{
	var val = getSelectedRadio(radio);
	parentVar.value = val;
	self.close();
}


