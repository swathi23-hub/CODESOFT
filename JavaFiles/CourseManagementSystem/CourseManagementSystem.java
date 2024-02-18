import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private String schedule;
    private List<Student> registeredStudents;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.registeredStudents = new ArrayList<>();
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }

    public List<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    public void addStudent(Student student) {
        registeredStudents.add(student);
    }

    public void removeStudent(Student student) {
        registeredStudents.remove(student);
    }

    public int getAvailableSlots() {
        return capacity - registeredStudents.size();
    }
}

class Student {
    private int id;
    private String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

class CourseDatabase {
    private List<Course> courses;

    public CourseDatabase() {
        this.courses = new ArrayList<>();
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }

    public Course findCourseByCode(String code) {
        for (Course course : courses) {
            if (course.getCode().equals(code)) {
                return course;
            }
        }
        return null;
    }
}

class StudentDatabase {
    private List<Student> students;

    public StudentDatabase() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getStudents() {
        return students;
    }

    public Student findStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }
}

public class CourseManagementSystem {
    private CourseDatabase courseDatabase;
    private StudentDatabase studentDatabase;

    public CourseManagementSystem() {
        this.courseDatabase = new CourseDatabase();
        this.studentDatabase = new StudentDatabase();
    }

    public void displayAvailableCourses() {
        List<Course> courses = courseDatabase.getCourses();
        System.out.println("Available Courses:");
        for (Course course : courses) {
            System.out.println("Code: " + course.getCode());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Capacity: " + course.getCapacity());
            System.out.println("Schedule: " + course.getSchedule());
            System.out.println("Available Slots: " + course.getAvailableSlots());
            System.out.println();
        }
    }

    public void registerStudentForCourse(Student student, Course course) {
        if (course.getAvailableSlots() > 0) {
            course.addStudent(student);
            System.out.println("Student " + student.getName() + " registered successfully for course " + course.getTitle());
        } else {
            System.out.println("Course " + course.getTitle() + " is already full. Registration failed.");
        }
    }

    public void removeStudentFromCourse(Student student, Course course) {
        course.removeStudent(student);
        System.out.println("Student " + student.getName() + " removed successfully from course " + course.getTitle());
    }

    public static void cls()
    {
        try
        {	
            new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
        }
        catch(Exception E)
        {
            System.out.println(E);
        }
    }

    public static void main(String[] args) {
        CourseManagementSystem cms = new CourseManagementSystem();
        Scanner scanner = new Scanner(System.in);

        // Sample courses
        Course c1 = new Course("CSCI101", "Introduction to Computer Science", "Basic concepts of programming", 30, "Mon/Wed/Fri 9-10AM");
        Course c2 = new Course("MATH201", "Calculus I", "Limits, derivatives, and integrals", 25, "Tue/Thu 1-3PM");
        Course c3 = new Course("ENG101", "English Composition", "Writing skills and grammar", 20, "Mon/Wed 11AM-12PM");

        cms.courseDatabase.addCourse(c1);
        cms.courseDatabase.addCourse(c2);
        cms.courseDatabase.addCourse(c3);

        // Sample students
        Student s1 = new Student(1, "John");
        Student s2 = new Student(2, "Emma");
        Student s3 = new Student(3, "Michael");

        cms.studentDatabase.addStudent(s1);
        cms.studentDatabase.addStudent(s2);
        cms.studentDatabase.addStudent(s3);

        boolean continueApplication = true;
        while (continueApplication) {
            System.out.println("Welcome to the Course Management System!");
            System.out.println("Enter your student ID to log in (or type 'exit' to quit):");
            String input = scanner.nextLine();
            cls();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("\nExiting the Course Management System. Goodbye!");
                break;
            }

            int studentId;
            try {
                studentId = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("\nInvalid input. Please enter a valid student ID.");
                continue;
            }

            Student loggedInStudent = cms.studentDatabase.findStudentById(studentId);
            if (loggedInStudent == null) {
                System.out.println("\nStudent ID not found. Please try again.");
                continue;
            }
            else
            {
                System.out.println("\n Welcome "+loggedInStudent.getName());
            }

            // Display registered course if any
            boolean hasRegisteredCourse = false;
            for (Course course : cms.courseDatabase.getCourses()) {
                if (course.getRegisteredStudents().contains(loggedInStudent)) {
                    System.out.println("\nYou have already registered for the following course:");

                    for (Course course2 : cms.courseDatabase.getCourses()) {
                        if (course2.getRegisteredStudents().contains(loggedInStudent)) {
                            System.out.println(course2.getTitle());
                        }
                
                     }
                    hasRegisteredCourse = true;
                    break;
                }

            }

            if (!hasRegisteredCourse) {
                System.out.println("\nYou have not registered for any course yet.");
                System.out.println("\nDo you want to register for a course? (yes/no)");
                String choice = scanner.nextLine();
                cls();
                if (!choice.equalsIgnoreCase("yes")) {
                    continue;
                }
            } else {
                System.out.println("\nDo you want to register for another course or remove one course? (register/remove)");
                String choice = scanner.nextLine();
                cls();
                if (choice.equalsIgnoreCase("remove")) {
                    // Display registered courses and prompt for removal
                    System.out.println("Select the course you want to remove:");
                    for (Course course : cms.courseDatabase.getCourses()) {
                        if (course.getRegisteredStudents().contains(loggedInStudent)) {
                            System.out.println(course.getCode() + " - " + course.getTitle());
                        }
                    }
                    String courseCodeToRemove = scanner.nextLine();
                    cls();
                    Course courseToRemove = cms.courseDatabase.findCourseByCode(courseCodeToRemove);
                    if (courseToRemove != null && courseToRemove.getRegisteredStudents().contains(loggedInStudent)) {
                        cms.removeStudentFromCourse(loggedInStudent, courseToRemove);
                        //System.out.println("\nCourse removed successfully.");
                    } else {
                        System.out.println("\nInvalid course code or you are not registered for this course.");
                    }
                    continue;
                }
            }

            boolean continueRegistration = true;
            while (continueRegistration) {
                cms.displayAvailableCourses();
                System.out.println("\nEnter the code of the course you want to register for (or enter 'exit' to quit):");
                String courseCode = scanner.nextLine();
                cls();
                if (courseCode.equalsIgnoreCase("exit")) {
                    continueApplication = false;
                    continueRegistration = false;
                    break;
                }

                Course selectedCourse = cms.courseDatabase.findCourseByCode(courseCode);
                if (selectedCourse == null) {
                    System.out.println("\nInvalid course code. Please try again.");
                    continue;
                }

                if (selectedCourse.getRegisteredStudents().contains(loggedInStudent)) {
                    System.out.println("\nYou have already registered for this course.");
                    continue;
                }

                cms.registerStudentForCourse(loggedInStudent, selectedCourse);
                //System.out.println("Student " + loggedInStudent.getName() + " registered successfully for course " + selectedCourse.getTitle() + ".");

                System.out.println("\nDo you want to register for another course? (yes/no)");
                String choice = scanner.nextLine();
                cls();
                if (!choice.equalsIgnoreCase("yes")) {
                    continueRegistration = false;
                }
            }
        }

        scanner.close();
    }
}
