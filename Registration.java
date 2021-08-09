package Server;


/**
	* Registration object that holds student and section offering information .
	* @author Aaron Li, Jian Shi Chen
	* @version 1.0
	* @since August 3, 2021
	*/
public class Registration {

/**
	* the student that is registering for a course
	*/
	private Student theStudent;

/**
	* section offering that the student registering for
	*/
	private SectionOffering theOffering;

/**
	* the grade of the student in the course
	*/
	private char grade;

/**
	* complete the registration by adding the registration object to the student's registeration list and section's registration list.
	* @param st student registering for course
	* @param of section offering that the student is registering for
	*/
	void completeRegistration (Student st, SectionOffering of) {
		theStudent = st;
		theOffering = of;
		addRegistration ();
	}

/**
	* adds the registration for both the student and section offering
	*/
	private void addRegistration () {
		theStudent.addRegistration(this);
		theOffering.addRegistration(this);
	}

/**
	* get the student object
	* @return student object
	*/
	public Student getStudent() {
		return theStudent;
	}

/**
	* set student object
	* @theStudent a student object
	*/
	public void setStudent(Student theStudent) {
		this.theStudent = theStudent;
	}

/**
	* get section offering
	* @return section offering
	*/
	public SectionOffering getSectionOffering() {
		return theOffering;
	}

/**
	* set the section offering
	* @param theOffering a section offering object
	*/
	public void setSectionOffering(SectionOffering theOffering) {
		this.theOffering = theOffering;
	}

/**
	* get Grade
	* @return char represnting letter grade
	*/
	public char getGrade() {
		return grade;
	}

/**
	* set Grade
	* @param grade character of the student's grade
	*/
	public void setGrade(char grade) {
		this.grade = grade;
	}

	/**
		* to String method for Registration .
		* @return String that can be printed to console
		*/
	@Override
	public String toString () {
		String st = "\n";
		st += "Student Name: " + getStudent() + "\n";
		st += "The Offering: " + getSectionOffering () + "\n";
		st += "Grade: " + getGrade();
		st += "\n-----------\n";
		return st;

	}


}
