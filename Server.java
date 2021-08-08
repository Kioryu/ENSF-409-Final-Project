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

  public void runApp(RegistrationApp r) throws IOException{

      String output;
			String s;
			String newStudentName;
      while(true){
				s= socketIn.readLine();
				String [] input = s.split(" ");
				if(s.equals("QUIT"))
					break;
      	else if(s.startsWith("1")){
        	output=r.searchCatalogue(input[1],input[2]);
					System.out.println(input[1]+input[2]);
					System.out.println(output);
        	socketOut.println(output+"\nEND\n");
					socketOut.flush();
      	}
      	else if(s.startsWith("2")){
        	output=r.registerCourse(Integer.parseInt(input[5]),input[1],input[2],Integer.parseInt(input[3]),input[4]);
					System.out.println(output);
        	socketOut.println(output);
					socketOut.flush();
        }
      	else if(s.startsWith("3")){
        	output=r.dropCourse(Integer.parseInt(input[3]),input[1],input[2]);
        	socketOut.print(output);
					socketOut.flush();
        }
      	else if(s.startsWith("4")){
        	output=r.displayAllCourses();
        	socketOut.print(output+"\nEND\n");
					socketOut.flush();
        }
      	else if(s.startsWith("5")){
        	output=r.displayStudentCourses(Integer.parseInt(input[1]));
        	socketOut.print(output+"\nEND\n");
					socketOut.flush();
        }
				else if (input[0].equals("11")){
					String studentExist=r.checkStudentRecords(Integer.parseInt(input[1]));
					socketOut.println(studentExist+"\nEND\n");
					socketOut.flush();
				}


				else if (input[0].equals("12")){
					newStudentName=input[1];
				}
      	else socketOut.print("failed");
    	}
  }



	public static void main (String [] args) throws IOException{

		try {
		    Server myServer = new Server(9898);
		    myServer.aSocket = myServer.serverSocket.accept();
        RegistrationApp r= new RegistrationApp ();
		    System.out.println("Connection accepted by server!");
		    myServer.socketIn = new BufferedReader (new InputStreamReader (myServer.aSocket.getInputStream()));
	      myServer.socketOut = new PrintWriter((myServer.aSocket.getOutputStream()), true);
        myServer.runApp(r);
				myServer.socketIn.close();
				myServer.socketOut.close();
			}
			catch (IOException e) {
				e.getStackTrace();
			}



	}

}
