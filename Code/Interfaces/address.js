/*********************************************************************
	Code version 0.1
	Address class
	Authors:
		Bilal Muhammad
		Lethabo Mogase
		Dirk de Klerk
		Lorenzo Spazzoli
	Team:
		CodeBlox
*********************************************************************/

var user = require('./user');

function Address(){

}

Address.prototype.houseNum = "";
Address.prototype.street = "";
Address.prototype.city = "";
Address.prototype.postalcode = "";

Address.prototype.setAddress = function(_houseNum, _street, _city, _postalcode){
	this.houseNum = _houseNum;
	this.street = _street;
	this.city = _city; 
	this.postalcode = _postalcode; 
};

Address.prototype.getHouseNum = function(){
	return this.houseNum; 
};

Address.prototype.getStreet = function(){
	return this.street; 
};

Address.prototype.getCity = function(){
	return this.city; 
};

Address.prototype.getpostalcode = function(){
	return this.postalcode; 
};

module.exports = Address;