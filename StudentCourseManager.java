

public class StudentCourseManager{
  private CourseCatalogue Catalogue;
  private String studentName;
  private int studentID;
  private String courseName;
  private int courseNumber;
  private int sectionNumber;

  public StudentCourseManager(CourseCatalogue cat ,String sn, int sID, String cName,int num, int section)
  {
    Catalogue=cat;
    studentName=sn;
    studentID=sID;
    courseName=cName;
    courseNumber=cNum;
    sectionNumber=section;
  }

  public void RegisterCourse(String studentName, int studentID, String courseName, int courseNumber, int sectionNum){
    Course c=searchCat(courseName, courseNumber);
    if (c!=null){
      sectionOffering = c.getSectionOfferingAt(c.searchSectionOffering(section));
      Student st3= new Student (studentName,studentID);
      Registration theRegistration= new Registration();
      theRegistration.completeRegistration(studentName,sectionOffering);
      System.out.println(sectionOffering);
    }

  }

  public void DropCourse(int studentID, String courseName, int courseNumber){
    Course c=searchCatalogue(courseName,courseNumber);
    int index=0;
    int sectionIndex=0;
    ArrayList <SectionOffering> theOffering= c.getSectionOffering();
    for (int i=0; i<theOffering.size(); i++){
      index=theOffering.get(i).searchForStudent(studentID);
      System.out.println(index);
      if (index>-1){
        sectionindex=i;
        break;
      }
    }


    public void viewCourseCatalogue(){

    }

    public void viewStudentCourses(){}
}
