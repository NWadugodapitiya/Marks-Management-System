import java.util.Scanner;

public class MarksManagementSystem {

    static String[] studentIds = new String[100];
    static String[] studentNames = new String[100];
    static int[] pfMarks = new int[100];
    static int[] dbmsMarks = new int[100];
    static boolean[] marksAdded = new boolean[100];

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = getChoice();

            switch (choice) {
                case 1:
                    addNewStudent();
                    break;
                case 2:
                    addNewStudentWithMarks();
                    break;
                case 3:
                    addMarks();
                    break;
                case 4:
                    updateStudentDetails();
                    break;
                case 5:
                    updateMarks();
                    break;
                case 6:
                    deleteStudent();
                    break;
                case 7:
                    printStudentDetails();
                    break;
                case 8:
                    printStudentRanks();
                    break;
                case 9:
                    bestInProgrammingFundamentals();
                    break;
                case 10:
                    bestInDatabaseManagementSystem();
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("WELCOME TO GDSE MARKS MANAGEMENT SYSTEM");
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("[1] Add New Student");
        System.out.println("[2] Add New Student With Marks");
        System.out.println("[3] Add Marks");
        System.out.println("[4] Update Student Details");
        System.out.println("[5] Update Marks");
        System.out.println("[6] Delete Student");
        System.out.println("[7] Print Student Details");
        System.out.println("[8] Print Student Ranks");
        System.out.println("[9] Best in Programming Fundamentals");
        System.out.println("[10] Best in Database Management System");
        System.out.print("Enter an option to continue > ");
    }

    private static int getChoice() {
        return scanner.nextInt();
    }

    private static void addNewStudent() {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("ADD NEW STUDENT");
        System.out.println("----------------------------------------------------------------------------");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.next();

        if (isStudentIdExists(studentId)) {
            System.out.println("Student ID already exists. Please enter a valid Student ID.");
            addNewStudent();
            return;
        }

        System.out.print("Enter Student Name: ");
        String studentName = scanner.next();

        int index = findEmptyIndex();
        studentIds[index] = studentId;
        studentNames[index] = studentName;
        marksAdded[index] = false;

        System.out.println("Student added successfully.");

        System.out.print("Do you want to add another student? (Y/n): ");
        String choice = scanner.next();
        if (!choice.equalsIgnoreCase("Y")) {
            return;
        }

        addNewStudent();
    }

    private static void addNewStudentWithMarks() {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("ADD NEW STUDENT WITH MARKS");
        System.out.println("----------------------------------------------------------------------------");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.next();

        if (!isStudentIdExists(studentId)) {
            System.out.println("Student ID does not exist. Please add the student details first.");
            return;
        }

        int index = getStudentIndexById(studentId);

        if (marksAdded[index]) {
            System.out.println("This student's marks have been already added. Use [4] Update Marks option.");
            return;
        }

        System.out.print("Enter Programming Fundamentals Marks: ");
        int pfMarksInput = validateMarksInput();

        System.out.print("Enter Database Management System Marks: ");
        int dbmsMarksInput = validateMarksInput();

        pfMarks[index] = pfMarksInput;
        dbmsMarks[index] = dbmsMarksInput;
        marksAdded[index] = true;

        System.out.println("Marks added successfully.");

        System.out.print("Do you want to add marks for another student? (Y/n): ");
        String choice = scanner.next();
        if (!choice.equalsIgnoreCase("Y")) {
            return;
        }

        addNewStudentWithMarks();
    }

    private static void addMarks() {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("ADD MARKS");
        System.out.println("----------------------------------------------------------------------------");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.next();

        if (!isStudentIdExists(studentId)) {
            System.out.println("Student ID does not exist. Please add the student details first.");
            return;
        }

        int index = getStudentIndexById(studentId);

        if (marksAdded[index]) {
            System.out.println("This student's marks have been already added. If you want to update the marks, use [5] Update Marks option.");
            return;
        }

        System.out.println("Enter Marks for Student: " + studentNames[index]);

        System.out.print("Enter Programming Fundamentals Marks: ");
        int pfMarksInput = validateMarksInput();

        System.out.print("Enter Database Management System Marks: ");
        int dbmsMarksInput = validateMarksInput();

        pfMarks[index] = pfMarksInput;
        dbmsMarks[index] = dbmsMarksInput;
        marksAdded[index] = true;

        System.out.println("Marks added successfully.");

        System.out.print("Do you want to add marks for another student? (Y/n): ");
        String choice = scanner.next();
        if (!choice.equalsIgnoreCase("Y")) {
            return;
        }

        addMarks();
    }


    private static void updateStudentDetails() {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("UPDATE STUDENT DETAILS");
        System.out.println("----------------------------------------------------------------------------");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.next();

        if (!isStudentIdExists(studentId)) {
            System.out.println("Student ID does not exist. Please enter a valid Student ID.");
            return;
        }

        int index = getStudentIndexById(studentId);

        System.out.println("Current Details:");
        System.out.println("Student ID: " + studentIds[index]);
        System.out.println("Current Student Name: " + studentNames[index]);

        System.out.print("Enter the new Student Name: ");
        String newStudentName = scanner.next();

        studentNames[index] = newStudentName;

        System.out.println("Student details have been updated successfully.");

        System.out.print("Do you want to update another student's details? (Y/n): ");
        String choice = scanner.next();
        if (!choice.equalsIgnoreCase("Y")) {
            return;
        }

        updateStudentDetails();
    }


    private static void updateMarks() {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("UPDATE MARKS");
        System.out.println("----------------------------------------------------------------------------");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.next();

        if (!isStudentIdExists(studentId)) {
            System.out.println("Student ID does not exist. Please enter a valid Student ID.");
            return;
        }

        int index = getStudentIndexById(studentId);

        if (!marksAdded[index]) {
            System.out.println("This student's marks have not been added yet. If you want to add marks, use [3] Add Marks option.");
            return;
        }

        System.out.println("Enter New Marks for Student: " + studentNames[index]);

        System.out.print("Enter new Programming Fundamentals Marks: ");
        int newPfMarks = validateMarksInput();

        System.out.print("Enter new Database Management System Marks: ");
        int newDbmsMarks = validateMarksInput();

        pfMarks[index] = newPfMarks;
        dbmsMarks[index] = newDbmsMarks;

        System.out.println("Marks have been updated successfully.");

        System.out.print("Do you want to update marks for another student? (Y/n): ");
        String choice = scanner.next();
        if (!choice.equalsIgnoreCase("Y")) {
            return;
        }

        updateMarks();
    }


    private static void deleteStudent() {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("DELETE STUDENT");
        System.out.println("----------------------------------------------------------------------------");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.next();

        if (!isStudentIdExists(studentId)) {
            System.out.println("Invalid Student ID. Do you want to search again? (Y/n): ");
            String searchAgain = scanner.next();
            if (searchAgain.equalsIgnoreCase("Y")) {
                deleteStudent();
            }
            return;
        }

        int index = getStudentIndexById(studentId);

        studentIds[index] = null;
        studentNames[index] = null;
        pfMarks[index] = 0;
        dbmsMarks[index] = 0;
        marksAdded[index] = false;

        System.out.println("Student has been deleted successfully.");

        System.out.print("Do you want to delete another student? (Y/n): ");
        String choice = scanner.next();
        if (!choice.equalsIgnoreCase("Y")) {
            return;
        }

        deleteStudent();
    }


    private static void printStudentDetails() {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("PRINT STUDENT DETAILS");
        System.out.println("----------------------------------------------------------------------------");

        System.out.print("Enter Student ID: ");
        String studentId = scanner.next();

        if (!isStudentIdExists(studentId)) {
            System.out.println("Invalid Student ID. Please enter a valid Student ID.");
            return;
        }

        int index = getStudentIndexById(studentId);

        System.out.println("Student ID: " + studentIds[index]);
        System.out.println("Student Name: " + studentNames[index]);

        if (marksAdded[index]) {
            System.out.println("| Programming Fundamentals Marks: " + pfMarks[index]);
            System.out.println("| Database Management System Marks: " + dbmsMarks[index]);
            System.out.println("| Total Marks: " + (pfMarks[index] + dbmsMarks[index]));
            System.out.println("| Avg. Marks: " + ((pfMarks[index] + dbmsMarks[index]) / 2.0));
            System.out.println("| Rank: " + getStudentRank(index));
        } else {
            System.out.println("Marks yet to be added");
        }

        System.out.print("Do you want to search another student's details? (Y/n): ");
        String choice = scanner.next();
        if (!choice.equalsIgnoreCase("Y")) {
            return;
        }

        printStudentDetails();
    }

    private static String getStudentRank(int studentIndex) {
        // Implementation for calculating student rank
        // You can customize the ranking logic based on your requirements
        // This is a simple example using total marks for ranking
        int totalMarks = pfMarks[studentIndex] + dbmsMarks[studentIndex];

        int rank = 1;
        for (int i = 0; i < studentIds.length; i++) {
            if (marksAdded[i] && (pfMarks[i] + dbmsMarks[i] > totalMarks)) {
                rank++;
            }
        }

        String rankText;
        if (rank == 1) {
            rankText = "First";
        } else if (rank == 2) {
            rankText = "Second";
        } else if (rank == 3) {
            rankText = "Third";
        } else {
            rankText = "Last";
        }

        return rank + " (" + rankText + ")";
    }


    private static void printStudentRanks() {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("PRINT STUDENTS' RANKS");
        System.out.println("----------------------------------------------------------------------------");

        // Sort students based on total marks (descending order)
        int[] totalMarksArray = new int[studentIds.length];
        for (int i = 0; i < studentIds.length; i++) {
            if (marksAdded[i]) {
                totalMarksArray[i] = pfMarks[i] + dbmsMarks[i];
            }
        }

        for (int i = 0; i < totalMarksArray.length - 1; i++) {
            for (int j = i + 1; j < totalMarksArray.length; j++) {
                if (totalMarksArray[i] < totalMarksArray[j]) {
                    swapStudents(i, j);
                    swapTotalMarks(totalMarksArray, i, j);
                }
            }
        }

        // Display ranks and details
        System.out.printf("%-5s%-5s%-15s%-15s%-15s%-15s\n", "Rank", "ID", "Name", "Total Marks", "Avg. Marks", "Status");
        for (int i = 0; i < studentIds.length; i++) {
            if (marksAdded[i]) {
                int totalMarks = pfMarks[i] + dbmsMarks[i];
                double avgMarks = (pfMarks[i] + dbmsMarks[i]) / 2.0;
                System.out.printf("%-5d%-5s%-15s%-15d%-15.2f%-15s\n", i + 1, studentIds[i], studentNames[i], totalMarks, avgMarks, getRankStatus(i));
            }
        }

        System.out.print("Do you want to go back to the main menu? (Y/n): ");
        String choice = scanner.next();
        if (!choice.equalsIgnoreCase("Y")) {
            return;
        }
    }

    private static void swapStudents(int i, int j) {
        String tempId = studentIds[i];
        studentIds[i] = studentIds[j];
        studentIds[j] = tempId;

        String tempName = studentNames[i];
        studentNames[i] = studentNames[j];
        studentNames[j] = tempName;

        int tempPfMarks = pfMarks[i];
        pfMarks[i] = pfMarks[j];
        pfMarks[j] = tempPfMarks;

        int tempDbmsMarks = dbmsMarks[i];
        dbmsMarks[i] = dbmsMarks[j];
        dbmsMarks[j] = tempDbmsMarks;

        boolean tempMarksAdded = marksAdded[i];
        marksAdded[i] = marksAdded[j];
        marksAdded[j] = tempMarksAdded;
    }

    private static void swapTotalMarks(int[] totalMarksArray, int i, int j) {
        int tempTotalMarks = totalMarksArray[i];
        totalMarksArray[i] = totalMarksArray[j];
        totalMarksArray[j] = tempTotalMarks;
    }

    private static String getRankStatus(int studentIndex) {
        int rank = 1;
        for (int i = 0; i < studentIds.length; i++) {
            if (marksAdded[i] && (pfMarks[i] + dbmsMarks[i] > pfMarks[studentIndex] + dbmsMarks[studentIndex])) {
                rank++;
            }
        }

        String rankText;
        if (rank == 1) {
            rankText = "First";
        } else if (rank == 2) {
            rankText = "Second";
        } else if (rank == 3) {
            rankText = "Third";
        } else {
            rankText = "Last";
        }

        return rank + " (" + rankText + ")";
    }

    private static void bestInProgrammingFundamentals() {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("BEST IN PROGRAMMING FUNDAMENTALS");
        System.out.println("----------------------------------------------------------------------------");

        // Sort students based on Programming Fundamentals marks (descending order)
        int[] pfMarksArray = new int[studentIds.length];
        for (int i = 0; i < studentIds.length; i++) {
            if (marksAdded[i]) {
                pfMarksArray[i] = pfMarks[i];
            }
        }

        for (int i = 0; i < pfMarksArray.length - 1; i++) {
            for (int j = i + 1; j < pfMarksArray.length; j++) {
                if (pfMarksArray[i] < pfMarksArray[j]) {
                    swapStudents(i, j);
                    swapPfMarks(pfMarksArray, i, j);
                }
            }
        }

        // Display best in Programming Fundamentals
        System.out.printf("%-5s%-5s%-15s%-15s\n", "Rank", "ID", "Name", "PF Marks");
        for (int i = 0; i < studentIds.length; i++) {
            if (marksAdded[i]) {
                System.out.printf("%-5d%-5s%-15s%-15d\n", i + 1, studentIds[i], studentNames[i], pfMarksArray[i]);
            }
        }

        System.out.print("Do you want to go back to the main menu? (Y/n): ");
        String choice = scanner.next();
        if (!choice.equalsIgnoreCase("Y")) {
            return;
        }
    }

    private static void swapPfMarks(int[] pfMarksArray, int i, int j) {
        int tempPfMarks = pfMarksArray[i];
        pfMarksArray[i] = pfMarksArray[j];
        pfMarksArray[j] = tempPfMarks;
    }

    private static void bestInDatabaseManagementSystem() {
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("BEST IN DATABASE MANAGEMENT SYSTEM");
        System.out.println("----------------------------------------------------------------------------");

        // Sort students based on Database Management System marks (descending order)
        int[] dbmsMarksArray = new int[studentIds.length];
        for (int i = 0; i < studentIds.length; i++) {
            if (marksAdded[i]) {
                dbmsMarksArray[i] = dbmsMarks[i];
            }
        }

        for (int i = 0; i < dbmsMarksArray.length - 1; i++) {
            for (int j = i + 1; j < dbmsMarksArray.length; j++) {
                if (dbmsMarksArray[i] < dbmsMarksArray[j]) {
                    swapStudents(i, j);
                    swapDbmsMarks(dbmsMarksArray, i, j);
                }
            }
        }

        // Display best in Database Management System
        System.out.printf("%-5s%-5s%-15s%-15s\n", "Rank", "ID", "Name", "DBMS Marks");
        for (int i = 0; i < studentIds.length; i++) {
            if (marksAdded[i]) {
                System.out.printf("%-5d%-5s%-15s%-15d\n", i + 1, studentIds[i], studentNames[i], dbmsMarksArray[i]);
            }
        }

        System.out.print("Do you want to go back to the main menu? (Y/n): ");
        String choice = scanner.next();
        if (!choice.equalsIgnoreCase("Y")) {
            return;
        }
    }

    private static void swapDbmsMarks(int[] dbmsMarksArray, int i, int j) {
        int tempDbmsMarks = dbmsMarksArray[i];
        dbmsMarksArray[i] = dbmsMarksArray[j];
        dbmsMarksArray[j] = tempDbmsMarks;
    }


    private static boolean isStudentIdExists(String studentId) {
        for (int i = 0; i < studentIds.length; i++) {
            if (studentIds[i] != null && studentIds[i].equals(studentId)) {
                return true;
            }
        }
        return false;
    }

    private static int findEmptyIndex() {
        for (int i = 0; i < studentIds.length; i++) {
            if (studentIds[i] == null) {
                return i;
            }
        }
        return -1; // Array is full
    }

    private static int getStudentIndexById(String studentId) {
        for (int i = 0; i < studentIds.length; i++) {
            if (studentIds[i] != null && studentIds[i].equals(studentId)) {
                return i;
            }
        }
        return -1; // Student not found
    }

    private static int validateMarksInput() {
        int marks;
        while (true) {
            try {
                marks = scanner.nextInt();
                if (marks < 0 || marks > 100) {
                    throw new IllegalArgumentException("Marks should be between 0 and 100.");
                }
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid integer between 0 and 100.");
                scanner.nextLine(); // Consume the invalid input
            }
        }
        return marks;
    }
}
