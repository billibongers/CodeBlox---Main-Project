/*********************************************************************
	Code version 0.1
	ActivationStatus class
	Authors:
		Bilal Muhammad
		Lethabo Mogase
		Dirk de Klerk
		Lorenzo Spazzoli
	Team:
		CodeBlox
*********************************************************************/
var moment = require('moment');

function ActivationStatus(){
}

ActivationStatus.prototype.effectiveDate = ""; //the day system was installed
ActivationStatus.prototype.activationEndDate = ""; // the day the current licence will expire
ActivationStatus.prototype.status = "";
ActivationStatus.prototype.reason = "";

ActivationStatus.prototype.setEffectiveDate = function(date){
    this.effectiveDate = date;
}

ActivationStatus.prototype.setActivationEndDate = function(date){
    this.activationEndDate = moment(date);
    this.setStatus();
}

ActivationStatus.prototype.setStatus = function(){
    var date1 = moment();
    var ex = this.activationEndDate.diff(date1, 'days');

    if(ex > 0)
		this.status = "Active";
	else
		this.status = "Inactive";
}

ActivationStatus.prototype.setReason = function(reason){
    this.reason = reason;
}

ActivationStatus.prototype.getActivationEndDate = function(){
    return this.activationEndDate;
}

ActivationStatus.prototype.getStatus = function(){
    return this.status;
}

ActivationStatus.prototype.getReason = function(){
    return this.reason;
}

module.exports = ActivationStatus;
