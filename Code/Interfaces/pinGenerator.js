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
var otpGenerator = require('otp-generator')
var pinArray = [10];


function PinGenerator() {

}

PinGenerator.prototype.setPin = function() {

	for(var i = 0; i < 10; i++) {
 		pinArray[i] = otpGenerator.generate(8, {upperCase: false, specialChars: false, alphabets: false });
 		console.log(pinArray[i]); 
	}
}; 
PinGenerator.setPin();
module.exports = PinGenerator;


