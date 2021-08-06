import java.util.ArrayList;

//This class is simulating a database for our
//program
public class DBManager {

	ArrayList <Course> courseList;

	public DBManager () {
		courseList = new ArrayList<Course>();
	}

	public ArrayList readFromDataBase() {
		// TODO Auto-generated method stub
		courseList.add(new Course ("ENGG", 233));
		courseList.add(new Course ("ENSF", 409));
		courseList.add(new Course ("PHYS", 259));
		courseList.add(new Course ("ENGG", 201));
		courseList.add(new Course ("ENCM", 237));
		courseList.add(new Course ("ENCM", 335));
		courseList.add(new Course ("ENEL", 353));



		return courseList;

	}

}
/*
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
*/
