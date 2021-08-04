
import java.util.Scanner;
import java.util.ArrayList;

public class RegistrationApp {

	public static void main(String[]args){
		Scanner scan=new Scanner(System.in);
    String input;
    CourseCatalogue cat = new CourseCatalogue ();
    Course c;
    Registration reg= new Registration();
    Registration reg1= new Registration();
    Registration reg2= new Registration();
    Registration reg3= new Registration();
    Registration reg4= new Registration();
    Registration reg5= new Registration();
    Registration reg6= new Registration();
    Registration reg7= new Registration();
    Registration reg8= new Registration();
    Registration reg9= new Registration();
    Registration reg10= new Registration();
    Registration reg11= new Registration();
    Registration reg12= new Registration();
    Registration reg13= new Registration();

    Student st = new Student ("Sara", 1);
		Student st2 = new Student ("Sam", 2);
    Student st3 = new Student ("Bob", 3);
    Student st4 = new Student ("Smith", 4);
    Student st5 = new Student ("Jack", 5);
    Student st6 = new Student ("Alice", 6);
    Student st7 = new Student ("Amy", 7);
    Student st8 = new Student ("Peanut", 8);
    Student st9 = new Student ("Cindy", 69);


    Course myCourse = cat.searchCat("ENGG", 233);
		if (myCourse != null) {
			cat.createCourseOffering(myCourse, 1, 10);
			cat.createCourseOffering(myCourse, 2, 100);
		}

    Course myCourse2 = cat.searchCat("ENSF", 409);
		if (myCourse2 != null) {
			cat.createCourseOffering(myCourse2, 1, 10);
			cat.createCourseOffering(myCourse2, 2, 100);
		}

    Course myCourse3 = cat.searchCat("PHYS", 259);
		if (myCourse3 != null) {
			cat.createCourseOffering(myCourse3, 1, 10);
			cat.createCourseOffering(myCourse3, 2, 100);
		}

    Course myCourse4 = cat.searchCat("ENGG", 201);
    if (myCourse4 != null) {
      cat.createCourseOffering(myCourse4, 1, 10);
      cat.createCourseOffering(myCourse4, 2, 100);
    }

    Course myCourse5 = cat.searchCat("ENCM", 237);
    if (myCourse5 != null) {
      cat.createCourseOffering(myCourse5, 1, 10);
      cat.createCourseOffering(myCourse5, 2, 100);
    }

    Course myCourse6 = cat.searchCat("ENCM", 335);
    if (myCourse6 != null) {
      cat.createCourseOffering(myCourse6, 1, 10);
      cat.createCourseOffering(myCourse6, 2, 100);
    }

    Course myCourse7 = cat.searchCat("ENEL", 353);
    if (myCourse7 != null) {
      cat.createCourseOffering(myCourse7, 1, 10);
      cat.createCourseOffering(myCourse7, 2, 100);
    }

    myCourse5.addPreReq(myCourse);
    myCourse2.addPreReq(myCourse5);
    myCourse2.addPreReq(myCourse6);
    myCourse5.addPreReq(myCourse);
    myCourse5.addPreReq(myCourse);

    reg.completeRegistration(st8,myCourse.getCourseOfferingAt(0));
    reg1.completeRegistration(st2,myCourse.getCourseOfferingAt(0));
    reg2.completeRegistration(st8,myCourse2.getCourseOfferingAt(1));
    reg3.completeRegistration(st8,myCourse7.getCourseOfferingAt(1));
    reg4.completeRegistration(st8,myCourse6.getCourseOfferingAt(1));
    reg5.completeRegistration(st8,myCourse5.getCourseOfferingAt(1));
    reg6.completeRegistration(st8,myCourse4.getCourseOfferingAt(1));
    reg7.completeRegistration(st,myCourse.getCourseOfferingAt(0));
    reg8.completeRegistration(st3,myCourse.getCourseOfferingAt(0));
    reg9.completeRegistration(st4,myCourse.getCourseOfferingAt(0));
    reg10.completeRegistration(st5,myCourse.getCourseOfferingAt(0));
    reg11.completeRegistration(st6,myCourse.getCourseOfferingAt(0));
    reg12.completeRegistration(st7,myCourse.getCourseOfferingAt(0));
    reg13.completeRegistration(st8,myCourse3.getCourseOfferingAt(0));

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
        String cn;
        int num;
        System.out.println("Please input the course name:\n");
        cn=scan.nextLine();
        System.out.println("Please input the course number:\n");
        num=Integer.parseInt(scan.nextLine());
        c=cat.searchCat(cn, num);
        System.out.println(c);
      }

      if(Integer.parseInt(input)==2){
        String sn;
        String sID;
        String cn;
        int num;
        int section;
        System.out.println("Please input the student name:\n");
        sn=scan.nextLine();
        System.out.println("Please input the student ID:\n");
        sID=scan.nextLine();
        System.out.println("Please input the course name to register in:\n");
        cn=scan.nextLine();
        System.out.println("Please input the course number to register in:\n");
        num=Integer.parseInt(scan.nextLine());
        System.out.println("Please enter the section to register in:\n");
        section=Integer.parseInt(scan.nextLine());

				StudentCourseManager temp= new studentCourseManager (cat,sn,Integer.parseInt(sID),cn,num,section);

      }


      if(Integer.parseInt(input)==3){
        String sID;
        String cn;
        int num;
        System.out.println("Please input the student ID:\n");
        sID=scan.nextLine();
        System.out.println("Please input the course name to register in:\n");
        cn=scan.nextLine();
        System.out.println("Please input the course number to register in:\n");
        num=Integer.parseInt(scan.nextLine());

        cat.deleteCourse(Integer.parseInt(sID), cn, num);
      }

      if(Integer.parseInt(input)==4){
        System.out.println(cat);
      }

      if(Integer.parseInt(input)==5){
        System.out.println("Please input the student ID:\n");
        String sID=scan.nextLine();
        cat.courseByStudent(Integer.parseInt(sID));
      }

      if(Integer.parseInt(input)==6){
        System.exit(1);
      }
    }

  }

}
