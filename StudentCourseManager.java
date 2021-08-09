import java.util.ArrayList;

/**
  * Manage all the methods relating to a student and a course
  */
public class StudentCourseManager{
  /**
    * A copy of the course Catalogue
    */
  private CourseCatalogue Catalogue;

  /**
    * A copy of the student data base
    */
  private StudentData studentData;

  /**
    *  The student name
    */
  private String studentName;

  /**
    * the student ID
    */
  private int studentID;

  /**
    * the course name
    */
  private String courseName;

  /**
    * the course number
    */
  private int courseNumber;

  /**
    * the section number
    */
  private int sectionNumber;

  /**
    * Constructor for the student course manager
    * @param s copy of the student database
    */
  public StudentCourseManager (StudentData s){
      studentData=s;
  }

  /**
    * Constructor for the student course manager
    * @param s copy of the student database
    * @param sID the student ID number
    */
  public StudentCourseManager (StudentData s, int sID){
      studentData=s;
      studentID=sID;
  }

  /**
    * Constructor for the student course manager
    * @param s copy of the student database
    * @param c copy of the course catalogue
    * @param sName student name
    * @param sID the student ID
    * @param cName the course name
    * @param cNum the course number
    * @param section the section number
    */
  public StudentCourseManager(CourseCatalogue c, StudentData s, String sName, int sID, String cName,int cNum, int section)
  {
    Catalogue=c;
    studentData=s;
    studentName=sName;
    studentID=sID;
    courseName=cName;
    courseNumber=cNum;
    sectionNumber=section;
  }

  /**
    * Constructor for the student course manager
    * @param s copy of the student database
    * @param c copy of the course catalogue
    * @param sID the student ID
    * @param cName the course name
    * @param cNum the course number
    */
  public StudentCourseManager(CourseCatalogue c, StudentData s,int sID, String cName, int cNum){
    Catalogue=c;
    studentData=s;
    studentName=null;
    studentID=sID;
    courseName=cName;
    courseNumber=cNum;
    sectionNumber=0;
  }

  /**
    * check if the student is a registered student
    * @return -1 if student is not found, else return the index of the student in the student array list
    */
  public int isRegisteredStudent(){
    for(int i=0;i<studentData.studentList.size();i++){
      if (studentData.studentList.get(i).getStudentID()==studentID)
        return i;
    }
    return -1;
  }

  /**
    * Main logic for registering a student into a new course
    * @return a string representing the outcome of the operation
    */
  public String RegisterCourse(){
    Course c=Catalogue.searchCatalogue(courseName, courseNumber);
    if (c!=null){
      SectionOffering s = c.getSectionOfferingAt(sectionNumber-1);
      if(s.sectionFull())
        return ("Section is Full, Registration Failed");
      if (s!=null){
        int studentIndex=isRegisteredStudent();
        ArrayList <Registration> r= s.getSectionRegistrationList();
        for (int i=0; i<r.size();i++){
          if(r.get(i).getStudent().getStudentID()==studentID){
            return("Student Already Registered");
          }
        }
        if(studentIndex>-1 && studentData.studentList.get(studentIndex).getRegSize()<6){
          Registration theRegistration= new Registration();
          theRegistration.completeRegistration(studentData.studentList.get(studentIndex),s);
          studentData.addRegistration(studentIndex,courseName,courseNumber,sectionNumber);
          return ("Registration Complete!");
        }
        else
          return("Too many courses!");
      }
    }
    return ("Registration Failed!");
  }

  /**
    * Main logic for dropping a course
    * @return a string representing the outcome of the operation
    */
  public String DropCourse(){
    Course c= Catalogue.searchCatalogue(courseName,courseNumber);
    if (c!=null){
      int studentIndex=isRegisteredStudent();
      SectionOffering s=null;
      //int regIndex=0;
      if (studentIndex>-1){
        ArrayList <Registration> r = studentData.studentList.get(studentIndex).getRegList();
        for (int i=0; i<r.size();i++){
          if(r.get(i).getSectionOffering().getTheCourse().getCourseName().equals(courseName)&&r.get(i).getSectionOffering().getTheCourse().getCourseNumber()==courseNumber){
            s= r.get(i).getSectionOffering();
            studentData.studentList.get(studentIndex).deleteRegistration(i);
            break;
        //    regIndex=i;
          }
        }
        if (s!=null){
          r=s.getSectionRegistrationList();
          for (int j=0; j<r.size();j++){
            if(r.get(j).getStudent().getStudentID()==studentID)
              s.deleteRegistration(j);
            }
          }
        studentData.removeFromData(studentIndex,courseName,courseNumber);
        return("Drop Successful!");
      }
    }
    return ("Can't drop course, drop your life");
  }

  /**
    * Main logic for viewing all the courses taken by a student
    * @param sID the student ID
    * @return a string with all the courses a student is registered in
    */
  public String viewStudentCourses(int sID){
    studentID=sID;
    int temp=isRegisteredStudent();
    if (temp==-1){
      return("Student not found!");
    }
      return(studentData.studentList.get(temp).toString2());
  }
}
