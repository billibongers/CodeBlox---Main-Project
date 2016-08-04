var user = require('./user');
var util = require('util').inherits;

function Address(_houseNum, _street, _city, _postalcode){

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
	return this.street; 
};
