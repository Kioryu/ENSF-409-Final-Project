import java.util.ArrayList;

public class StudentCourseManager{
  private CourseCatalogue Catalogue;
  private StudentData studentData;
  private String studentName;
  private int studentID;
  private String courseName;
  private int courseNumber;
  private int sectionNumber;

  public StudentCourseManager (StudentData s){
      studentData=s;
  }
  public StudentCourseManager (StudentData s, int sID){
      studentData=s;
      studentID=sID;
  }
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

  public StudentCourseManager(CourseCatalogue c, StudentData s,int sID, String cName, int cNum){
    Catalogue=c;
    studentData=s;
    studentName=null;
    studentID=sID;
    courseName=cName;
    courseNumber=cNum;
    sectionNumber=0;
  }


  public int isRegisteredStudent(){
    for(int i=0;i<studentData.studentList.size();i++){
      if (studentData.studentList.get(i).getStudentID()==studentID)
        return i;
    }
    return -1;
  }

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


    public String viewStudentCourses(int sID){
      studentID=sID;
      int temp=isRegisteredStudent();
      if (temp==-1){
        return("Student not found!");
      }
      return(studentData.studentList.get(temp).toString2());
    }




      }
