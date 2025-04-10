import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;

public class Student extends User implements StudentInterface { // Student class that implements the Student and serializable interface and extends the User Class
    // Each method signature below corresponds to a possible action the user can take.
    private ArrayList<Course> student_courses = new ArrayList<>(); // ArrayList of courses a particular student is registered in

    public Student(String username, String password, String first_name, String last_name) { // Student constructor that takes username, password, first name, and last name as parameters and calls super
        super(username, password, first_name, last_name);
    }

    public ArrayList<Course> get_student_courses() { // Returns the ArrayList of courses a particular student is registered in
        return student_courses;
    }

    public void view_courses(ArrayList<Course> courses, boolean not_Full) { // Overrides the abstract method 'view_courses' in the User class to either display ALL COURSES to the student or ALL COURSES that are not full (Overriding)
        // For student, each course will display information on the course name, id, instructor, section number, and location
        if (not_Full) { // Meaning the student only wants to see courses that are not full
            for (Course course : courses) {
                if (course.get_max_student() != course.get_current_student()) { // Which means the course is full
                    System.out.println(course.get_course_info("Student")); // Displays the proper course information to the student
                }                         
            }
        }
        else { // Meaning the student wants to see all courses
            for (Course course : courses) {
                System.out.println(course.get_course_info("Student")); // Displays the proper course information to the student
            }
        }
    }

    public void view_courses(ArrayList<Course> courses) { // Overrides and overloads the abstract method 'view_courses' in the User class to display only information for the courses the student is currently registered in (Overloading and Overriding)
        for (Course course : student_courses) { // Only provides info on the courses that the student is registered for
            System.out.println(course.get_course_info("Student"));
        }
    }

    public void register(ArrayList<Course> courses) { // Overrides the register method in the User class for the Student; student needs class name, section, and first and last name (Overriding)
        Scanner input = new Scanner(System.in);
        System.out.println("Please type in your requested course");
        String name = input.nextLine();
        System.out.println("Next, please type in the section number");
        int sect = input.nextInt();
        input.nextLine();
        System.out.println("Type in your first name");
        String first_name = input.nextLine();
        System.out.println("Type in your last name");
        String last_name = input.nextLine();
        for (Course course : courses) { // Finds the corresponding course the student wants to register for
            if ((course.get_course_name().equals(name)) && (course.get_section_number() == sect)) { 
                if (course.get_current_student() == course.get_max_student()) { // Makes sure the class is not full
                    System.out.println("Course is full. Please wait until later for a spot to open up");
                }
                else {
                    course.set_student_list(first_name, last_name, "Add"); // Adds the student name to the registered student list and increments the number by 1
                    student_courses.add(course); // Adds the courses to the student's list
                    System.out.println("You have been successfully registered into the class");
                    break;
                }
            }
        }
    }
        

    public void withdraw_from_course(ArrayList<Course> courses) {
        Scanner input = new Scanner(System.in);
        System.out.println("Please type in the course name you want to withdraw from:");
        String name = input.nextLine();
        System.out.println("Type in your first name:");
        String first_name = input.nextLine();
        System.out.println("Type in your last name:");
        String last_name = input.nextLine();
    
        // Use an iterator to safely remove the course from student_courses
        Iterator<Course> iterator = student_courses.iterator();
        boolean courseFound = false;
        while (iterator.hasNext()) {
            Course course = iterator.next();
            // Check if this is the course the student wants to withdraw from.
            // You might also want to check for section number or course id if needed.
            if (course.get_course_name().equals(name) &&
                course.get_Student_ArrayList().contains(first_name + " " + last_name)) {
                
                // Remove the student from the course's student list (and adjust count, etc.)
                course.set_student_list(first_name, last_name, "Remove");
                // Safely remove the course from the student's registered courses list
                iterator.remove();
                System.out.println("You have been successfully removed from the course.");
                courseFound = true;
                break;
            }
        }
        
        if (!courseFound) {
            System.out.println("You are not registered in that course or the course name is incorrect.");
        }
    }
}
