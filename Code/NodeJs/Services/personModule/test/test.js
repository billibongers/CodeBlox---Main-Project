var supertest = require("supertest");
var should = require("should");
var chai = require('chai');
//var personRegister = require('./../public/controllers/registerCtrl');

// This agent refers to PORT where program is runninng.

var server = supertest.agent("http://localhost:3000");

// UNIT test begin

describe("Connection to server",function(){

  // #1 should return home page

  it("should return page",function(done){

    // calling home page api
    server.get('/')
    .expect("Content-type",/json/)
    .expect(200).end(function(err,res){
      //Get the value of the status
      var expected = res.status;
      
      // HTTP status should be 200 
      chai.assert(expected == "200", "You are unable to load a page");
      // Error key should be false.
      //res.body.error.should.equal(false);
      done();
    });
  });

  it("should return 404",function(done){
    server
    .get("/random")
    .expect(404)
    .end(function(err,res){
      res.status.should.equal(404);
      done();
    });
  });


});


describe("Add user",function(){
  var personObj = {fname: 'Bilal', 
        lname: 'Muhammad', 
        id: '0000000000000', 
        email: 'b@gmail.com', 
        password1: '12345678', 
        password2: '12345678', 
        _id: 55445};
  server
  .post('/registration/')
  .send(personObj)
  .expect("Content-type",/json/)
  .expect(200)
  .end(function(err, res){
    console.log("Errors occurred: " + err);
    console.log("The response was: " + res.status);
    res.status.should.equal(200);
  });
});

describe("Getting user email",function(){

  // #1 should return home page

  it("should return b@gmail.com",function(done){

    // calling home page api
    server.get('/returnUser/:email')
    .expect("Content-type",/json/)
    .expect(200).end(function(err,res){
      //Get the value of the status
      //console.log(res);
      var expected = res.status;
      
      // HTTP status should be 200 
      chai.assert(expected == "200", "You are unable to load a page");
      // Error key should be false.
      //res.body.error.should.equal(false);
      done();
    });
  });
});



