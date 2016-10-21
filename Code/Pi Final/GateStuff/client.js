//Code by Lorenzo Spazzoli for the pi to open and closew the gate by triggering a pin uses open and close pi , please change ip address to relevat addfress before use
var net = require('net');
var sys = require('sys')
var exec = require('child_process').exec;

var client = new net.Socket();
client.connect(1337, '10.8.0.1', function() {
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
else if (data==0)
{
    console.log('Received: A Zero .... ' + data);//Opens pi
     function puts(error, stdout, stderr) { sys.puts(stdout) }
  exec("python close.py", puts);
    }
    else if (data==2)
{
    console.log('Received: A 2 .... ' + data);//Opens pi
     function puts(error, stdout, stderr) { sys.puts(stdout) }
  exec("python clcoff.py", puts);
    }
    else if (data==3)
{
    console.log('Received: A 3 .... ' + data);//Opens pi
     function puts(error, stdout, stderr) { sys.puts(stdout) }
  exec("python opcoff.py", puts);
    }
});

client.on('close', function() {
	console.log('Connection closed');
});