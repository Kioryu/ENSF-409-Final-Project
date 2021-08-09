import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.net.*;

/**
	* The server
	* @author Jian Shi Chen
	* @version 2.0
	* @since August 5, 2021
	*/
public class Server {

	/**
		* The socket to communicate with client
		*/
	private static Socket aSocket;

	/**
		* the serverSocket for the program
		*/
	private static ServerSocket serverSocket;

	/**
		* The print writer stream for ouputing to socket
		*/
	private static PrintWriter socketOut;

	/**
		* the buffer reader stream for reading input from socket
		*/
	private static BufferedReader socketIn;

	/**
		* The registration app
		*/
	private static RegistrationApp r;

	/**
		* The pool of threads
		*/
	private static ExecutorService pool;

	/**
		* Constructor for the server
		* @param port the port number
		*/
	public Server(int port) {
		try {
			serverSocket = new ServerSocket(port);
				pool = Executors.newFixedThreadPool(5);
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
		* run the application
		* sets up the socketIn and socketOut
		* establish connection with client and assign it to a thread
		* @throws IOException when there is an error from buffered reader or print writer
		*/
  public void runApp() throws IOException{
		try{
			while(true){
			aSocket = serverSocket.accept();
			System.out.println("Connection accepted by server!");
			socketIn = new BufferedReader (new InputStreamReader (aSocket.getInputStream()));
			socketOut = new PrintWriter((aSocket.getOutputStream()), true);
			RunApp runThread = new RunApp(socketIn, socketOut, r);
			pool.execute(runThread);
  		}
		}
		catch (IOException e){
			System.out.println("Server Closing");
		}
	}

	/**
		* the main function for the server side
		* @param args gives the program the ability to pass
		* @throws IOException when there is any issue with the socket streams
		*/

	public static void main (String [] args) throws IOException{

		try {
		    Server myServer = new Server(9898);
				System.out.println("Server Starting");
       	r= new RegistrationApp ();
        myServer.runApp();

				socketIn.close();
				socketOut.close();
			}
			catch (IOException e) {
				e.getStackTrace();
			}
	}

}
