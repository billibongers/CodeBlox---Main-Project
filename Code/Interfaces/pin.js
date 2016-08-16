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
var newPin = new pinGen();
var newPinID = ""; 
var used = false; 

function Pin() {
	newPinID = newPin.getPin()
}

Pin.prototype.getStatus = function(){
	return used; 
};

Pin.prototype.setStatus = function(){
	used = true;
};

Pin.prototype.getPin = function(){
	return newPinID;
};

module.exports = Pin;