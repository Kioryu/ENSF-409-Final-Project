import java.util.ArrayList;

/**
	* Holds all the information relevant to a course object.
	* @author Aaron Li, Jian Shi Chen
	* @version 1.0
	* @since August 3, 2021
	*/

public class Course {

/**
	* the String for courseName
	*/
	private String courseName;

/**
	* the courseNumber of the courses
	*/
	private int courseNumber;

/**
	* an Arraylist holding all the prereq for this courses
	*/
	private ArrayList<Course> preReq;

/**
	* an Arraylist holding all the course offering for this course.
	*/
	private ArrayList<SectionOffering> sectionList;

/**
	* Constructor for object Course.
	* sets the course name and course number.
	* creates a prereq list and a list of seciton offerings.
	* @param courseName a String representing the name of course
	* @param courseNumber an integer representing the ID of the course
	*/
	public Course(String courseName, int courseNumber) {
		this.setCourseName(courseName);
		this.setCourseNumber(courseNumber);
		// Both of the following are only association
		preReq = new ArrayList<Course>();
		sectionList = new ArrayList<SectionOffering>();
	}

/**
	* add a section offering to the offering ArrayList
	* @param offering sectionoffering to be added
	*/
	public void addOffering(SectionOffering offering) {
		if (offering != null && offering.getTheCourse() == null) {
			offering.setTheCourse(this);
			if (!offering.getTheCourse().getCourseName().equals(courseName)
					|| offering.getTheCourse().getCourseNumber() != courseNumber) {
				System.err.println("Error! This section belongs to another course!");
				return;
			}

			sectionList.add(offering);
		}
	}

/**
	* get the CourseName
	* @return courseName of courses
	*/
	public String getCourseName() {
		return courseName;
	}

/**
	* set the CourseName
	* @param courseName the String for new courseName
	*/
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

/**
	* get the course number
	* @return the course number integer
	*/
	public int getCourseNumber() {
		return courseNumber;
	}

/**
	* set the course number
	* @param courseNumber the integer to be added as course number
	*/
	public void setCourseNumber(int courseNumber) {
		this.courseNumber = courseNumber;
	}

	public ArrayList <SectionOffering> getSectionOffering(){
		return sectionList;
	}
/**
	* get Section offering given an array index
	* @param i the index to get section offering
	* @return SectionOffering object from the index given
	*/
	public SectionOffering getSectionOfferingAt(int i) {
		// TODO Auto-generated method stub
		if (i < 0 || i >= sectionList.size() )
			return null;
		else
			return sectionList.get(i);
	}
/**
	* toString method for printing
	* @return String object that can be printed with ocurse information.
	*/
	@Override
	public String toString () {
		String st = "\n";
		st += getCourseName() + " " + getCourseNumber ();
		st += "\nAll course sections:\n";
		for (SectionOffering c : sectionList)
			st += c;
		st += "\n-------\n";
		return st;
	}



}
