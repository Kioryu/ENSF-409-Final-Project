# ENSF-409-Final-Project
This project builds off from the registration system introduced in Lab 3 Exercise 2. Some architectural changes include: client-server implementation, multithreading, and GUI implementation. 
All the data (courseList and studentList) are stored in .dat files accessed through using the RandomAccessFile Class.

## Group Members
* Aaron Li (aaron.li1@ucalgary.ca)
* Jian Shi Chen (jianshi.chen@ucalgary.ca)

## How to Run the Application
1. After downloading and decompressing the file. Open two command windows to run the server (java Server.Server) and client (java Client.ClientSocket).
* Access as a student:
2. Enter a valid student ID (any number between 650320-650340) to access the student registration GUI.
3. Follow the GUI prompts to search, add, drop courses, view catalogue or view all registered courses

* Access as an admin:
2. Enter following credential to access the admin GUI:
    * ID: 123456
    * Password: peanut
3. Follow the GUI prompts to add course, add student or turn off the server

* Important Notes:
   * Do not type space after input in JTextFields
   * Do not click Drop course or Add course button when TextFields are empty
  
## Additional Features
* Added user login feature (typing in the correct studentID)
* Added admin GUI for adding new courses, adding new students and terminate server


## Notes
* Server and Client code were modified from class example
