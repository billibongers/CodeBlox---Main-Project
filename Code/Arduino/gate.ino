
#include <Servo.h>

Servo myservo;  // create servo object to control a servo
// twelve servo objects can be created on most boards

int pos = 0;    // variable to store the servo position
int ByteReceived;//var for getting input from serial pins

void setup() {
  myservo.attach(9);  // attaches the servo on pin 9 to the servo object
myservo.write(pos);  
//led 
pinMode(12, OUTPUT);
  //Setup serial
  Serial.begin(9600);  
  Serial.println("--- Start Serial Monitor SEND_RCVE ---");
    Serial.println(" Type in Box above, . ");
  Serial.println("(Decimal)(Hex)(Character)");  
  Serial.println();
}

void loop() {


  if (Serial.available() > 0)
  {
    ByteReceived = Serial.read();
    Serial.print(ByteReceived); 
    if (ByteReceived==48) close();//0
    else if (ByteReceived==49)open();}//1
}
void close(){
  if ( pos >= 120){
  digitalWrite(12, LOW);
  for (pos = 120; pos >= 0; pos -= 1) { // goes from 180 degrees to 0 degrees
    myservo.write(pos);              // tell servo to go to position in variable 'pos'
    delay(45);                       // waits 15ms for the servo to reach the position
  }}
  }
void open()
{if ( pos <= 0){
  for (pos = 0; pos <= 120; pos += 1) { // goes from 0 degrees to 180 degrees
    // in steps of 1 degree
    myservo.write(pos);              // tell servo to go to position in variable 'pos'
    delay(45);
    digitalWrite(12, HIGH);}// waits 15ms for the servo to reach the position
  } 
}
