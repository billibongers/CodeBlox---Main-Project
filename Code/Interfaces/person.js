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
function Person(){

}

Person.prototype.firstName = "";
Person.prototype.lastName = "";
Person.prototype.cellNumber = "";
Person.prototype.email = "";

Person.prototype.setPerson = function(_firstName, _lastName, _cellNumber, _email){
	this.firstName = _firstName;
	this.lastName = _lastName;
	this.cellNumber = _cellNumber; 
	this.email = _email;
};

Person.prototype.getName = function(){
	return this.firstName;
};

Person.prototype.getLname = function(){
	return this.lastName;
};

Person.prototype.getCell = function(){
	return this.cellNumber;
};

Person.prototype.getEmail = function(){
	return this.email;
};

Person.prototype.getPerson = function(){
	return this.getName() + this.getLname() + this.getCell() + this.getEmail();
};

module.exports = Person;