import java.util.ArrayList;
/**
	* Holds all the information relevant to a sectionOffering object.
	* @author Aaron Li, Jian Shi Chen
	* @version 1.0
	* @since August 3, 2021
	*/
public class SectionOffering {

/**
	* integer representing the section number
	*/
	private int sectionNum;

/**
	* integer representing the section capacity
	*/
	private int sectionCap;

/**
	* the Course that this section offering belongs to
	*/
	private Course theCourse;
	//private ArrayList<Student> studentList;

/**
	* arraylist of registrations for a given sectionOffering
	*/
	private ArrayList <Registration> sectionRegistrationList;

/**
	* Constructor for the sectionOffering object
	* @param sectionNum section number of the course offering
	* @param sectionCap max capacity of course offering
	*/
	public SectionOffering (int sectionNum, int sectionCap) {
		this.setSectionNum(sectionNum);
		this.setSectionCap(sectionCap);
		sectionRegistrationList = new ArrayList <Registration>();
	}

/**
	* get section number
	* @return integer of section number
	*/
	public int getSectionNum() {
		return sectionNum;
	}

/**
	* get set section number
	* @param sectionNum section number to be setted
	*/
	public void setSectionNum(int sectionNum) {
		this.sectionNum = sectionNum;
	}

/**
	* get Section capacity
	* @return integer representing section capacity
	*/
	public int getSectionCap() {
		return sectionCap;
	}

/**
	* set the section capacity
	* @param sectionCap section capacity to be setted
	*/
	public void setSectionCap(int sectionCap) {
		this.sectionCap = sectionCap;
	}

/**
	* get the course object
	* @return course object containing this section offering
	*/
	public Course getTheCourse() {
		return theCourse;
	}

/**
	* set the course object
	* @param theCourse that this section offering is offered for
	*/
	public void setTheCourse(Course theCourse) {
		this.theCourse = theCourse;
	}

/**
	* to String method for printing
	* @return String that can be printed.
	*/
	@Override
	public String toString () {
		String st = "\n";
		st += getTheCourse().getCourseName() + " " + getTheCourse().getCourseNumber() + "\n";
		st += "Section Num: " + getSectionNum() + ", section cap: "+ getSectionCap() +"\n";
		//We also want to print the names of all students in the section
		return st;
	}

/**
	* add registration to the registration list
	* @param registration object with student and seciton offering information
	*/
	public void addRegistration(Registration registration) {
		// TODO Auto-generated method stub
		sectionRegistrationList.add(registration);

	}



}
