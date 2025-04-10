import java.util.ArrayList;
import java.io.Serializable;

public class Course implements Serializable { // course class; used to create 'Course' objects; implements serialization
    private static final long serialVersionUID = 1L;
    private String name; // name of the course
    private String id; // course id
    private int max_num; // maximum number of students for the course
    private int current_num; // current number of students for the course
    private ArrayList<String> student_list = new ArrayList<>(); // names of students currently being registered into the course
    private String instructor; // instructor
    private int section_number; // section number
    private String location; // location of course

    // course constructor, the values of the instance fields are set to the corresponding arguments passed through the constructor
    public Course(String name, String id, int max_num, int current_num, ArrayList<String> names, String instructor, int section_number, String location) {
        this.name = name;
        this.id = id;
        this.max_num = max_num;
        this.current_num = current_num;
        this.student_list = names;
        this.instructor = instructor;
        this.section_number = section_number;
        this.location = location;
    }

    public String get_course_name() { // Returns the course name
        return name;
    }

    public String get_course_id() { // Returns the course id
        return id;
    }

    public int get_max_student() { // Returns the max number of students
        return max_num;
    }

    public int get_current_student() { // Returns the current number of students
        return current_num;
    }

    public ArrayList<String> get_Student_ArrayList() {
        return student_list;
    }

    public String get_student_names() { // Returns the current list of names of students
        String list_of_Names = "";
        for (String student_name : student_list) {
            list_of_Names += (student_name + ", ");
        }
        return list_of_Names;
    }

    public String get_instructor() { // Returns the instructor
        return instructor;
    }

    public int get_section_number() { // Returns the section number
        return section_number;
    }

    public String get_location() { // Returns the location
        return location;
    }

    public void set_max_students(int num) { // setter method to change the maximum number of students
        max_num = num;
    }

    public void set_instructor(String instructor) { // setter method to change the instructor
        this.instructor = instructor;
    }

    public void set_student_list(String first_name, String last_name, String action) { // Returns the ArrayList of students registered for a course to allow adding or removing of students
        if (action.equals("Add")) {
            student_list.add(first_name + " " + last_name); // Adds the student to the ArrayList of student names
            current_num++;
        }
        else if (action.equals("Remove")) {
            student_list.remove(first_name + " " + last_name); // Removes the student from the ArrayList of student names
            current_num--;
        }
    }   

    public void set_section_number(int num) { // setter method to change the section number
        this.section_number = num;
    }

    public void set_location(String location) {
        this.location = location;
    }

    public String get_course_info(String user_Type) { // Returns information for the course; useful for various tasks of the admin and student
        String information = "";
        if (user_Type.equals("Admin")) { // The information that the admin sees differs from the information the student sees
        information = "Course Name: " + name + ", ";
        information += "Course ID: " + id + ", ";
        information += "Maximum Number of Students: " + max_num + ", ";
        information += "Current Number of Students: " + current_num + ", ";
        information += "List of Students: " + get_student_names() + ", ";
        information += "Instructor: " + instructor + ", ";
        information += "Section Number: " + section_number + ", ";
        information += "Location: " + location;
        }
        else { // The information that the student sees differs from the information that the admin sees
        information = "Course Name: " + name + ", ";
        information += "Course ID: " + id + ", ";
        information += "Instructor: " + instructor + ", ";
        information += "Section Number: " + section_number + ", ";
        information += "Location: " + location;
        }
        return information;
    }
}
    

    

    