//Code by Lorenzo Spazzoli for the pi to open and closew the gate by triggering a pin uses open and close pi , please change ip address to relevat addfress before use
var net = require('net');
var sys = require('sys')
var exec = require('child_process').exec;
var sleep = require('sleep');

var Gpio = require('onoff').Gpio,
//buzzer = new Gpio(17, 'out'),
button = new Gpio(14, 'in', 'both');
switch1 = new Gpio(23, 'out');
switch2 = new Gpio(24, 'out');
switch3 = new Gpio(25, 'out');
switch4 = new Gpio(8, 'out');
switch5 = new Gpio(7, 'out');
switch6 = new Gpio(12, 'out');
switch7 = new Gpio(16, 'out');

var client = new net.Socket();
client.connect(1337, '10.8.0.1', function() {
	console.log('Connected');
	client.write('Hello, server! Love, Client.');
});

client.on('data', function(data) {

if (data==0)
{
    console.log('Received: A Zero .... ' + data);//Opens pi
     function puts(error, stdout, stderr) { sys.puts(stdout) }
  exec("python close.py", puts);
    }
    else if (data==1)
{
	console.log('Received: A one .... ' + data);
  function puts(error, stdout, stderr) { sys.puts(stdout) }
  exec("python open.py", puts);
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
    else if (data==4){ switch1.writeSync(1); 	console.log('aerfgsfded');}//Switch ++
      else if (data==5) {switch1.writeSync(0);	console.log('Cosdergdsfgnnected');}
        else if (data==6){ switch2.writeSync(1);}
          else if (data==7){switch2.writeSync(0);}  
          else if (data==8){ switch3.writeSync(1);}
            else if (data==9){switch3.writeSync(0);}
              else if (data==10){ switch4.writeSync(1);}
                else if (data==11){switch4.writeSync(0);}
                  else if (data==12){ switch5.writeSync(1);}
                    else if (data==13){switch5.writeSync(0);}
                      else if (data==14){ switch6.writeSync(1);	console.log('Coargfsdfgfsgdnnected');}
                        else if (data==15){switch6.writeSync(0);	console.log('Constdghdfghdfghnected');}
                          else if (data==16){ switch7.writeSync(1);}
                            else if (data==17){switch7.writeSync(0);}
                            
});

client.on('close', function() {
	console.log('Connection closed');
});

button.watch(function(err, value) {
  if (err) exit();
 
  if (value==1)
  {
  console.log('Sent Server a 69 mmm yeah');
   client.write('69');
    sleep.sleep(2)//sleep for  seconds
  }
  else { console.log(value);}
  //buzzer.writeSync(value);
});

function exit() {
  //buzzer.unexport();
  button.unexport();
  process.exit();
}

process.on('SIGINT', exit);