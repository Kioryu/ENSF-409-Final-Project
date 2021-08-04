import java.io.RandomAccessFile;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class StudentData{
  private static File  fileIN;
  private static RandomAccessFile ra=null;
  private static Scanner scan= new Scanner (System.in);
  private static ArrayList <Student> studentList = new ArrayList<Student> ();
  private static CourseCatalogue cat;
  private static int studentListIndex=0;

  public StudentData(){
   cat = new CourseCatalogue ();
   fileIN= new File ("StudentData.dat");
  }

//  public ArrayList <Student> initialsetupStudentData(){
//
//  }
  public static void main (String [] args) throws IOException{
    StudentData test = new StudentData();
    Course myCourse = cat.searchCatalogue ("ENGG", 233);
    if (myCourse != null) {
      cat.createSectionOffering(myCourse, 1, 10);
      cat.createSectionOffering(myCourse, 2, 100);
    }

    Course myCourse2 = cat.searchCatalogue ("ENSF", 409);
    if (myCourse2 != null) {
      cat.createSectionOffering(myCourse2, 1, 10);
      cat.createSectionOffering(myCourse2, 2, 100);
    }

    Course myCourse3 = cat.searchCatalogue ("PHYS", 259);
    if (myCourse3 != null) {
      cat.createSectionOffering(myCourse3, 1, 10);
      cat.createSectionOffering(myCourse3, 2, 100);
    }

    Course myCourse4 = cat.searchCatalogue ("ENGG", 201);
    if (myCourse4 != null) {
      cat.createSectionOffering(myCourse4, 1, 10);
      cat.createSectionOffering(myCourse4, 2, 100);
    }

    Course myCourse5 = cat.searchCatalogue ("ENCM", 237);
    if (myCourse5 != null) {
      cat.createSectionOffering(myCourse5, 1, 10);
      cat.createSectionOffering(myCourse5, 2, 100);
    }

    Course myCourse6 = cat.searchCatalogue ("ENCM", 335);
    if (myCourse6 != null) {
      cat.createSectionOffering(myCourse6, 1, 10);
      cat.createSectionOffering(myCourse6, 2, 100);
    }

    Course myCourse7 = cat.searchCatalogue ("ENEL", 353);
    if (myCourse7 != null) {
      cat.createSectionOffering(myCourse7, 1, 10);
      cat.createSectionOffering(myCourse7, 2, 100);
    }


    System.out.println(fileIN.exists());
    if(! fileIN.exists() || ! fileIN.isFile())
    {
    try{
      ra= new RandomAccessFile("StudentData.dat","rw");
      for(int i=0;i<1;i++)
      {
        System.out.println("Input name, ID, (coursename,coursenumber,sectionnumberx6)");
        String nameip= scan.nextLine();
        StringBuffer name= new StringBuffer(nameip);
        name.setLength(30);
        ra.writeChars(name.toString());
        int sID=Integer.parseInt(scan.nextLine());
        ra.writeInt(sID);
        for (int j=0;j<1;j++){
          String courseName=scan.nextLine();
          StringBuffer courseID= new StringBuffer(courseName);
          courseID.setLength(4);
          ra.writeChars(courseID.toString());
          int courseNumber=Integer.parseInt(scan.nextLine());
          ra.writeInt(courseNumber);
          int sectionNumber=Integer.parseInt(scan.nextLine());
          ra.writeInt(sectionNumber);
        }
      }
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
  if(fileIN.exists()){
    try{
      ra=new RandomAccessFile("StudentData.dat","r");
      }
    catch(IOException e){
      System.out.println("error opening file");
    }
    try{
      while (true){
      char scanName[]= new char[30];
      for (int k=0;k<30;k++)
          scanName[k]=ra.readChar();
      String outputName= new String(scanName);
      System.out.println(outputName);
      studentList.add(new Student(outputName,ra.readInt()));
      for (int m=0;m<6;m++){
        char courseID[]= new char[4];
        String courseName="";
        for(int n=0;n<4;n++){
          courseID[n]=ra.readChar();
          courseName=new String(courseID);
        }
          int temp = ra.readInt();
          int secNum = ra.readInt();
          System.out.println(courseName);
          System.out.println(temp);
          System.out.println(secNum);
          if(cat.searchCatalogue (courseName,temp)!=null){
             Registration reg= new Registration ();
             reg.completeRegistration(studentList.get(studentListIndex),cat.searchCatalogue (courseName,temp).getSectionOfferingAt(secNum-1));
          }

      }

      studentListIndex++;
    }
  }
    catch(EOFException e){
      System.out.println("End of File");
    }
    try{
      ra.close();
    }
    catch(IOException e){
      System.out.println("error closing file");
    }
}
  System.out.println(studentList.get(0));
  }
}
