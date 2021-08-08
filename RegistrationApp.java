
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class RegistrationApp {
	private CourseCatalogue cat=new CourseCatalogue();
	private StudentData studentdata=new StudentData(cat);
	private Course c;


	public RegistrationApp(){
		cat=new CourseCatalogue();
		studentdata=new StudentData(cat);
		studentdata.initialsetupStudentData();
		}

	public StudentData getStudentData(){
			return studentdata;
		}

	public String searchCatalogue(String cName, String cNum){
		c=cat.searchCatalogue(cName, Integer.parseInt(cNum));
		if(c==null)
			return("Course not found!");
		return (c.toString());

			}

	public String registerCourse(int i, String cName, String cNum, int secNum,String studentName){
		StudentCourseManager temp= new StudentCourseManager (cat, studentdata,studentName,i,cName, Integer.parseInt(cNum),secNum);
		String s=temp.RegisterCourse();
		return (s);
	}

	public String dropCourse(int i, String cName, String cNum){
		StudentCourseManager temp= new StudentCourseManager (cat, studentdata,i,cName, Integer.parseInt(cNum));
		String s=temp.DropCourse();
		return (s);
	}

	public String displayStudentCourses(int i){
		StudentCourseManager temp=new StudentCourseManager(studentdata);
		return(temp.viewStudentCourses(i));
	}

	public String displayAllCourses(){
		return (cat.toString());
	}

	public String checkStudentRecords(int i){
		StudentCourseManager temp=new StudentCourseManager (studentdata,i);
		int registered=temp.isRegisteredStudent();
		if (registered>0){
			return(studentdata.studentList.get(registered).getStudentName());
		}
		return ("null");
	}




	public static void main(String[]args){
		CourseCatalogue cat=new CourseCatalogue();
		StudentData studentdata=new StudentData(cat);

		Scanner scan=new Scanner(System.in);
    String input;
		Course c;


		studentdata.initialsetupStudentData();
		//System.out.println(studentdata.studentList.get(1).getStudentName());

    while(true){
      System.out.println(" ");
      System.out.println("1. Search catalogue courses\n" +
                         "2. Add course to student courses\n" +
                         "3. Remove course from student courses\n" +
                         "4. View All courses in catalogue\n" +
                         "5. View all courses taken by student\n" +
                         "6. Quit\n");
      input=scan.nextLine();
      if(Integer.parseInt(input)==1){
        System.out.println("Please input the course name:\n");
        String cn=scan.nextLine();
        System.out.println("Please input the course number:\n");
        int num=Integer.parseInt(scan.nextLine());
        c=cat.searchCatalogue(cn, num);
        System.out.println(c);
      }

      if(Integer.parseInt(input)==2){
        System.out.println("Please input the student name:\n");
        String sn=scan.nextLine();
        System.out.println("Please input the student ID:\n");
        String sID=scan.nextLine();
        System.out.println("Please input the course name to register in:\n");
        String cn=scan.nextLine();
        System.out.println("Please input the course number to register in:\n");
        int num=Integer.parseInt(scan.nextLine());
        System.out.println("Please enter the section to register in:\n");
        int section=Integer.parseInt(scan.nextLine());

				StudentCourseManager temp= new StudentCourseManager (cat, studentdata, sn,Integer.parseInt(sID),cn,num,section);
				temp.RegisterCourse();

      }


      if(Integer.parseInt(input)==3){
        System.out.println("Please input the student ID:\n");
        String sID=scan.nextLine();
        System.out.println("Please input the course name to drop:\n");
        String cn=scan.nextLine();
        System.out.println("Please input the course number to drop:\n");
        int num=Integer.parseInt(scan.nextLine());

				StudentCourseManager temp= new StudentCourseManager (cat,studentdata,Integer.parseInt(sID),cn,num);
				temp.DropCourse();
      }

      if(Integer.parseInt(input)==4){
        System.out.println(cat);
      }

      if(Integer.parseInt(input)==5){
        System.out.println("Please input the student ID:\n");
        String sID=scan.nextLine();
				StudentCourseManager temp=new StudentCourseManager(studentdata);
        System.out.println(temp.viewStudentCourses(Integer.parseInt(sID)));
      }

      if(Integer.parseInt(input)==6){

				System.exit(1);
      }
    }



  }


}
