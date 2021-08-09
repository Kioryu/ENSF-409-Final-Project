package Server;

import java.io.RandomAccessFile;
import java.io.*;
import java.util.ArrayList;

/**
	* This class is simulating a database for our program.
	* @author Maan Khedr
	* @version 1.0
	* @since August 3, 2021
	*/
public class DBManager {

	/**
		* The arraylist containing all the course listings
		*/
	private ArrayList <Course> courseList;

	/**
		* The file to be read and written to
		*/
		private  File  fileIN;


	  /**
	    * A random access file pointer
	    */
	  private RandomAccessFile ra=null;

	/**
		* Constructor for the DBManager
		*/
	public DBManager () {
		fileIN= new File ("AllCourses.dat");
		courseList = new ArrayList<Course>();
		initialsetupCourseData();
	}



	  /**
	    * To set up the course data file the first time
	    */
	  public void initialsetupCourseData(){
	    if(! fileIN.exists() || ! fileIN.isFile())
	    {
	    try{
	      ra= new RandomAccessFile("AllCourses.dat","rw");
	      addCourseEntry(ra);
	    }
	    catch(IOException e){
	      System.out.println("error opening file");
	    }
	    try{
	      ra.close();
	    }catch(IOException e){
	      System.out.println("error closing file");
	    }
	  }
	  else readFile();
	  }

	  /**
	    * Add course entry when setting up pthe AllCourses.dat file
	    * @param r the random access file pointing to the beginning of the AllCourses.dat file
	    * @throws IOException when there is an error from the scanner
	    */
	  public void addCourseEntry(RandomAccessFile r) throws IOException{
	    String cont = "Y";
	    RandomAccessFile ra=r;
	    StringBuffer name= new StringBuffer("ENGG");
	    name.setLength(4);
	    ra.writeChars(name.toString());
			ra.writeInt(233);
			name= new StringBuffer("ENSF");
			name.setLength(4);
			ra.writeChars(name.toString());
			ra.writeInt(409);
			name= new StringBuffer("PHYS");
			name.setLength(4);
			ra.writeChars(name.toString());
			ra.writeInt(259);
			name= new StringBuffer("ENGG");
			name.setLength(4);
			ra.writeChars(name.toString());
			ra.writeInt(201);
			name= new StringBuffer("ENCM");
			name.setLength(4);
			ra.writeChars(name.toString());
			ra.writeInt(237);
			name= new StringBuffer("ENCM");
			name.setLength(4);
			ra.writeChars(name.toString());
			ra.writeInt(335);
			name= new StringBuffer("ENEL");
			name.setLength(4);
			ra.writeChars(name.toString());
			ra.writeInt(353);
			courseList.add(new Course ("ENGG", 233));
			courseList.add(new Course ("ENSF", 409));
			courseList.add(new Course ("PHYS", 259));
			courseList.add(new Course ("ENGG", 201));
			courseList.add(new Course ("ENCM", 237));
			courseList.add(new Course ("ENCM", 335));
			courseList.add(new Course ("ENEL", 353));
	   }

	    /**
	      * Reads the AllCourses.dat file
	      */
	    public void readFile(){
	      if(fileIN.exists()){
	        try{
	          ra=new RandomAccessFile("AllCourses.dat","rw");
	        }
	        catch(IOException e){
	          System.out.println("error opening file");
	        }
	        try{
	          while (true){
	            char scanName[]= new char[4];
	            for (int nameCharacter=0;nameCharacter<4;nameCharacter++)
	              scanName[nameCharacter]=ra.readChar();
	            String outputName= new String(scanName);
	            int outputID = ra.readInt();
	            courseList.add(new Course(outputName,outputID));
	          }
	        }
	        catch(EOFException e){
	     
	        }
	        catch(IOException e){
	          System.out.println("GG");
	        }
	        try{
	          ra.close();
	        }
	        catch(IOException e){
	          System.out.println("error closing file");
	        }
	      }
	    }



	  /**
	    * add a new courses to the file
	    * @param c new Course to be added
	    */
	  public void addNewCourse(Course c){
	    System.out.println("writring to file") ;
	    courseList.add(c);
	    try{
	      RandomAccessFile r=new RandomAccessFile("AllCourses.dat","rw");
	      r.seek(12*(courseList.size()-1));
	      StringBuffer cn= new StringBuffer (c.getCourseName());
	      cn.setLength(4);
	      r.writeChars(cn.toString());
	      int courseNumber= c.getCourseNumber();
	      r.writeInt(courseNumber);
	      System.out.println("finished writing");
	      r.close();
	    }
	    catch(IOException e){
	      System.out.println("error");
	    }
	  }

		/**
			* getter for courseList
			* @return courselist
			*/

	public ArrayList readFromDataBase() {
		// TODO Auto-generated method stub

		return courseList;
	}
}
