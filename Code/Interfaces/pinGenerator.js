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
var otpGenerator = require('otp-generator');
var pinReturn = "";

function PinGenerator() {
	pinReturn = otpGenerator.generate(8, {upperCase: false, specialChars: false, alphabets: false });
}

PinGenerator.prototype.getPin = function(){
	return pinReturn;
};

module.exports = PinGenerator;


