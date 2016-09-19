var currentVal = 0; 		//current value
var old = 0;				//old value
var op = "=";			//previous operation
var isClean = true;			//is the new value clean?

function toString() {
	return "val: " + currentVal.toString() + " old: " + old.toString() + " op: "
		+ op + " isClean: " + isClean.toString(); 
}

function updateDisplay(newChar) {
	var display = document.getElementById("numInput");
	display.placeholder = newChar;
}

function process(c) {
	if(isClear(c)) {

		currentVal = 0;
		old = 0;
		op = '=';
		isClean = true;

	} else if(isDigit(c)) {

		var d = evalDigit(c);
		if(isClean) {
			//start a new value
			old = currentVal;
			currentVal = d;
		} else {
			// add to the existing value
			currentVal = (currentVal * 10) + d;
		}
		isClean = false; 

	} else if(isOp(c)){

		var v = evalOp(op, old, currentVal); 
		if(!isClean){
			//start a new value
			old = currentVal;
		}
		currentVal = v;
		op = c;
		isClean = true;
	}
	updateDisplay(currentVal);
	console.log(toString());
}

function isOp(c){
	switch(c) {
		case '=' : return true;
		case '+' : return true;
		case '-' : return true;
		case 'x' : return true;
	}
	return false;
}

function evalOp(c, m, n){
	switch(c) {
		case '=' : return n; 	//m is the old value, n is the new value
		case '+' : return m + n;
		case '-' : return m - n;
		case 'x' : return m * n;
	}
}

function isDigit(c){
	return c >= 0 && c <= 9;
}

function evalDigit(c) {    
    return c - '0';
}
 
 function isClear(c) {    
    return c == 'c';
}

function handleKeyPress(e) {
	e = e || window.event;

}

window.onload = init;

function init(){

	document.onkeydown = function(evt) {
		evt = evt || window.event;
		handleKeyPress()
	}
}







