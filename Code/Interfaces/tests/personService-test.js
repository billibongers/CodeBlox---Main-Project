/*********************************************************************
	test version 0.1
	PersonServices Test
	Authors:
		Bilal Muhammad
		Lethabo Mogase
		Dirk de Klerk
		Lorenzo Spazzoli
	Team:
		CodeBlox
*********************************************************************/

var chai = require('chai');
var expect = chai.expect;
var Person = require('./../person');
var ps = new Person("Bilal", "Muhammad", "0728787807");

describe('Person', function(){
	it('Testing getPerson() function', function(){
		var expected = ps.getPerson();
		console.log(expected);
		chai.assert(expected == ps.getName() + ps.getLname() + ps.getCell(),'You are not getting the correct person back');
	});
});
