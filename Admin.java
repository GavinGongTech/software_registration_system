import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Admin extends User implements AdminInterface { // Admin class that implements the admin and serializable interface and extends the User class
    // There is only one admin, so we only need one username and password
    private static final long serialVersionUID = 1L;
    transient Scanner input = new Scanner(System.in);

    private ArrayList<Student> student_Body = new ArrayList<>(); // The student body ArrayList will store all registered students, regardless of the courses they are taking

    public ArrayList<Student> getStudentBody() {
        return student_Body;
    }
    
    public Admin(String username, String password, String first_name, String last_name) {
        super(username, password, first_name, last_name); // There is a set username and password for the admin
    }

    public void create_course(ArrayList<Course> courses) { // Creates a new course into the system. Admin is required to ascertain the specific aspects of the course
        String[] specified_information = specify_course("Admin"); // Calls the inherited specify_course from the user class (not overridden)
        boolean is_new_course = true; // Variable to check if the created course is a new course
        for (Course course : courses) {
            if ((course.get_course_name().equals(specified_information[0])) && (course.get_course_id().equals(specified_information[1])) && (course.get_max_student() == Integer.parseInt(specified_information[2])) && (course.get_instructor().equals(specified_information[3])) && (course.get_section_number() == Integer.parseInt(specified_information[4])) && (course.get_location().equals(specified_information[5]))) { // Checks to see if all the significant course attributes match with an existing course
                is_new_course = false;
                System.out.println("Course already exists");
                break; 
            }
        }
        if (is_new_course) { // if confirmed new course, we add to the ArrayList
            Course new_course = new Course(specified_information[0], specified_information[1], Integer.parseInt(specified_information[2]), 0, new ArrayList<String>(), specified_information[3], Integer.parseInt(specified_information[4]), specified_information[5]);
            courses.add(new_course);
            System.out.println("Course has been created");
        }
    }

    public void delete_course(ArrayList<Course> courses) { // Removes a specified course from the ArrayList of courses
        String[] specified_information = specify_course("Admin"); // Calls the inherited specify_course from the user class (not overridden)
        boolean course_found = false; // Variable to check if the requested course to delete exists
        for (Course course : courses) {
            if ((course.get_course_name().equals(specified_information[0])) && (course.get_course_id().equals(specified_information[1])) && (course.get_max_student() == Integer.parseInt(specified_information[2])) && (course.get_instructor().equals(specified_information[3])) && (course.get_section_number() == Integer.parseInt(specified_information[4])) && (course.get_location().equals(specified_information[5]))) { // Checks to see if all the significant course attributes match with an existing course
                courses.remove(course);
                course_found = true;
                System.out.println("Course has been deleted");
                break; 
            }
        }
        if (!course_found) { // if confirmed course does not exist, no change is made to the ArrayList
            System.out.println("Course not found");
        }
    }

    public void edit_course(ArrayList<Course> courses) { // Edits a specific attribute of a specific course
        String[] specified_information = specify_course("Admin"); // Calls the inherited specify_course from the user class (not overridden)
        System.out.println("Enter the number corresponding to the attribute you would like to edit.");
        System.out.println("Note that the course name and the course ID are unchangable.");
        System.out.println("1: Maximum Number of Students");
        System.out.println("2: Course Instructor");
        System.out.println("3: Section Number");
        System.out.println("4: Location");
        String attribute = input.nextLine(); 
        while (!attribute.equals("1") && !attribute.equals("2") && !attribute.equals("3") && !attribute.equals("4")) { // Checks if user input is valid
            System.out.println("Not a valid choice. Please choose either '1', '2', '3', or '4'.");
            attribute = input.nextLine();
        }
        if (attribute.equals("1")) { // Inquire for the desired edit to max student number
            System.out.println("Enter the new maximum number of students");
            int new_max = input.nextInt();
            input.nextLine();
            for (int i = 0; i < courses.size(); i++) { // parse through the ArrayList to find the corresponding course
                if ((courses.get(i).get_course_name().equals(specified_information[0])) && (courses.get(i).get_course_id().equals(specified_information[1])) && (courses.get(i).get_max_student() == Integer.parseInt(specified_information[2])) && (courses.get(i).get_instructor().equals(specified_information[3])) && (courses.get(i).get_section_number() == Integer.parseInt(specified_information[4])) && (courses.get(i).get_location().equals(specified_information[5]))) { // Checks to see if all the significant course attributes match with an existing course
                    courses.get(i).set_max_students(new_max);
                    System.out.println("Edit successfully made");
                    break;
                }
            }
        }
        if (attribute.equals("2")) { // Inquire for the desired edit to course instructor
            System.out.println("Enter the new course instructor");
            String new_instructor = input.nextLine();
            for (int i = 0; i < courses.size(); i++) { // parse through the ArrayList to find the corresponding course
                if ((courses.get(i).get_course_name().equals(specified_information[0])) && (courses.get(i).get_course_id().equals(specified_information[1])) && (courses.get(i).get_max_student() == Integer.parseInt(specified_information[2])) && (courses.get(i).get_instructor().equals(specified_information[3])) && (courses.get(i).get_section_number() == Integer.parseInt(specified_information[4])) && (courses.get(i).get_location().equals(specified_information[5]))) { // Checks to see if all the significant course attributes match with an existing course
                    courses.get(i).set_instructor(new_instructor);
                    System.out.println("Edit successfully made");
                    break;
                }
            }
        }
        if (attribute.equals("3")) { // Inquire for the desired edit to course section number
            System.out.println("Enter the new section number");
            int new_section_number = input.nextInt();
            input.nextLine();
            for (int i = 0; i < courses.size(); i++) { // parse through the ArrayList to find the corresponding course
                if ((courses.get(i).get_course_name().equals(specified_information[0])) && (courses.get(i).get_course_id().equals(specified_information[1])) && (courses.get(i).get_max_student() == Integer.parseInt(specified_information[2])) && (courses.get(i).get_instructor().equals(specified_information[3])) && (courses.get(i).get_section_number() == Integer.parseInt(specified_information[4])) && (courses.get(i).get_location().equals(specified_information[5]))) { // Checks to see if all the significant course attributes match with an existing course
                    courses.get(i).set_section_number(new_section_number);
                    System.out.println("Edit successfully made");
                    break;
                }
            }
        }
        if (attribute.equals("4")) { // Inquire for the desired edit to course location
            System.out.println("Enter the new course location");
            String new_location = input.nextLine();
            for (int i = 0; i < courses.size(); i++) { // parse through the ArrayList to find the corresponding course
                if ((courses.get(i).get_course_name().equals(specified_information[0])) && (courses.get(i).get_course_id().equals(specified_information[1])) && (courses.get(i).get_max_student() == Integer.parseInt(specified_information[2])) && (courses.get(i).get_instructor().equals(specified_information[3])) && (courses.get(i).get_section_number() == Integer.parseInt(specified_information[4])) && (courses.get(i).get_location().equals(specified_information[5]))) { // Checks to see if all the significant course attributes match with an existing course
                    courses.get(i).set_location(new_location);
                    System.out.println("Edit successfully made");
                    break;
                }
            }
        }
    }

    public void display(ArrayList<Course> courses, String course_ID) { // Takes the current ArrayList of courses and the desired course_ID as parameters
        ArrayList<Course> same_ID_courses = new ArrayList<>(); // ArrayList to store the classes with the desired course_ID. If greater than 1, further specification needed to determine the course to display
        for (Course course : courses) {
            if (course.get_course_id().equals(course_ID)) { // Checks to see if the course id attribute matches
                same_ID_courses.add(course);
            }
        }
        if (same_ID_courses.size() == 0) { // Meaning no course was found
            System.out.println("Course not found with this course ID.");
        }
        else if (same_ID_courses.size() == 1) { // Optimal case where only one course with this ID was found
            System.out.println(same_ID_courses.get(0).get_course_info("Admin")); // Calls the get_course_info method in the course class that displays the course information in a readable manner
        }
        else {
            System.out.println("Not enough information. Please provide the section number of the desired course: ");
            int sect = input.nextInt();
            input.nextLine();
            boolean course_found = false;
            for (Course course : same_ID_courses) {
                if (course.get_section_number() == sect) {
                    System.out.println(course.get_course_info("Admin")); // Calls the get_course_info method in the course class that displays the course information in a readable manner
                } 
            }
            if (!course_found) { // executes if course is still not found
                System.out.println("Could not find the requested course");
            }
        }
    }

    public void register(ArrayList<Course> courses) { // overriding the register method for the admin (Overriding)
        // This version of the register method allows the Admin to register a student into the system, but not to a particular course
        System.out.println("To register a student, you need to enter their first name, last name, username, and password");
        System.out.println("Start by entering their first name: ");
        String first_name = input.nextLine();
        System.out.println("Now enter the student's last name: ");
        String last_name = input.nextLine();
        System.out.println("Now enter the student's username. Note that usernames chosen must be unique.");
        String username = input.nextLine();
        System.out.println("Now enter the student's password. Note that passwords chosen must be unique.");
        String password = input.nextLine();
        Student student = new Student(username, password, first_name, last_name);
        student_Body.add(student); // Once a student has been registered, it is added to the student body; however, we check in the main if it uses a duplicate username or password
    }
 
    public void view_courses(ArrayList<Course> courses, boolean Full) { // Overrides the abstract method 'view_courses' in the User class to either display ALL COURSES to the admin or ALL COURSES that are full (Overriding)
    // For admin, each course will display information on list of enrolled students, their respective student IDs, the total number of registered students, and the maximum number of students that can be registered
        if (Full) { // This block runs if the admin only wants to see full courses
            for (Course course : courses) {
                if (course.get_max_student() == course.get_current_student()) { // Which means the course is full
                    System.out.println(course.get_student_names()); // Prints out the names of all students registered in the courses that are full
                    for (Student student : student_Body) {
                        if (student.get_student_courses().contains(course)) {
                            System.out.print(student.get_username() + ", "); // Prints out the student IDs (usernames) of all registered students
                        }
                    }
                    System.out.println("Current Number of Students: " + course.get_current_student()); // Prints out the current number of students
                    System.out.println("Maximum Number of Students: " + course.get_max_student()); // Prints out the maximum number of students
                }                    
            }
        }
        else { // This block runs if the admin wants to see ALL courses
            for (Course course : courses) {  
                System.out.println(course.get_student_names()); // Prints out the names of all students registered in the courses that are full
                for (Student student : student_Body) {
                    if (student.get_student_courses().contains(course)) {
                        System.out.print(student.get_username() + ", "); // Prints out the student IDs (usernames) of all registered students
                    }
                }
                System.out.println("Current Number of Students: " + course.get_current_student()); // Prints out the current number of students
                System.out.println("Maximum Number of Students: " + course.get_max_student()); // Prints out the maximum number of students                   
            }
        }
    }

    public void view_courses(ArrayList<Course> courses, String first_name, String last_name) { // Overrides and overloads the abstract method 'view_courses' in the User class to display the courses for a particular student (Overloading and Overriding)
        for (Student student : student_Body) {
            if (student.get_first_name().equals(first_name) && student.get_last_name().equals(last_name)) { // Identifies the proper student based on their first and last name
                for (Course course : student.get_student_courses()) { 
                    System.out.print(course.get_course_name() + ", "); // Prints out the courses the student is registered in a readable manner
                }
                break;
            }
        }
    }

    public void file_full_courses(ArrayList<Course> courses) { // Writes to a file the list of courses that are full 
        String fileName = "fullCourses.txt"; // The file to write to
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Course course : courses) {
                if (course.get_max_student() == course.get_current_student()) { // Which means the course is full
                    writer.println(course.get_course_info("Admin")); // Calls the get_course_info method which returns information for a course in a readable manner
                    System.out.println("File has been written");
                }
            }
        } 
        catch (IOException e) {
            // If an I/O error occurs, print the stack trace
            e.printStackTrace();
        }
    }

    public void names_for_course(ArrayList<Course> courses) { // Displays the names of the students registered in a particular course
        String[] specified_information = specify_course("Admin"); // Calls the inherited specify_course from the user class (not overridden)
        boolean course_exists = false;
        for (Course course : courses) {
            if ((course.get_course_name().equals(specified_information[0])) && (course.get_course_id().equals(specified_information[1])) && (course.get_max_student() == Integer.parseInt(specified_information[2])) && (course.get_instructor().equals(specified_information[3])) && (course.get_section_number() == Integer.parseInt(specified_information[4])) && (course.get_location().equals(specified_information[5]))) { // Checks to see if all the significant course attributes match with an existing course
                course_exists = true;
                System.out.println(course.get_student_names()); // calls the get_student_names method from the Course class to display the student names in a readable manner
                break;
            }
        }
        if (!course_exists) { // tells the admin the requested course could not be found
            System.out.println("The course does not exist.");
        }
    }

    public void sort_courses(ArrayList<Course> courses) { // Sorts the courses from greatest to least according to the number of students enrolled per course
        for (int i = 0; i < courses.size(); i++) { // Perform a merge sort on the ArrayList of courses to organize them from greatest to least (in terms of number of registered students)
            for (int j = 0; j < courses.size() - 1; j++) {
                if (courses.get(j).get_current_student() < courses.get(j + 1).get_current_student()) {
                    Course temp = courses.get(j); // create a temporary variable so that information is not lost
                    courses.set(j, courses.get(j + 1));
                    courses.set(j + 1, temp);
                }
            }
        }
        System.out.println("Courses have been sorted");
    }
}
