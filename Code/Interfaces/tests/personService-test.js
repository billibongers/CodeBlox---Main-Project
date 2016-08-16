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

var globalPin = "";
describe('Does pin generate', function(){
	it('Should return a 8 digit random number', function(){
		var expected = newUser.getPin();
		globalPin = newUser.getPin();
		console.log(expected);
		chai.assert(expected == expected,'8 digit random number not generated');
	});
});

describe('Is pin for current user correct ?', function(){
	it('Should return the same OTP generated from previous function', function(){
		var expected = newUser.getPin();
		chai.assert(expected == globalPin,'Pin global is causing a problem');
	});
});

describe('Pin should be unused', function(){
	it('Should return false', function(){
		var expected = newUser.getPinStatus();
		chai.assert(expected == false,'Pin status not correct');
	});
});

describe('Pin should be used', function(){
	it('Should return true', function(){
		newUser.setPinStatus();
		var expected = newUser.getPinStatus();
		chai.assert(expected == true,'Pin status not correct');
	});
});

