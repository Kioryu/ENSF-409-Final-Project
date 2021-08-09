import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
  * A class that handles each thread's run function and logic
  * @author Jian Shi Chen
  * @version 2.0
  * @since August 5,2021
  */
public class RunApp implements Runnable{

  /**
    * output stream to the socket
    */
	private PrintWriter socketOut;

  /**
    * input stream of the socket
    */
  private BufferedReader socketIn;

  /**
    * The registration app object that each thread will use
    */
  private RegistrationApp r;

  /**
    * Constructor for the RunApp class
    * @param in buffered reader for input stream
    * @param out print writer for output stream
    * @regApp registration app created in main for all the threads
    */
	public RunApp (BufferedReader in, PrintWriter out, RegistrationApp regApp) {
		socketIn = in;
		socketOut = out;
    r=regApp;
	}

	@Override

  /**
    * run function of each thread
    * calls server logic
    */
	public void run() {
    serverLogic();
	}

  /**
    * sorts the input stream and calls the corresponding function
    * also collects the information to send to the output stream and flush it out accordingly
    */
  public void serverLogic(){
    String output;
    String s=null;
    String newStudentName;
    while(true){
      try{
        s= socketIn.readLine();
        System.out.println(s);
        String [] input = s.split(" ");
        if(s.equals("QUIT"))
          break;
        else if (input[0].equals("11")){
          if (input[1].equals("123456")){
            socketOut.println("Admin"+"\nEND\n");
            socketOut.flush();
          }
          else{
          String studentExist=r.checkStudentRecords(Integer.parseInt(input[1]));
          socketOut.println(studentExist+"\nEND\n");
          socketOut.flush();
          }
        }
        else if (input[0].equals("12")){
          if (input[1].equals("peanut")){
            socketOut.println("Authenticated"+"\nEND\n");
          }
          else socketOut.println("authetication failed!"+"\nEND\n");
          socketOut.flush();
        }
        else if(input[0].equals("21")){
          output=r.addCourse(input[1].toUpperCase(),Integer.parseInt(input[2]),Integer.parseInt(input[3]),Integer.parseInt(input[4]));
          socketOut.println(output+"\nEND\n");
          socketOut.flush();
        }
        else if(input[0].equals("22")){
          output=r.addStudent(input[1],Integer.parseInt(input[2]));
          socketOut.println(output+"\nEND\n");
          socketOut.flush();
        }
        else if(input[0].equals("69")){
          socketOut.println("Server Died"+"\nEND\n");
          socketOut.flush();
          System.exit(0);
        }
        else if(input[0].equals("1")){
          output=r.searchCatalogue(input[1].toUpperCase(),input[2]);
          System.out.println(input[1]+input[2]);
          System.out.println(output);
          socketOut.println(output+"\nEND\n");
          socketOut.flush();
        }
        else if(input[0].equals("2")){
          output=r.registerCourse(Integer.parseInt(input[5]),input[1].toUpperCase(),input[2],Integer.parseInt(input[3]),input[4]);
          System.out.println(output);
          socketOut.println(output+"\nEND\n");
          socketOut.flush();
        }
        else if(input[0].equals("3")){
          output=r.dropCourse(Integer.parseInt(input[3]),input[1].toUpperCase(),input[2]);
          socketOut.print(output+"\nEND\n");
          socketOut.flush();
        }
        else if(input[0].equals("4")){
          output=r.displayAllCourses();
          socketOut.print(output+"\nEND\n");
          socketOut.flush();
        }
        else if(input[0].equals("5")){
          output=r.displayStudentCourses(Integer.parseInt(input[1]));
          socketOut.print(output+"\nEND\n");
          socketOut.flush();
        }
        else socketOut.print("failed");
      }
      catch(IOException e){
        e.getStackTrace();
      }
    }
  }
}
