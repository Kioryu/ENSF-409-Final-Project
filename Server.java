import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private static Socket aSocket;
	private static ServerSocket serverSocket;
	private static  PrintWriter socketOut;
	private static BufferedReader socketIn;


	public Server(int port) {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// The logic of the application:
	public void capitalize() {
		String line = null;

		while (true) {
			try {
				line = socketIn.readLine();
				if (line.equals("QUIT")) {
					line = "Good Bye!";
					socketOut.println(line);
					break;
				}
				line = line.toUpperCase();
				socketOut.println(line);
			} catch (IOException e) {
				e.getStackTrace();
			}
		}
	}

  public void runApp(String s,RegistrationApp r){

      String output;
      if(s.startsWith("1")){
        output=r.searchCatalogue(s.substring(1,4),s.substring(5,7));
        socketOut.println(output);
        return;
      }
      else if(s.startsWith("2")){
        output=r.registerCourse(Integer.parseInt(s.substring(8,s.length()-1)),s.substring(1,4),s.substring(5,7));
        socketOut.println(output);
        return;
      }
      else if(s.startsWith("3")){
        output=r.dropCourse(Integer.parseInt(s.substring(8,s.length()-1)),s.substring(1,4),s.substring(5,7));
        socketOut.println(output);
        return;
      }
      else if(s.startsWith("4")){
        output=r.displayAllCourses();
        socketOut.println(output);
        return;
      }
      else if(s.startsWith("5")){
        output=r.displayStudentCourses(Integer.parseInt(s.substring(1,s.length()-1)));
        socketOut.println(output);
        return;
      }
      else socketOut.println("failed");
    }



	public static void main (String [] args) throws IOException{

		try {
		    Server myServer = new Server(9898);
		    myServer.aSocket = myServer.serverSocket.accept();
        RegistrationApp r= new RegistrationApp ();
		    System.out.println("Connection accepted by server!");
		    myServer.socketIn = new BufferedReader (new InputStreamReader (myServer.aSocket.getInputStream()));
	      myServer.socketOut = new PrintWriter((myServer.aSocket.getOutputStream()), true);

        String strIn="",strOut="";
        strIn=socketIn.readLine();
        myServer.runApp(strIn,r);


		myServer.socketIn.close();
		myServer.socketOut.close();
		}catch (IOException e) {
		e.getStackTrace();
	}



	}

}
