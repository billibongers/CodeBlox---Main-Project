//The main resaon for this is for testing of the android application , this will be hosted on the pi to trigger the gate/Box opening
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyServer 
{
	public static void main(String[] args) throws IOException 
	{
		Socket clientSocket = null;
		ServerSocket serverSocket = null;
		try
		{
		serverSocket = new ServerSocket(4444);
		System.out.println("server started....");
		clientSocket = serverSocket.accept();
		}catch(Exception e){} //read & display the message
		//BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		Scanner in = new Scanner(clientSocket.getInputStream());
		String command; 
		while(true){
		if (in.hasNext())
		{
			command=in.nextLine();
			System.out.println("Client Command :"+command);
			
			//Here the command will be executed 
			
			//TO DO
		}
		}
	}

}
