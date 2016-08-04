var person = require('./person');

function user(_idNum){
	this.idNum = _idNum;
}

user.prototype.getID = function(){
	return this.idNum;
}

module.exports = user;