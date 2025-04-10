import java.util.*;
import java.io.*;

public class Main {
    
    public static void main(String[] args) {
        ArrayList<Course> courses = null; // Creates the ArrayList to store courses with their respective information
        ArrayList<Student> student_Body = new ArrayList<>(); // The student body ArrayList will store all registered students, regardless of the courses they are taking
        
        File serFile = new File("courses.ser"); 
        if (serFile.exists()) {
            // Load courses from the serialized file
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(serFile))) {
                courses = (ArrayList<Course>) ois.readObject();
                System.out.println("Loaded courses from serialized file.");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                // If something goes wrong, create a new list
                courses = new ArrayList<>();
            }
        } 
        else {
            courses = new ArrayList<>();
            String csvFile = "MyUniversityCourses.csv"; // Path to the csv file
            System.out.println("The original CSV file is going to be read.");
            String line;
            int counter = 1; // This counter is used to skip the first line of the csv file which gives the labels for the courses (as opposed to actual data)
            try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) { // Uses Buffered Reader to read in the csv file
                while ((line = br.readLine()) != null) {
                    if (counter == 1) { 
                        counter --; // 'Skips' the first line
                    }
                    else {
                        String[] fields = line.split(","); // Creates an array, with each element a required property of the course
                        Course particular_course = new Course(fields[0], fields[1], Integer.parseInt(fields[2]), Integer.parseInt(fields[3]), new ArrayList<String>(), fields[5], Integer.parseInt(fields[6]), fields[7]);
                        courses.add(particular_course);
                        // Instantiates a course object for each line of the csv file and adds it to the ArrayList
                    }
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        File studentFile = new File("students.ser");
        if (studentFile.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(studentFile))) {
                student_Body = (ArrayList<Student>) ois.readObject();
                System.out.println("Loaded registered students from serialized file.");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                student_Body = new ArrayList<>();
            }
        } else {
            student_Body = new ArrayList<>();
        }
        Admin admin = new Admin("Admin", "Admin001", "Roger", "Federer"); // Create the admin object
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the course registration system."); 
        System.out.println("Are you an admin or student?"); // Asks the user if they are the admin or a student
        String response = input.nextLine();
        while (!response.toLowerCase().equals("admin") && !response.toLowerCase().equals("student")) { // checks to make sure input is valid
            System.out.println("Not a valid input. Please enter either 'admin' or 'student'.");
            response = input.nextLine();
        }
        if (response.toLowerCase().equals("admin")) { // This body runs if the user selects 'admin'
            System.out.println("Username: ");
            String username = input.nextLine();
            while (!username.equals(admin.get_username()) && !username.toLowerCase().equals("q")) { // Loop continues to run until the admin types in the correct username
                System.out.println("Incorrect Username. Please try again. You can also quit by typing 'q'.");
                username = input.nextLine();
                if (username.equals("q")) {
                    saveCourses(courses); // Serialization method is called to save the changes made to the system
                    System.out.println("Courses have been saved. Exiting...");
                    saveStudents(student_Body);
                    System.exit(0);
                }
            }
            System.out.println("Password: ");
            String password = input.nextLine();
            while (!password.equals(admin.get_password()) && !password.toLowerCase().equals("q")) { // Loop continues to run until the admin types in the correct username
                System.out.println("Incorrect Password. Please try again. You can also quit by typing 'q'.");
                password = input.nextLine();
                if (password.equals("q")) {
                    saveCourses(courses); // Serialization method is called to save the changes made to the system
                    System.out.println("Courses have been saved. Exiting...");
                    saveStudents(student_Body);
                    System.exit(0);
                }
            }
            System.out.println("Thank you for verifying your identity. What would you like to do? Enter either '1' for course registration or '2' for reports. Feel free to quit by typing 'q'.");
            String choice = input.nextLine(); // Admin can choose between 'Course Management' or 'Reports'
            while (!choice.equals("1") && !choice.equals("2") && !choice.toLowerCase().equals("q")) { // checks to see if input is valid
                System.out.println("Not a valid choice. Please select 'Course Registration', 'Reports',  or 'q' to quit");
                choice = input.nextLine();
            }
            if (choice.equals("1")) {
                boolean quit_activated = false; // variable to account for when the admin decides to terminate the program
                while (!quit_activated) {
                    System.out.println("Type in the number (e.g. '1' corresponding to the choice that you want:"); // display the six options for the admin
                    System.out.println("1. Create a New Course");
                    System.out.println("2. Delete a Course");
                    System.out.println("3. Edit a Course");
                    System.out.println("4. Display Information for a Given Course");
                    System.out.println("5. Register a Student");
                    System.out.println("6. Quit");
                    String selection = input.nextLine();
                    while (!selection.equals("1") && !selection.equals("2") && !selection.equals("3") && !selection.equals("4") && !selection.equals("5") && !selection.equals("6")) { // Loop continues to run until admin types in a proper number choice
                        System.out.println("Not a valid choice. Please type in a number from 1 to 6 for your choice. Here are the choices listed for you again: ");
                        System.out.println("Type in the number (e.g. '1' corresponding to the choice that you want:"); // display the six options for the admin
                        System.out.println("1. Create a New Course");
                        System.out.println("2. Delete a Course");
                        System.out.println("3. Edit a Course");
                        System.out.println("4. Display Information for a Given Course");
                        System.out.println("5. Register a Student");
                        System.out.println("6. Exit");
                        selection = input.nextLine();
                    }
                    if (selection.equals("1")) { // Creates a new course; obtains the values of the attributes for the new course
                        admin.create_course(courses);
                    }
                    else if (selection.equals("2")) { // Deletes a course in the list
                        admin.delete_course(courses);
                    }
                    else if (selection.equals("3")) { // Edits a specific aspect of a specific course
                        admin.edit_course(courses);                
                    }
                    else if (selection.equals("4")) { // Displays the information for a course given the course ID
                        System.out.println("Type in the course ID for the course you want the information for");
                        String id = input.nextLine();
                        admin.display(courses, id);
                    }
                    else if(selection.equals("5")) { 
                        // Record the current size of Admin's student list
                        int originalSize = admin.getStudentBody().size();
                        
                        // Call register, which reads input and adds a new student to admin's student_Body
                        admin.register(courses);
                        
                        // Retrieve the updated list from Admin
                        ArrayList<Student> adminStudents = admin.getStudentBody();
                        
                        // Process only the new students added (from originalSize to end)
                        for (int i = originalSize; i < adminStudents.size(); i++) { // This checks to see if the inputted username or password is a duplicate
                            Student newStudent = adminStudents.get(i);
                            boolean duplicate = false;
                            // Check for duplicate username
                            for (Student s : student_Body) {
                                if (s.get_username().equals(newStudent.get_username())) {
                                    duplicate = true;
                                    break;
                                }
                            }
                            // Check for duplicate password
                            for (Student s : student_Body) {
                                if (s.get_password().equals(newStudent.get_password())) {
                                    duplicate = true;
                                    break;
                                }
                            }
                            // If no duplicate is found, add the new student to the global list
                            if (!duplicate) {
                                student_Body.add(newStudent);
                                System.out.println("Student is successfully registered");
                            } else {
                                System.out.println("A registered student is already using this username or password so the registration failed.");
                            }
                        }
                    }
                    else if (selection.equals("6")) { // Exits the menu and ends the admin session
                        quit_activated = true;
                    }
                }
                saveCourses(courses); // Serialization method is called to save the changes made to the system
                System.out.println("Courses have been saved. Exiting...");
                saveStudents(student_Body);
                System.exit(0);
            }
            else if (choice.equals("2")) {
                boolean quit_activated = false; // variable to account for when the admin decides to terminate the program
                while (!quit_activated) {
                    System.out.println("Type in the number (e.g. '1' corresponding to the choice that you want:"); // display the seven options for the admin
                    System.out.println("1. View all Courses");
                    System.out.println("2. View all FULL Courses");
                    System.out.println("3. Write to a file the list of courses that are full");
                    System.out.println("4. View the names of students in a specific course");
                    System.out.println("5. View the list of courses for a particular student");
                    System.out.println("6. Sort the courses based on the current number of students registered (greatest to least)");
                    System.out.println("7. Exit");
                    String selection = input.nextLine();
                    while (!selection.equals("1") && !selection.equals("2") && !selection.equals("3") && !selection.equals("4") && !selection.equals("5") && !selection.equals("6") && !selection.equals("7")) { // Loop continues to run until admin types in a proper number choice
                        System.out.println("Type in the number (e.g. '1' corresponding to the choice that you want:"); // display the seven options for the admin
                        System.out.println("1. View all Courses");
                        System.out.println("2. View all FULL Courses");
                        System.out.println("3. Write to a file the list of courses that are full");
                        System.out.println("4. View the names of students in a specific course");
                        System.out.println("5. View the list of courses for a particular student");
                        System.out.println("6. Sort the courses based on the current number of students registered (greatest to least)");
                        System.out.println("7. Exit");
                        selection = input.nextLine();
                    }
                    if (selection.equals("1")) { // Display information for ALL courses
                        admin.view_courses(courses, false);
                    }
                    else if (selection.equals("2")) { // Display information for the courses that are full
                        admin.view_courses(courses, true);
                    }
                    else if (selection.equals("3")) { // Write to a file the list of courses that are full
                        admin.file_full_courses(courses);            
                    }
                    else if (selection.equals("4")) { // View the names of students in a specific course
                        admin.names_for_course(courses);
                    }
                    else if (selection.equals("5")) { // View the list of courses for a particular student
                        System.out.println("Enter the student's first name: "); // We require the admin to enter the first and last name to identify the proper student
                        String first_name = input.nextLine();
                        System.out.println("Enter the student's last name: ");
                        String last_name = input.nextLine();
                        admin.view_courses(courses, first_name, last_name);
                    }
                    else if (selection.equals("6")) { // Sort the courses
                        admin.sort_courses(courses);
                    }
                    else if (selection.equals("7")) { // Exits the menu and ends the admin session
                        quit_activated = true;
                    }
                }
                saveCourses(courses); // Serialization method is called to save the changes made to the system
                System.out.println("Courses have been saved. Exiting...");
                saveStudents(student_Body);
                System.exit(0);
            }
            else if (choice.toLowerCase().equals("q")) {
                saveCourses(courses); // Serialization method is called to save the changes made to the system
                System.out.println("Courses have been saved. Exiting...");
                saveStudents(student_Body);
                System.exit(0);
            }
        }
        else if (response.toLowerCase().equals("student")) { // This body runs if the user selects 'student'
            Student verified_Student = null;
            System.out.println("Username: "); // Asks the student to enter their username
            String username = input.nextLine();
            boolean found = false;
            while (true) {
                for (Student student : student_Body) {
                    if (student.get_username().equals(username)) {
                        verified_Student = student;
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Incorrect Username. It is also possible that you haven't been registered as a student yet. Please contact the admin if you have any issues. You may try again or type 'q' to quit.");
                    username = input.nextLine();
                }
                else {
                    break;
                }
            }
            System.out.println("Password: ");
            String password = input.nextLine();
            found = false;
            while (true) {
                for (Student student : student_Body) {
                    if (student.get_password().equals(password)) {
                        found = true;
                        break;
                    }
                }
                if (!found) {
                    System.out.println("Incorrect Password. It is also possible that you haven't been registered as a student yet. Please contact the admin if you have any issues. You may try again or type 'q' to quit.");
                    password = input.nextLine();
                }
                else {
                    break;
                }
            }
            System.out.println("Welcome, " + verified_Student.get_first_name() + " " + verified_Student.get_last_name());
            System.out.println("Thank you for verifying your identity. What would you like to do?"); // Student verified
            boolean quit_activated = false; // variable to account for when the student decides to terminate the program
            while (!quit_activated) {
                System.out.println("Type in the number (e.g. '1' corresponding to the choice that you want:"); // display the six options for the student
                System.out.println("1. View all Courses");
                System.out.println("2. View all Courses that are Not Full");
                System.out.println("3. Register for a Course");
                System.out.println("4. Withdraw from a Course");
                System.out.println("5. View all Courses the Student is Currently Registered In");
                System.out.println("6. Quit");
                String selection = input.nextLine();
                while (!selection.equals("1") && !selection.equals("2") && !selection.equals("3") && !selection.equals("4") && !selection.equals("5") && !selection.equals("6")) { // Loop continues to run until admin types in a proper number choice
                    System.out.println("Not a valid choice. Please type in a number from 1 to 6 for your choice. Here are the choices listed for you again: ");
                    System.out.println("Type in the number (e.g. '1' corresponding to the choice that you want:"); // display the six options for the student
                    System.out.println("1. View all Courses");
                    System.out.println("2. View all Courses that are Not Full");
                    System.out.println("3. Register for a Course");
                    System.out.println("4. Withdraw from a Course");
                    System.out.println("5. View all Courses the Student is Currently Registered In");
                    System.out.println("6. Quit");
                    selection = input.nextLine();
                }
                if (selection.equals("1")) { // Displays course information for all courses
                    verified_Student.view_courses(courses, false);
                }
                else if (selection.equals("2")) { // Displays course information only for courses that are not full
                    verified_Student.view_courses(courses, true);
                }
                else if (selection.equals("3")) { // Registers a student into a specified course
                    verified_Student.register(courses);
                }
                else if (selection.equals("4")) { // Withdraws a student from a specified course
                    verified_Student.withdraw_from_course(courses);
                }
                else if (selection.equals("5")) { // Displays information on the courses the student is currently registered in
                    verified_Student.view_courses(courses);      
                }       
                else if (selection.equals("6")) { // Exits the menu and ends the student session
                    quit_activated = true;
                }
            }
            saveCourses(courses); // Serialization method is called to save the changes made to the system
            saveStudents(student_Body);
            System.out.println("Courses have been saved. Exiting...");
            System.exit(0);
        }
        else {
            saveCourses(courses); // Serialization method is called to save the changes made to the system
            saveStudents(student_Body);
            System.out.println("Courses have been saved. Exiting...");
            System.exit(0);
        }
    }
    // Method to serialize the courses ArrayList
    public static void saveCourses(ArrayList<Course> courses) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("courses.ser"))) {
            oos.writeObject(courses);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void saveStudents(ArrayList<Student> students) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students.ser"))) {
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


