import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CwFinal {

    static int seats = 100; // total seats
    private static int index = 0; // array element index
    private static String[] names = new String[100]; // name array
    private static String[] id = new String[100]; // id array
    private static Student[] students = new Student[100]; // student array
    static Scanner s = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {

            displayMenu();
            try {

                choice = s.nextInt();
                switch (choice) {
                    case 1:
                        checkAvailableSeats();
                        break;
                    case 2:
                        registerStudent();
                        break;
                    case 3:
                        deleteStudent();
                        break;
                    case 4:
                        findStudent();
                        break;
                    case 5:
                        storeStudent();
                        break;
                    case 6:
                        loadStudent();
                        break;
                    case 7:
                        viewStudent();
                        break;
                    case 8:
                        manageStudentModules();
                        break;
                    case 9:
                        System.out.println("Exiting the program. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 8.");
                s.next(); // clear invalid input
                choice = 0; // reset choice
            }

        } while (choice != 9);
    }

    public static void displayMenu() {
        System.out.println("\t ______\n \t |MENU| \n");
        System.out.println("1. Check available seats");
        System.out.println("2. Register student (with ID)");
        System.out.println("3. Delete student");
        System.out.println("4. Find student (with student ID)");
        System.out.println("5. Store student details into a file");
        System.out.println("6. Load student details from the file to the system");
        System.out.println("7. View the list of students based on their names");
        System.out.println("8. Manage student modules");
        System.out.println("9. Exit");
        System.out.println("\nEnter your choice : 1-8 : ");
    }

    public static void checkAvailableSeats() {

        System.out.println("\nIf you need load data file to system: \n\t Enter 6 to Yes\n\t Enter 0 to No ");
        int yes=s.nextInt();
        if(yes==6){
            loadStudent();
            checkAvailableSeatsprocess();
        }else if(yes==0){
            checkAvailableSeatsprocess();
        }else{
            System.out.println("invalid input");
        }
    }

    public static void checkAvailableSeatsprocess(){
        System.out.println("\n * Check available seats : " + seats + " seats are available");
        System.out.println("\n Do you want to add new student \n\n\t Enter number |1| for yes \n\t Enter number |2| for no ");

        try {

            int cChoice = s.nextInt();
            if (cChoice == 1) {
                registerStudent();
            } else if (cChoice == 2) {
                System.out.println(" ");
            } else {
                System.out.println("Invalid input.");
            }

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter 1 or 2.");
            s.next(); // clear invalid input
        }
    }

    public static void registerStudent() {

        if (seats > 0) {
            System.out.println("Firstly you need load data file to system: \n\t Enter 6 to Continue :");

            try {
                int yes = s.nextInt();
                if (yes == 6) {

                    loadStudent();
                    System.out.println("\n Register student (with ID)");
                    try {

                        System.out.print("\nEnter student ID: w");
                        String studentID = s.next();
                        if (studentID.length() == 7) {
                            String StudentID2 = "w" + studentID;
                            System.out.print("Enter student name with initials | ex - P.W.W.Perera : ");
                            String studentName = s.next();
                            System.out.println("\n Student registered successfully.");
                            System.out.println("\t Name: " + studentName);
                            System.out.println("\t ID  : " + StudentID2);
                            names[index] = studentName;
                            id[index] = StudentID2;
                            students[index] = new Student(StudentID2, studentName);
                            index++;
                            seats--;
                            System.out.println("\n Do you want to add new student \n\n\t Enter number 1 for yes \n\t Enter number 2 for no \n\t Enter number 3 for save file to details");
                            int rChoice = s.nextInt();
                            if (rChoice == 1) {
                                storeStudent();
                                registerStudent();
                            } else if (rChoice == 2) {
                                System.out.println(" ");
                            } else if (rChoice == 3) {
                                storeStudent();
                            } else {
                                System.out.println("Invalid input.");
                            }
                        } else {
                            System.out.println("Enter valid ID. Try again.");
                        }

                    } catch (InputMismatchException e) {
                        System.out.println("ERROR: Invalid input. Please enter a valid integer for the student ID.");
                        s.next(); // clear invalid input
                    }

                } else {
                    System.out.println("Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter 6 to continue.");
                s.next(); // clear invalid input
            }
        } else {
            System.out.println("No seats available. Cannot register more students.");
        }
    }

    public static void deleteStudent() {

        System.out.println("\n Delete student");
        System.out.println(" ");
        for (int i = 1; i <= index; i++) {
            System.out.println(i + ".) " + names[i - 1] + " - " + id[i - 1]);
        }

        try {
            System.out.print("\n Enter the index of the element to remove: ");
            int del = s.nextInt();
            if (del <= index && del > 0) {
                for (int x = del - 1; x < index - 1; x++) {
                    id[x] = id[x + 1];
                    names[x] = names[x + 1];
                    students[x] = students[x + 1];
                }
                id[index - 1] = null; // Clear last element
                names[index - 1] = null; // Clear last element
                students[index - 1] = null; // Clear last element
                index--; // Decrement index to reflect the new size
                seats++; // Increment available seats
                System.out.println("Student removed successfully.");
                System.out.println("\n Do you want to delete another student \n\n\t Enter number 1 for yes \n\t Enter number 2 for no \n\t Enter number 3 for save file to details");
                int dChoice = s.nextInt();
                if (dChoice == 1) {
                    deleteStudent();
                } else if (dChoice == 2) {
                    System.out.println(" ");
                } else if (dChoice == 3) {
                    storeStudent();
                } else {
                    System.out.println("Invalid input.");
                }
            } else {
                System.out.println("Invalid index.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid index.");
            s.next(); // clear invalid input
        }
    }

    public static void findStudent() {

        System.out.println("\n Find student (with student ID)");
        try {
            System.out.print("Enter the student ID to search: w");
            int searchId = s.nextInt();
            String searchID2 = "w" + searchId;
            for (int i = 0; i < index; i++) {
                if (id[i].equals(searchID2)) {
                    System.out.println("\nStudent found:");
                    System.out.println("Name: " + names[i]);
                    System.out.println("ID: " + id[i]);
                    break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid student ID.");
            s.next(); // clear invalid input
        }
    }

    public static void storeStudent() {

        System.out.println("\n Store student details into a file");
        try {
            FileWriter studentStore = new FileWriter("textfile.txt");
            for (int i = 0; i < index; i++) {
                studentStore.write(names[i] + " " + id[i] + "\n");
            }
            System.out.println("Successfully added students to the file.");
            studentStore.close();
        } catch (IOException e) {
            System.out.println("An error occurred while storing student details.");
        }
    }

    public static void loadStudent() {

        try {
            File file = new File("textfile.txt");
            Scanner reader = new Scanner(file);
            index = 0;
            seats = 100;
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] parts = data.split(" ");
                names[index] = parts[0];
                id[index] = parts[1];
                students[index] = new Student(parts[1], parts[0]);
                index++;
                seats--;
            }
            reader.close();
            System.out.println("Student details loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while loading student details.");
        }
    }

    public static void viewStudent() {

        System.out.println("\n View the list of students based on their names");
        System.out.println(" ");
        for (int i = 1; i <= index; i++) {
            System.out.println(i + ".) " + names[i - 1] + " - " + id[i - 1]);
        }
    }

    public static void manageStudentModules() {

        System.out.println("\n Manage student modules \n\n\ta. Add student\n\tb. Add module marks\n\tc. View student grades\n\td. Return to main menu");
        System.out.print("\nEnter your choice: ");
        try {
            String choice = s.next();
            switch (choice) {
                case "a":
                    registerStudent();
                    break;
                case "b":
                    addModuleMarks();
                    break;
                case "c":
                    viewStudentGrades();
                    break;
                case "d":
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    manageStudentModules();
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid option.");
            s.next(); // clear invalid input
            manageStudentModules();
        }
    }

    public static void addModuleMarks() {

        System.out.println("\n Add module marks");
        try {
            System.out.print("Enter the student ID to add marks: w");
            int searchId = s.nextInt();
            String searchID2 = "w" + searchId;
            for (int i = 0; i < index; i++) {
                if (id[i].equals(searchID2)) {
                    System.out.println("Enter marks for each module : ");
                    for (int z = 0; z < 3; z++) {
                        System.out.print("Module " + (z + 1) + ": ");
                        int marks = s.nextInt();
                        students[i].setModuleMark(z, marks);
                    }
                    System.out.println("Marks added successfully.");
                    break;
                }else{
                    System.out.println("\nStudent with ID " + searchID2 + " not found.");
                    break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter valid marks.");
            s.next(); // clear invalid input
        }
    }

    public static void viewStudentGrades() {
        
        System.out.println("\n View student grades");
        for (int i = 0; i < index; i++) {
            System.out.println("Student ID: " + students[i].getStudentID());
            System.out.println("Student Name: " + students[i].getStudentName());
            int[] marks = students[i].getModuleMarks();
            for (int z = 0; z < marks.length; z++) {
                System.out.println("Module " + (z + 1) + " Marks: " + marks[z]);
            }
            System.out.println("Grade: " + students[i].getGrade());
            System.out.println();
        }
    }
}