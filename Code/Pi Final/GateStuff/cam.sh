#!/bin/tcsh

set myip=192.168.1.145
set port=5000
set width=320
set height=240

gst-launch\
  v4l2src !\
  ffmpegcolorspace !\!\
  video/x-raw-yuv,width=${width},height=${height},framerate=\(fraction\)30/1 !\
  jpegenc !\
  tcpserversink host=${myip} port=${port} sync=false!\