#usrscript


function getScriptAsText(){
	var div = document.createElement('div');
	var scripts = [];
	var scriptNodes = docmunet.getElementsByTagName('script');
	
	for (var i=0, iLen=scriptNodes.length; i<iLen; i++){
	div.appendChild(scriptNodes[i].cloneNode(true));
	scripts.push(div.innerHTML);
	div.removeChild(div.firstChild);
}

return scripts;


//jmiller