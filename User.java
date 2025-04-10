import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public abstract class User implements Serializable { // Abstract User class that implements serializable; our admin and student class will extend this abstract user class
    public String username;
    public String password;
    public String first_name;
    public String last_name;
    transient Scanner input = new Scanner(System.in);
    public User(String username, String password, String first_name, String last_name) {
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
    }

    public String get_username() { // getter method to return the username
        return username;
    }

    public String get_password() { // getter method to return the password
        return password; 
    }

    public String get_first_name() { // getter method to return the first name
        return first_name;
    }

    public String get_last_name() { // getter method to return the last name
        return last_name;
    }

    public abstract void register(ArrayList<Course> courses); // abstract method to register a student (either into the system or into a course); implementation will differ depending on whether admin or student is calling the method

    public abstract void view_courses(ArrayList<Course> courses, boolean full); // displays information about selective courses to the user based on certain criteria

    public String[] specify_course(String user_Type) { // whenever the user wants to identify a course, this method is called
        String[] specified_information;
        if (user_Type.equals("Admin")) { // specifications required for the Admin when identifying a course
            System.out.println("Enter the name of the course: ");
            String name = input.nextLine();
            System.out.println("Enter the course ID: ");
            String id = input.nextLine();
            System.out.println("Enter the maximum number of students: ");
            int num = input.nextInt();
            input.nextLine();
            System.out.println("Enter the course instructor: ");
            String instructor = input.nextLine();
            System.out.println("Enter the course section number (should be an integer): ");
            int section_num = input.nextInt();
            input.nextLine();
            System.out.println("Enter the course location: ");
            String location = input.nextLine();
            specified_information = new String[]{name, id, Integer.toString(num), instructor, Integer.toString(section_num), location};
        }
        else { // Specifications for student when identifying a course
            System.out.println("Enter the name of the course: ");
            String name = input.nextLine();
            System.out.println("Enter the course ID: ");
            String id = input.nextLine();
            System.out.println("Enter the course instructor: ");
            String instructor = input.nextLine();
            System.out.println("Enter the course section number (should be an integer): ");
            int section_num = input.nextInt();
            input.nextLine();
            System.out.println("Enter the course location: ");
            String location = input.nextLine();
            specified_information = new String[]{name, id, instructor, Integer.toString(section_num), location};
        }
        return specified_information;
    }
}
