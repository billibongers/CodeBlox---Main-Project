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

function Person(_firstName, _lastName, _cellNumber){
	this.firstName = _firstName;
	this.lastName = _lastName;
	this.cellNumber = _cellNumber; 
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

Person.prototype.getPerson = function(){
	return this.getName() + this.getLname() + this.getCell();
};

module.exports = Person;