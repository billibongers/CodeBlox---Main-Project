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
var User = require('./../user');
var pinGen = require('./../pinGenerator'); 

//var ps = new Person("Bilal", "Muhammad", "0728787807");
var newUser = new User();
newUser.setUser("John", "Doe", "0123260986", "john@example.com", "8702158963080");
newUser.setAddress("12", "gert", "pretoria", "0001");

describe('User', function(){
	it('Testing getUser() function', function(){
		var expected = newUser.getUser();
		chai.assert(expected == "JohnDoe0123260986john@example.com8702158963080",'You are not getting the correct user back');
	});
});

describe('Address', function(){
	it('Testing getAddress() function', function(){
		var expected = newUser.getAddress();
		chai.assert(expected == "12gertpretoria0001",'You are not getting the correct user back');
	});
});

describe('Active Status', function(){
	it('Testing getStatus function should return active', function(){
		newUser.setEndActivationDate("2017-07-11");
		var expected = newUser.getActivationStatus();
		chai.assert(expected == "Active",'Status not correct');
	});
});


describe('Inactive Status', function(){
	it('Testing getStatus function should return active', function(){
		newUser.setEndActivationDate("2016-07-11");
		var expected = newUser.getActivationStatus();
		chai.assert(expected == "Inactive",'Status not correct');
	});
});

describe('Generating pins', function(){
	it('Testing setPin function should generate 10 pins',function(){
		newUser.setPin();
	})
})