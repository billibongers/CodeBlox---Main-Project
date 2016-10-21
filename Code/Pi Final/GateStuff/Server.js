/*
In the node.js intro tutorial (http://nodejs.org/), they show a basic tcp 
server, but for some reason omit a client connecting to it.  I added an 
example at the bottom.
Save the following server in example.js:
*/

var net = require('net');
var x=0;var y=0;


var server = net.createServer(function(socket) {
while(true){
	socket.write('Echo server\r\n'+x);
	x=x+1;y=x;
	while (x<20000){x=x+1;}
	x=y;
	if (x==20000)x=2;
	socket.pipe(socket);
}});

server.listen(1337, 'homeautomation.codeblox.co.za');
