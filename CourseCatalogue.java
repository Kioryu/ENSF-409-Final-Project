import java.util.ArrayList;

/**
	* Stores all the courses that the school offers.
	* @author Aaron Li, Jian Shi Chen
	* @version 1.0
	* @since August 3, 2021
	*/

public class CourseCatalogue {

	/**
		* stores all the course in an ArrayList
		*/
	private ArrayList <Course> courseList;


	/**
		* Constructor for CourseCatalogue.
		* load courses from a database.
		*/
	public CourseCatalogue () {
		loadFromDataBase ();
	}

	/**
		* creates a database object and obtain courselist by reading the database.
		*/
	private void loadFromDataBase() {
		// TODO Auto-generated method stub
		DBManager db = new DBManager();
		setCourseList(db.readFromDataBase());
		initializeSectionOffering();
	}

	/**
		* Create section offering and add the offering to the course
		* @param c Course object that will get new section addOffering
		* @param sectionNum the section number of course addOffering
		* @param sectionCap the maximum capacity of the section offering
		*/
	public void createSectionOffering (Course c, int sectionNum, int sectionCap) {
		if (c!= null) {
			SectionOffering theOffering = new SectionOffering (sectionNum, sectionCap);
			c.addOffering(theOffering);
		}
	}

	public void initializeSectionOffering(){
		for (int i=0;i<courseList.size();i++){
			createSectionOffering(courseList.get(i),1,10);
			createSectionOffering(courseList.get(i),2,10);
		}
	}

	/**
		* Search the course catalogue by course name and course number.
		* @param courseName the String code for the course to search for
		* @param courseNumber the integer code for the course to search for
		* @return Course object if found, else returns null and displays error message
		*/
	public Course searchCatalogue (String courseName, int courseNumber) {
		for (Course c : courseList) {
			if (courseName.equals(c.getCourseName()) &&
					courseNumber == c.getCourseNumber()) {
				return c;
			}
		}
		displayCourseNotFoundError();
		return null;
	}

	/**
		* displays course not found
		*/
	private void displayCourseNotFoundError() {
		// TODO Auto-generated method stub
		System.err.println("Course was not found!");
	}

	/**
		* get the entire courseList
		* @return the entire courselist ArrayList
		*/
	public ArrayList <Course> getCourseList() {
		return courseList;
	}

	/**
		* set the courselist
		* @param courseList arraylist of courses
		*/
	public void setCourseList(ArrayList <Course> courseList) {
		this.courseList = courseList;
	}

	/**
		* to String method for Course Catalogue.
		* @return String that can be printed to console
		*/
	@Override
	public String toString () {
		String st = "All courses in the catalogue: \n";
		for (Course c : courseList) {
			st += c;  //This line invokes the toString() method of Course
			st += "\n";
		}
		return st;
	}

}
