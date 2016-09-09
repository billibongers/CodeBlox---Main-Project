//Server to be used with client.js running on a pi and this running on the cloud server to be connected to , change ip address to server url

var net = require('net');

var x=0;var y=0;


var server = net.createServer(function(socket) {
	while (true)
	{
		x=1;
		socket.write(''+x);
		while (x<2000000000){x=x+1;}
		x=0;
		socket.write(''+x);
		while (x<2000000000){x=x+1;}
	}
	
		socket.pipe(socket);
});

server.listen(1337, '192.168.1.104');