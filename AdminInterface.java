import java.util.ArrayList;

public interface AdminInterface { // Define the interface for the admin here. 
    // Each method signature below corresponds to a possible action the admin can take.
    public void create_course(ArrayList<Course> courses); // Appends a new course to the ArrayList of courses

    public void delete_course(ArrayList<Course> courses); // Deletes a specified course from the ArrayList of courses

    public void edit_course(ArrayList<Course> courses); // Edits a specified attribute of a specified course

    public void display(ArrayList<Course> courses, String course_ID); // Displays the information for a specific course based on the given course ID

    public void file_full_courses(ArrayList<Course> courses); // Writes to a file the list of courses that are full 

    public void names_for_course(ArrayList<Course> courses); // Displays the names of the students registered in a particular course

    public void sort_courses(ArrayList<Course> courses); // Sorts the courses from greatest to least according to the number of students enrolled per course
}
