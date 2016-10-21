sudo nohup  sudo openvpn --config  home/pi/GateStuff/client1.ovpn 0<&- &>/dev/null &;
sudo nohup /opt/vc/bin/raspivid -o - -t 0 -hf -w 320 -h 240 -fps 24 |cvlc -vvv stream:///dev/stdin --sout '#standard{access=http,mux=ts,dst=:8090}' :demux=h264 0<&- &>/dev/null &;
sudo nohup nodejs home/pi/GateStuff/client.js 0<&- &>/dev/null &;