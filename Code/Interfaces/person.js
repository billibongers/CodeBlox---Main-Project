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

function Person(_firstName, _lastName, _cellNumber, _idNumber, _email, _strAddress){
	this.firstName = _firstName;
	this.lastName = _lastName;
	this.cellNumber = _cellNumber;
	this.idNumber = _idNumber;
	this.email = _email;
	this.strAddress = _strAddress; 
};

Person.prototype.getName = function(){
	return "The persons name is " + this.firstName + "\n";
};

Person.prototype.getLname = function(){
	return "The persons last name is " + this.lastName + "\n";
};

Person.prototype.getCell = function(){
	return "The persons cell is " + this.cellNumber + "\n";
};

Person.prototype.getIdNum = function(){
	return "The persons id number is " + this.idNumber + "\n";
};

Person.prototype.getEmail = function(){
	return "The persons email is " + this.email + "\n";
};

Person.prototype.getAddr = function(){
	return "The persons Address is " + this.strAddress + "\n";
};

Person.prototype.getPerson = function(){
	return this.getName() + this.getLname() + this.getCell() + this.getIdNum() + this.getEmail() + this.getAddr();
};

module.exports = Person;