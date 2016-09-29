//Code by Lorenzo Spazzoli for the pi to open and closew the gate by triggering a pin uses open and close pi , please change ip address to relevat addfress before use
var net = require('net');
var sys = require('sys')
var exec = require('child_process').exec;

var client = new net.Socket();
client.connect(1337, '192.168.1.147', function() {
	console.log('Connected');
	client.write('Hello, server! Love, Client.');
});

client.on('data', function(data) {
if (data==1)
{
	console.log('Received: A one .... ' + data);
  function puts(error, stdout, stderr) { sys.puts(stdout) }
  exec("python open.py", puts);
 }
else
{
    console.log('Received: A Zero .... ' + data);//Opens pi
     function puts(error, stdout, stderr) { sys.puts(stdout) }
  exec("python close.py", puts);
    }
});

client.on('close', function() {
	console.log('Connection closed');
});