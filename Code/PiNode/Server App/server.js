var express = require("express");
var app = express();
var bodyParser = require("body-parser");
var net = require('net');
app.use(express.static(__dirname + "/public"));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded());
var router = express.Router();
var socket=net.Socket;
var server = app.listen(process.env.PORT || 3000,function(){
 // var port = server.address().port;
 // console.log("Server running on port: " + port);
});

var server = net.createServer(function(socke) {
socket=socke;
socket.write(''+0);
socket.pipe(socket);
});

server.listen(1337, '192.168.1.104');

//Error handling used by all endpoints
function handleError(res, reason, message, code) {
  console.log("ERROR: " + reason);
  res.status(code || 500).json({"error": message});
}

// Close Gate
app.get('/close', function(req, res){
 var emailadd = req.params.close;
	socket.write(''+0);
	socket.pipe(socket);
});

app.get('/open', function(req, res){
    var emailadd = req.params.open;
    socket.write(''+1);
	socket.pipe(socket);
});
//Open gate
app.post('/registration', function(req, res){
console.log(req.body.email);
socket.write(''+1);
socket.pipe(socket);  
});

