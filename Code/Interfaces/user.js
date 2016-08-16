/*********************************************************************
	Code version 0.1
	User class
	Authors:
		Bilal Muhammad
		Lethabo Mogase
		Dirk de Klerk
		Lorenzo Spazzoli
	Team:
		CodeBlox
*********************************************************************/

var person = require('./person');
var ActivationStatus = require('./ActivationStatus');
var Address = require('./address'); 
var pinGenerator = require('./pinGenerator');

function User(){

}

User.prototype.idNum = "";

var as = new ActivationStatus();
var per = new person();
var addr = new Address();
var pin = new pinGenerator();

User.prototype.setUser = function(_firstName, _lastName, _cellNumber, _email, _idNum) {
	per.setPerson(_firstName, _lastName, _cellNumber, _email);
	this.idNum = _idNum;
};

User.prototype.getUser = function() {
	return per.getPerson() + this.getID(); 
}

User.prototype.setID = function(_idNum) {
	this.idNum = _idNum; 
};

User.prototype.getID = function(){
	return this.idNum;
};

User.prototype.setEndActivationDate = function(date) {
	as.setActivationEndDate(date);
};

User.prototype.getActivationStatus = function() {
	return as.getStatus();
};

User.prototype.setAddress = function(_houseNum, _street, _city, _postalcode) {
	addr.setAddress(_houseNum, _street, _city, _postalcode); 
};

User.prototype.getAddress = function() {
	return addr.getHouseNum() + addr.getStreet() + addr.getCity() + addr.getpostalcode();
};
User.prototype.setPin = function(){
	return pin.setPin();
};
module.exports = User;