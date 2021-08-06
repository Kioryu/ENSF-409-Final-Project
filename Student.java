import java.util.ArrayList;
/**
	* Object storing information relevant to a student
	* @author Aaron Li, Jian Shi Chen
	* @version 1.0
	* @since August 3, 2021
	*/
public class Student {

/**
	* String representing student's name
	*/
	private String studentName;

/**
	* integer representing student's ID number
	*/
	private int studentID;
	//private ArrayList<CourseOffering> offeringList;

/**
	* registration list containing all the registrations a student has
	*/
	private ArrayList<Registration> studentRegistrationList;


/**
	* Constructor for student object
	* @param studentName name of the student
	* @studentID ID of the student
	*/
	public Student (String studentName, int studentID) {
		this.setStudentName(studentName);
		this.setStudentID(studentID);
		studentRegistrationList = new ArrayList<Registration>();
	}

/**
	* get Student name
	* @return String of student name
	*/
	public String getStudentName() {
		return studentName;
	}

	public ArrayList <Registration> getRegList(){
		return studentRegistrationList;
	}

	public void deleteRegistration (int i){
		studentRegistrationList.remove(i);
	}
/**
	* set Student name
	* @param studentName String of student name
	*/
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

/**
	* get Student ID
	* @return student's ID number
	*/
	public int getStudentID() {
		return studentID;
	}

/**
	* set the student ID
	* @param studentID integer represneting student's ID
	*/
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public int getRegSize(){
		return studentRegistrationList.size();
	}
		/**
			* to String method for Student .
			* @return String that can be printed to console
			*/
	@Override
	public String toString () {
		String st = "Student Name: " + getStudentName() + "\n" +
				"Student Id: " + getStudentID() + "\n\n";
		for (int i=0;i<studentRegistrationList.size();i++){
			st = st+ "Course: " + studentRegistrationList.get(i).getSectionOffering().getTheCourse().getCourseName() + studentRegistrationList.get(i).getSectionOffering().getTheCourse().getCourseNumber()+  "\n\n" ;
	}
		return st;
	}

	public void addRegistration(Registration registration) {
		// TODO Auto-generated method stub
		studentRegistrationList.add(registration);
	}

}
