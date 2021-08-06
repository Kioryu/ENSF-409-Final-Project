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

  public StudentCourseManager(CourseCatalogue c, StudentData s, String sn, int sID, String cName,int cNum, int section)
  {
    Catalogue=c;
    studentData=s;
    studentName=sn;
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

  public void RegisterCourse(){
    Course c=Catalogue.searchCatalogue(courseName, courseNumber);
    if (c!=null){
      SectionOffering s = c.getSectionOfferingAt(sectionNumber-1);
        System.out.println(s);
      if (s!=null){
        int studentIndex=isRegisteredStudent();
          System.out.println(studentIndex);
        if(studentIndex>-1 && studentData.studentList.get(studentIndex).getRegSize()<6){
          Registration theRegistration= new Registration();
          theRegistration.completeRegistration(studentData.studentList.get(studentIndex),s);
          studentData.addRegistration(studentIndex,courseName,courseNumber,sectionNumber);
        }
        else if (studentIndex==-1){
          Registration theRegistration= new Registration();
          Student temp=new Student(studentName,studentID);
          theRegistration.completeRegistration(temp,s);
          studentData.addNewRegistration(temp,courseName,courseNumber,sectionNumber);

        }

        else
          System.out.println("Too many courses!");


      }

    }

  }

  public void DropCourse(){
    Course c= Catalogue.searchCatalogue(courseName,courseNumber);
    if (c!=null){
      int studentIndex=isRegisteredStudent();
      SectionOffering s=null;
      int regIndex=0;
      if (studentIndex>-1){
        ArrayList <Registration> r = studentData.studentList.get(studentIndex).getRegList();
        for (int i=0; i<r.size();i++){
          if(r.get(i).getSectionOffering().getTheCourse().getCourseName().equals(courseName)&&r.get(i).getSectionOffering().getTheCourse().getCourseNumber()==courseNumber)
            s= r.get(i).getSectionOffering();
            studentData.studentList.get(studentIndex).deleteRegistration(i);
            regIndex=i;

        }
        r=s.getSectionRegistrationList();
        for (int j=0; j<r.size();j++){
          if(r.get(j).getStudent().getStudentID()==studentID)
            s.deleteRegistration(j);

      }
      studentData.removeFromData(studentIndex,regIndex);

    }
  }
  else
    System.out.println("Can't drop course, drop your life");
}


    public String viewStudentCourses(int sID){
      studentID=sID;

      return(studentData.studentList.get(isRegisteredStudent()).toString2());
    }




      }
