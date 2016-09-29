To Start the camera Feed run 

/opt/vc/bin/raspivid -o - -t 0 -hf -w 640 -h 360 -fps 25|cvlc -vvv stream:///dev/stdin --sout '#standard{access=http,mux=ts,dst=:8090}' :demux=h264

found at :   

http://raspberrypi.stackexchange.com/questions/27082/how-to-stream-raspivid-to-linux-and-osx-using-gstreamer-vlc-or-netcat

To start the gate system run node client.js
-open and close scripts are based off the arduino 
-triggered system by turning on and off GPIO pin 15
- they can be run using python open.py and python close.py
- can be testing with attaching an LED to GPIO pin 15 and negative