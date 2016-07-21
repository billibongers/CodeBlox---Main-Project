import RPi.GPIO as GPIO
import time
GPIO.setmode(GPIO.BCM)
GPIO.setwarnings(False)
GPIO.setup(18,GPIO.OUT)
GPIO.setup(27,GPIO.OUT)
GPIO.setup(17,GPIO.OUT)
print "LED Red off"
GPIO.output(18,GPIO.LOW)
time.sleep(1)
print "GreenOn"
GPIO.output(27,GPIO.HIGH)
time.sleep(10)
GPIO.output(27,GPIO.LOW)
for letter in 'GoFastAsTheManOnecrsaidinfrcehewasnolongerthequeenofthebishop':
	print "LED on"
	GPIO.output(17,GPIO.HIGH)
	time.sleep(0.1)
	print "LED off"
	GPIO.output(17,GPIO.LOW)
	time.sleep(0.1)
GPIO.output(18,GPIO.HIGH)
