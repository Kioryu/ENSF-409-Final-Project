package Server;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
	* This class takes care of the main functionality of the program.
	* @author Jian Shi Chen
	* @version 2.0
	* @since August 5,2021
	*/

public class RegistrationApp {

	/**
		* A course catalogue with all the courses
		*/
	private CourseCatalogue cat=new CourseCatalogue();

	/**
		* The student database
		*/
	private StudentData studentdata=new StudentData(cat);

	/**
		* The constructor for RegistrationApp
		*/
	public RegistrationApp(){
		cat=new CourseCatalogue();
		studentdata=new StudentData(cat);
		studentdata.initialsetupStudentData();
		}

	/**
		* Getter for the student database
		* @return studentdata object
		*/
	public StudentData getStudentData(){
			return studentdata;
		}

	/**
		* To add a course to the course catalogue
		* @param cName the course name of the new course
		* @param cNumber the course number of the new course
		* @param sectionNumber  the number of sections to create for the new course
		* @param capacity the max capacity for each section
		* @return String telling the client if the operation was successful or not
		*/
  synchronized public String addCourse(String cName, int cNumber,int sectionNumber, int capacity){
		if(sectionNumber>0 && capacity>0){
			Course c= new Course(cName,cNumber);
			cat.addNewCourse(c,sectionNumber,capacity);
			return("Add Course Complete!");
		}
		return("Add Course Failed");
	}

	/**
		* To add a student to the student data base
		* @param sName the student Name
		* @param sID the student ID
		* @return String telling the client if the operation was successful or not
		*/
	synchronized public String addStudent(String sName,int sID){
			Student temp = new Student(sName,sID);
			studentdata.addNewRegistration(temp);
			return("Add Student successful!");
	}

	/**
		* To search the catalogue
		* @param cName the courseName we are searching for
		* @param cNum the course number we are searching for
		* @return course not found or the information about the course
		*/
	public String searchCatalogue(String cName, String cNum){
		Course c=cat.searchCatalogue(cName, Integer.parseInt(cNum));
		if(c==null)
			return("Course not found!");
		return (c.toString());
	}

	/**
		* To register for a course
		* @param i the ID of the student
		* @param cName the course name
		* @param cNum the course number
		* @param secNum the section Number
		* @param studentName the student name
		* @return the outcome of the operation
		*/
	synchronized public String registerCourse(int i, String cName, String cNum, int secNum){
		StudentCourseManager temp= new StudentCourseManager (cat, studentdata,i,cName, Integer.parseInt(cNum),secNum);
		String s=temp.RegisterCourse();
		return (s);
	}

	/**
		* To drop a course
		* @param i the student ID
		* @param cName the course Name
		* @param cNum the course number
		* @return the outcome of the operation
		*/
	synchronized public String dropCourse(int i, String cName, String cNum){
		StudentCourseManager temp= new StudentCourseManager (cat, studentdata,i,cName, Integer.parseInt(cNum));
		String s=temp.DropCourse();
		return (s);
	}

	/**
		* To look at all the courses that a student is taking
		* @param i the student ID
		* @return either operation failed or the list of student courses
		*/
	synchronized public String displayStudentCourses(int i){
		StudentCourseManager temp=new StudentCourseManager(studentdata);
		return(temp.viewStudentCourses(i));
	}

	/**
		* display All the courses in the course catalogue
		* @return the String outputing all the courses in the course catalogue.
		*/
	public String displayAllCourses(){
		return (cat.toString());
	}

	/**
		* check if a student ID is in the records
		* @param i the student ID
		* @return the student Name of the student or null if not registered.
		*/
	public String checkStudentRecords(int i){
		StudentCourseManager temp=new StudentCourseManager (studentdata,i);
		int registered=temp.isRegisteredStudent();
		if (registered>0){
			return(studentdata.studentList.get(registered).getStudentName());
		}
		return ("FAIL");
	}

/*
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

*/

  }
