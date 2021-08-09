package Server;

import java.io.RandomAccessFile;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

/**
  * The student database monitored through a dat file and random access file pointer
  * @author Jian Shi Chen
  * @version 2.0
  * @since August 5,2021
  */
public class StudentData{
  /**
    * The file containing the data
    */
  private  File  fileIN;

  /**
    * The input scanner
    */
  private  Scanner scan= new Scanner (System.in);

  /**
    * an arraylist storing all the student
    */
  public static ArrayList <Student> studentList = new ArrayList<Student> ();

  /**
    * a copy of the course catalogue
    */
  private CourseCatalogue cat;

  /**
    * A random access file pointer
    */
  private RandomAccessFile ra=null;

  /**
    * Cosntructor for the student data
    * @param c copy of the course catalogue
    */
  public StudentData(CourseCatalogue c){
   cat = c;
   fileIN= new File ("StudentData.dat");
  }

  /**
    * To set up the student data file the first time
    */
  public void initialsetupStudentData(){
    if(! fileIN.exists() || ! fileIN.isFile())
    {
    try{
      ra= new RandomAccessFile("StudentData.dat","rw");
      addStudentEntry(ra);
    }
    catch(IOException e){
      System.out.println("error opening file");
    }
    try{
      ra.close();
    }catch(IOException e){
      System.out.println("error closing file");
    }
  }
  else readFile();
  }

  /**
    * Add student entry when initially setting up the student dat file
    * @param r the random access file pointing to the beginning of the StudentData.dat file
    * @throws IOException when there is an error from the scanner
    */
  public void addStudentEntry(RandomAccessFile r) throws IOException{
    String cont = "Y";
    RandomAccessFile ra=r;
    while (cont.equals("Y")){
      System.out.println("Input student name (up to 30 characters)");
      String nameip= scan.nextLine();
      StringBuffer name= new StringBuffer(nameip);
      name.setLength(30);
      ra.writeChars(name.toString());
      System.out.println("Input student ID");
      int sID=Integer.parseInt(scan.nextLine());
      ra.writeInt(sID);
      studentList.add(new Student(nameip,sID));
      int registrationAmount=0;
      while(true){
        System.out.println("Do you want to add a course Registration?");
          String addRegistration=scan.nextLine();
        if(addRegistration.toUpperCase().equals("Y")){
          System.out.println("Input course name, course number, section number");
          String courseName=scan.nextLine();
          StringBuffer courseID= new StringBuffer(courseName);
          courseID.setLength(4);
          int courseNumber=Integer.parseInt(scan.nextLine());
          int sectionNumber=Integer.parseInt(scan.nextLine());
          if(cat.searchCatalogue (courseName,courseNumber)!=null){
            Registration reg= new Registration ();
            reg.completeRegistration(studentList.get(studentList.size()-1),cat.searchCatalogue (courseName,courseNumber).getSectionOfferingAt(sectionNumber-1));
            System.out.println(cat.searchCatalogue (courseName,courseNumber).getSectionOfferingAt(sectionNumber-1));
            ra.writeChars(courseID.toString());
            ra.writeInt(courseNumber);
            ra.writeInt(sectionNumber);
            registrationAmount++;
          }
        }
        else break;
      }
        StringBuffer temp = new StringBuffer("0000");
        temp.setLength((4*Character.BYTES*(6-registrationAmount)+2*Integer.BYTES*(6-registrationAmount))/2);
        ra.writeChars(temp.toString());
        System.out.println("Do you wish to add another student");
        cont=scan.nextLine();
      }

    }

    /**
      * Reads the StudentData.dat file
      */
    public void readFile(){
      if(fileIN.exists()){
        try{
          ra=new RandomAccessFile("StudentData.dat","rw");
        }
        catch(IOException e){
          System.out.println("error opening file");
        }
        try{
          while (true){
            char scanName[]= new char[30];
            for (int nameCharacter=0;nameCharacter<30;nameCharacter++)
              scanName[nameCharacter]=ra.readChar();
            String outputName= new String(scanName);
            int outputID = ra.readInt();
            studentList.add(new Student(outputName,outputID));
            for (int m=0;m<6;m++){
              char courseID[]= new char[4];
              String courseName="";
              for(int n=0;n<4;n++){
                courseID[n]=ra.readChar();
                courseName=new String(courseID);
              }
              int temp = ra.readInt();
              int secNum = ra.readInt();
              if(cat.searchCatalogue (courseName,temp)!=null){
               Registration reg= new Registration ();
               reg.completeRegistration(studentList.get(studentList.size()-1),cat.searchCatalogue (courseName,temp).getSectionOfferingAt(secNum-1));
              }
            }

          }
        }
        catch(EOFException e){
        }
        catch(IOException e){
          System.out.println("GG");
        }
        try{
          ra.close();
        }
        catch(IOException e){
          System.out.println("error closing file");
        }
      }
    }

  /**
    * add a registration to an existing student
    * @param studentIndex the student Index of student in student List
    * @param courseName the course name of new registration
    * @param courseNumber the course number of new registration
    * @param sectionNumber the section number of new registration
    */
  public void addRegistration(int studentIndex,String courseName,int courseNumber, int sectionNumber){
    try{
      ra=new RandomAccessFile("StudentData.dat","rw");
    }
    catch(IOException e){
      System.out.println("Truly unfortunate");
    }
    try{
      int increment=studentIndex*160+64;
      ra.seek(increment);
      StringBuffer cn=new StringBuffer(courseName);
      cn.setLength(4);
      for (int m=0;m<6;m++){
        char courseID[]= new char[4];
        String courseNameFromFile="";
        for(int n=0;n<4;n++){
          courseID[n]=ra.readChar();
          courseNameFromFile=new String(courseID);
        }
      if (courseNameFromFile.contentEquals("0000")){
        ra.seek(ra.getFilePointer()-8);
        ra.writeChars(cn.toString());
        ra.writeInt(courseNumber);
        ra.writeInt(sectionNumber);
        ra.close();
        break;
      }
    }
  }
    catch(IOException e){
      System.out.println("Truly unfortunate");
    }
  }

  /**
    * remove student registration from database
    * @param studentIndex the student Index of student in student List
    * @param courseName the course name of new registration
    * @param courseNumber the course number of new registration
    */
  public void removeFromData(int studentIndex, String courseName, int courseNumber){
    System.out.println("deleting from file");
    try{
      ra=new RandomAccessFile("StudentData.dat","rw");
      ra.seek((160*studentIndex)+(64));
      System.out.println("current file pointer position "+ ra.getFilePointer());
      for (int m=0;m<6;m++){
        char courseID[]= new char[4];
        String courseNameFromFile="";
        for(int n=0;n<4;n++){
          courseID[n]=ra.readChar();
          courseNameFromFile=new String(courseID);
        }
        int temp = ra.readInt();
        if(courseNameFromFile.equals(courseName)&&temp==courseNumber){
          ra.seek(ra.getFilePointer()-12);
          StringBuffer sn= new StringBuffer ("0000");
          sn.setLength(4);
          ra.writeChars(sn.toString());
          ra.writeInt(0);
          ra.writeInt(0);
          break;
        }
      }
      System.out.println("finished deleting");
      ra.close();
    }
    catch(IOException e){
      System.out.println("error");
    }
  }

  /**
    * add a new student to the database
    * @param temp new student object to be added
    */
  public void addNewRegistration(Student temp){
    System.out.println("writring to file") ;
    studentList.add(temp);
    try{
      RandomAccessFile r=new RandomAccessFile("StudentData.dat","rw");
      System.out.println(studentList.size()-1);
      r.seek(160*(studentList.size()-1));
      StringBuffer sn= new StringBuffer (temp.getStudentName());
      sn.setLength(30);
      System.out.println(sn.toString());
      r.writeChars(sn.toString());
      int studentID= temp.getStudentID();
      System.out.println(studentID);
      r.writeInt(studentID);
      StringBuffer emptyspaces=new StringBuffer("0000");
      emptyspaces.setLength(48);
      r.writeChars(emptyspaces.toString());
      System.out.println("finished writing");
      System.out.println(temp);
      r.close();
    }
    catch(IOException e){
      System.out.println("error");
    }
  }

}
