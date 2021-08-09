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
	ArrayList <Course> courseList;

	/**
		* Constructor for the DBManager
		*/
	public DBManager () {
		courseList = new ArrayList<Course>();
	}

	/**
		* setup the course list
		* @return the course list
		*/
	public ArrayList readFromDataBase() {
		// TODO Auto-generated method stub
		courseList.add(new Course ("ENGG", 233));
		courseList.add(new Course ("ENSF", 409));
		courseList.add(new Course ("PHYS", 259));
		courseList.add(new Course ("ENGG", 201));
		courseList.add(new Course ("ENCM", 237));
		courseList.add(new Course ("ENCM", 335));
		courseList.add(new Course ("ENEL", 353));
		return courseList;
	}
}
