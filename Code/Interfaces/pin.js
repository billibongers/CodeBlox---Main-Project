/*********************************************************************
	Code version 0.1
	Person class
	Authors:
		Bilal Muhammad
		Lethabo Mogase
		Dirk de Klerk
		Lorenzo Spazzoli
	Team:
		CodeBlox
*********************************************************************/
var pinGen = require('./pinGenerator'); 
var newPin = ""; 
Boolean used = false; 

function Pin() {
	newPin = new pinGenerator();
}

Pin.prototype.getStatus = function(){
	return used; 
};

Pin.prototype.setStatus = function(){
	used = true; 
};