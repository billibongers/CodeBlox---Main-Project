import java.io.*;
import java.net.*;
import java.security.*;

/**
 * Title:        Sample Server
 * Description:  This utility will accept input from a socket, posting back to the socket before closing the link.
 * It is intended as a template for coders to base servers on. Please report bugs to brad at kieser.net
 * Copyright:    Copyright (c) 2002
 * Company:      Kieser.net
 * @author B. Kieser
 * @version 1.0
 */

public class sample_server {

  private static int port=4444, maxConnections=0;static int x;
//String[] open={"ipconfig"};
	//static String[] close={"ipconfig"};
	static String[] close={"sudo python /home/pi/Downloads/Gate\ Server/close.py"};
  // Listen for incoming connections and handle them
  public static void main(String[] args) {
    int i=0;
try {
		Runtime.getRuntime().exec (close);
		x=0;
 		} catch (Exception err) {
        	err.printStackTrace();
  		  }

    try{
      ServerSocket listener = new ServerSocket(port);
      Socket server;

      while((i++ < maxConnections) || (maxConnections == 0)){
        doComms connection;

        server = listener.accept();
        doComms conn_c= new doComms(server);
        Thread t = new Thread(conn_c);
        t.start();
      }
    } catch (IOException ioe) {
      System.out.println("IOException on socket listen: " + ioe);
      ioe.printStackTrace();
    }
  }

}

class doComms implements Runnable {
    private Socket server;
    private String line,input;
int x=0;
//S//tring[] open={"ipconfig"};
//static String[] close={"ipconfig"};
String[] open={"sudo python /home/pi/Downloads/Gate\ Server/open.py"};
String[] close={"sudo python /home/pi/Downloads/Gate\ Server/close.py"};

    doComms(Socket server) {
      this.server=server;
    }

    public void run () {

      input="";

      try {
        // Get input from the client
        DataInputStream in = new DataInputStream (server.getInputStream());
        PrintStream out = new PrintStream(server.getOutputStream());

        while((line = in.readLine()) != null && !line.equals(".")) {
          input=input + line;
          out.println("I got:" + line);
if (x==0){
try {
		Runtime.getRuntime().exec (open);
		x=1;
 		} catch (Exception err) {
        	err.printStackTrace();
  		  }}
else {try {
		Runtime.getRuntime().exec (close);
		x=0;
 		} catch (Exception err) {
        	err.printStackTrace();
  		  }}
        }

        // Now write to the client

        System.out.println("Overall message is:" + input);
        out.println("Overall message is:" + input);

        server.close();
      } catch (IOException ioe) {
        System.out.println("IOException on socket listen: " + ioe);
        ioe.printStackTrace();
      }
    }
}