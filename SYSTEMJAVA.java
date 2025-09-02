import java.io.*;
import java.util.*;

/*
JAVA STUDENT MANAGEMENT SYSTEM - METHOD CATALOG
===============================================

This document explains all the object.method() operations used in the code.

1. FILE AND DIRECTORY OPERATIONS
--------------------------------
These methods interact with the computer's file system.

file.exists()
- Purpose: Checks if a file or directory exists on disk
- Example: if (!folder.exists())
- Explanation: Asks "Does the 'users' folder already exist?"

file.mkdir()
- Purpose: Creates a new directory
- Example: folder.mkdir()
- Explanation: "Create the 'users' folder now"

file.listFiles(filter)
- Purpose: Returns list of files in directory, optionally filtered
- Example: folder.listFiles((d,n) -> n.endsWith(".txt"))
- Explanation: "Get all .txt files from the 'users' folder"

file.getName()
- Purpose: Returns just the filename without path
- Example: file.getName()
- Explanation: "What is this file's name?"

file.delete()
- Purpose: Permanently deletes a file from disk
- Example: studentFile.delete()
- Explanation: "Delete this student's file permanently"

new FileWriter(filename)
- Purpose: Creates tool to write text to a file
- Example: new FileWriter(filename)
- Explanation: "Open this file for writing"

2. SCANNER OPERATIONS (USER INPUT)
----------------------------------
These methods handle user input from keyboard.

sc.nextLine()
- Purpose: Reads entire line of user input
- Example: String input = sc.nextLine()
- Explanation: "Wait for user to type and press Enter, then save input"

sc.nextLine().trim()
- Purpose: Removes spaces from start/end of input
- Example: sc.nextLine().trim()
- Explanation: "Clean up the input by removing extra spaces"

sc.nextLine().toUpperCase()
- Purpose: Converts input to uppercase letters
- Example: sc.nextLine().toUpperCase()
- Explanation: "Convert 'y' to 'Y' for easier comparison"

3. STRING OPERATIONS
--------------------
These methods work with text data.

string.trim()
- Purpose: Removes whitespace from both ends
- Example: input.trim()
- Explanation: "Clean up user input by removing spaces"

string.toUpperCase()
- Purpose: Converts string to uppercase
- Example: confirm.toUpperCase()
- Explanation: "Make everything uppercase for consistent comparison"

string.isEmpty()
- Purpose: Checks if string has no characters
- Example: input.isEmpty()
- Explanation: "Did the user just press Enter without typing anything?"

string.matches(pattern)
- Purpose: Checks if string matches a pattern
- Example: input.matches("[a-zA-Z ]+")
- Explanation: "Does this input contain only letters and spaces?"

string.equalsIgnoreCase(text)
- Purpose: Compares strings ignoring case
- Example: input.equalsIgnoreCase("X")
- Explanation: "Did user type 'x' or 'X'?"

string.replace(old, new)
- Purpose: Replaces parts of string
- Example: file.getName().replace(".txt", "")
- Explanation: "Remove the .txt extension from filename"

4. FILE WRITER OPERATIONS
-------------------------
writer.write(text)
- Purpose: Writes text to a file
- Example: writer.write("Name: " + name)
- Explanation: "Save this text to the file"

5. PROCESS BUILDER (SCREEN CLEARING)
------------------------------------
new ProcessBuilder("cmd", "/c", "cls")
- Purpose: Executes system command to clear screen
- Example: new ProcessBuilder("cmd", "/c", "cls")
- Explanation: "Run the Windows 'cls' command to clear console"

inheritIO()
- Purpose: Connects system command to current console
- Example: .inheritIO()
- Explanation: "Make the command output appear in our program's console"

start().waitFor()
- Purpose: Runs command and waits for it to finish
- Example: .start().waitFor()
- Explanation: "Execute the command and wait until it's done"

6. SYSTEM OPERATIONS
--------------------
System.out.println(text)
- Purpose: Prints text to console with newline
- Example: System.out.println("Hello")
- Explanation: "Display this message on screen"

System.out.print(text)
- Purpose: Prints text without newline
- Example: System.out.print("Enter name: ")
- Explanation: "Show prompt but don't move to next line yet"

System.exit(0)
- Purpose: Terminates the program
- Example: System.exit(0)
- Explanation: "Close the program completely"

7. ARRAY OPERATIONS
-------------------
array.length
- Purpose: Returns number of items in array
- Example: files.length
- Explanation: "How many student files are there?"

8. SPECIAL OPERATORS
--------------------
ternary operator (?:)
- Purpose: Short if-else statement
- Example: condition ? valueIfTrue : valueIfFalse
- Explanation: "If condition is true, use first value, else use second"

try-with-resources
- Purpose: Automatically closes files when done
- Example: try (FileWriter writer = ...)
- Explanation: "Automatically close the file when finished writing"

ANSI Escape Sequences
- Purpose: Controls text color and formatting in console
- Example: \u001B[31m (red text), \u001B[0m (reset)
- Explanation: "Special codes to make colored text in terminal"*/

class FormState {
    public static String name = "", lrn = "", strand = "";
    public static void reset() { name = ""; lrn = ""; strand = ""; }
}

public class SYSTEMJAVA 
{
    private static final File folder = new File("users");
    private static final Scanner sc = new Scanner(System.in);
    private static final String banner = "\u001b[30m===================================================================\u001B[0m\n" +
        "=   \u001B[31m  ▄▄█▀▀▀█▄█                                                   \u001B[0m=\n" +
        "=   \u001B[31m▄██▀     ▀█                                          \u001B[0m ▄▄▄     =\n" +
        "=   \u001B[31m██▀       ▀▀███▄███  ▄██▀██▄ \u001B[0m▀███  ▀███ ▀████████▄    ▀███    =\n" +
        "=   \u001B[31m██           ██▀ ▀▀ ██▀   ▀██ \u001B[0m ██    ██   ██   ▀██      ██    =\n" +
        "=   \u001B[31m██▄    ▀████ ██     ██     ██ \u001B[0m ██    ██   ██    ██      ██    =\n" +
        "=   \u001B[31m▀██▄     ██  ██     ██▄   ▄██ \u001B[0m ██    ██   ██   ▄██      ██    =\n" +
        "=   \u001B[31m  ▀▀███████▄████▄    ▀█████▀ \u001B[0m ▀████▀███▄ ██████▀     ▄████▄   =\n" +
        "=   \u001B[31m                                     \u001B[0m    ██                   =\n" +
        "\u001b[30m===============================================================+===\u001B[0m";
    private static final String menu = "\n\n\u001B[1mXXXX Student Management System.\u001B[0m\n" +
        "\n\u001b[47m\u001B[1mPress 1 | ➤ \u001B[0m to \u001B[31mAdd a new student\u001B[0m" +
        "\n\u001b[47m\u001B[1mPress 2 | ➤ \u001B[0m to \u001B[31mView all students\u001B[0m" +
        "\n\u001b[47m\u001B[1mPress 3 | ➤ \u001B[0m to \u001B[31mDelete a student file\u001B[0m" +
        "\n\u001b[47m\u001B[1mPress 4 | ➤ \u001B[0m to \u001B[31mView data of student\u001B[0m" +
        "\n\u001b[47m\u001B[1mPress 5 | ➤ \u001B[0m to \u001B[31mSee the members of the group\u001B[0m" +
        "\n\u001b[47m\u001B[1mPress 6 | ➤ \u001B[0m to \u001B[31mExit the program\u001B[0m";
    private static final String members = "\n\n\u001B[1mMembers:\u001B[0m\n\n" +
        "\u001b[47m\u001b[30m 1. \u001B[0m Dave Marcos - \u001B[31mLead Developer, UI/UX Designer\u001B[0m\n" +
        "\u001b[47m\u001b[30m 2. \u001B[0m Johncint F. Bagonoc - \u001B[31mAssistant Developer, Flowchart designer, & Ideator\u001B[0m\n" +
        "\u001b[47m\u001b[30m 3. \u001B[0m Mico Clemente - \u001B[31mTester, Bug Catcher, Abstarct\u001B[0m\n" +
        "\u001b[47m\u001b[30m 4. \u001B[0m Harold Bazar - \u001B[31mTester, Abstract\u001B[0m\n" +
        "\u001b[47m\u001b[30m 5. \u001B[0m Jasmine Ybera - \u001B[31mIdeator, Flowchart designer\u001B[0m\n" +
        "\u001b[47m\u001b[30m 6. \u001B[0m Laniejenn Balistoy - \u001B[31mMember, Flowchart designer\u001B[0m\n";
    public static void main(String[] args) {
        if (!folder.exists()) folder.mkdir();
        String menuError = "";
        while (true) {
            clearScreen();
            System.out.println(banner + menu + "\n");
            if (!menuError.isEmpty()) System.out.println("\u001b[0m\u001b[41m\u001b[37m" + menuError + " \u001B[0m");
            menuError = "";
            System.out.print("\u001b[47m\u001b[30m Enter your choice | ➤ \u001B[0m ");
            switch (sc.nextLine()) {
                case "1": case "add": addStudent(); break;
                case "2": case "viewall": viewAllStudents(); break;
                case "3": case "del": deleteStudent(); break;
                case "4": case "view": viewStudentData(); break;
                case "5": case "mem": case "members": clearScreen(); System.out.println(banner + "" + members + "\n"); waitForEnter(); break;
                case "6": exitProgram(); break;
                default: menuError = "[!] Invalid choice! Please enter [1-6: menu options]"; break;
            }
        }
    }
    /* 
    PROGRAM StudentManagementSystem
    // Global variables
    CLASS FormState:
        STATIC name = ""
        STATIC lrn = ""
        STATIC strand = ""
        STATIC METHOD reset(): Sets all fields to empty strings
    
    // Constants
    folder = Directory "users"
    sc = Scanner for user input
    banner = ASCII art string with color codes
    menu = Menu options string with formatting
    members = Group members list string
    
    // Main method
    METHOD main():
        IF folder doesn't exist THEN create it
        menuError = ""  // For storing error messages
        
        WHILE true:  // Main program loop
            CLEAR_SCREEN()
            PRINT banner + menu
            IF menuError is not empty THEN PRINT error with red background
            PRINT prompt for menu choice
            
            READ user input
            SWITCH based on input:
                CASE "1" or "add": CALL addStudent()
                CASE "2" or "viewall": CALL viewAllStudents()
                CASE "3" or "del": CALL deleteStudent()
                CASE "4" or "view": CALL viewStudentData()
                CASE "5" or "mem" or "members": SHOW group members
                CASE "6": CALL exitProgram()
                DEFAULT: SET menuError to invalid choice message*/

    private static void addStudent() {
        FormState.reset();
        boolean addingStudent = true;
        String error = "";
        while (addingStudent) {
            clearScreen();
            System.out.println(banner + "\n\n\u001B[1mAdd New Student:\u001B[0m\n");
            if (!error.isEmpty()) { System.out.println("\u001b[41m\u001b[30m\u001B[37m" + error + " \u001B[0m"); error = ""; }

            if (FormState.name.isEmpty()) {
                System.out.print("\u001b[47m\u001b[30m Enter student name | ➤ \u001B[0m ");
                String input = sc.nextLine().trim();
                if (input.equalsIgnoreCase("X")) { addingStudent = false; continue; }
                if (input.isEmpty()) { error = "[!] Name cannot be empty - please enter student name"; continue; }
                if (!input.matches("[a-zA-Z ]+")) { error = "[!] Name should contain only letters and spaces"; continue; }
                FormState.name = input; continue;
            } else System.out.println("\u001b[47m\u001b[30m Enter student name | ➤ \u001B[0m " + FormState.name);

            if (FormState.lrn.isEmpty()) {
                System.out.print("\u001b[47m\u001b[30m Enter LRN (numbers only) | ➤ \u001B[0m ");
                String input = sc.nextLine().trim();
                if (input.equalsIgnoreCase("X")) { addingStudent = false; continue; }
                if (input.isEmpty()) { error = "[!] LRN cannot be empty - please enter student ID number"; continue; }
                if (!input.matches("\\d{12}")) { error = "[!] LRN must be 12 digits"; continue; }
                if (!input.matches("\\d+")) { error = "[!] LRN should contain only numbers (no letters or symbols)"; continue; }
                FormState.lrn = input; continue;
            } else System.out.println("\u001b[47m\u001b[30m Enter LRN (numbers only) | ➤ \u001B[0m " + FormState.lrn);

            if (FormState.strand.isEmpty()) {
                System.out.print("\u001b[47m\u001b[30m Enter strand | ➤ \u001B[0m ");
                String input = sc.nextLine().trim();
                if (input.equalsIgnoreCase("X")) { addingStudent = false; continue; }
                if (input.isEmpty()) { error = "[!] Strand cannot be empty - please enter academic strand"; continue; }
                if (!input.matches("[a-zA-Z ]+")) { error = "[!] Strand should contain only letters and spaces"; continue; }
                FormState.strand = input; continue;
            } else System.out.println("\u001b[47m\u001b[30m Enter strand | ➤ \u001B[0m " + FormState.strand);

            String confirmError = "", confirm;
            while (true) {
                clearScreen();
                System.out.println(banner + "\n\n\u001b[41m\u001b[30m\u001B[37mPlease confirm student information\u001B[0m\n");
                System.out.println("\u001b[47m\u001B[1mName | ➤ \u001B[0m " + FormState.name);
                System.out.println("\u001b[47m\u001B[1mLRN | ➤ \u001B[0m " + FormState.lrn);
                System.out.println("\u001b[47m\u001B[1mStrand | ➤ \u001B[0m " + FormState.strand + "\n");
                if (!confirmError.isEmpty()) { System.out.println("\u001b[41m\u001b[30m\u001B[37m" + confirmError + " \u001B[0m"); confirmError = ""; }
                System.out.print("\u001b[41m\u001b[30m\u001B[37mIs this correct? [Y: confirm | N: refill | X: cancel] | ➤ \u001B[0m ");
                confirm = sc.nextLine().trim().toUpperCase();
                if (confirm.equals("X")) { addingStudent = false; break; }
                else if (confirm.equals("N")) { FormState.reset(); break; }
                else if (confirm.equals("Y")) break;
                else { confirmError = "[!] Invalid choice! Please enter [Y: yes | N: no | X: cancel]"; continue; }
            }

            if (!addingStudent || confirm.equals("N")) continue;
            String filename = "users" + File.separator + FormState.name + ".txt";
            File studentFile = new File(filename);
            String overwrite = "";
            if (studentFile.exists()) {
                String overwriteError = "";
                while (true) {
                    clearScreen();
                    System.out.println(banner + "\n\n\u001b[41m\u001b[30m\u001B[37mPlease confirm student information\u001B[0m\n");
                    System.out.println("\u001b[47m\u001B[1mName | ➤ \u001B[0m " + FormState.name);
                    System.out.println("\u001b[47m\u001B[1mLRN | ➤ \u001B[0m " + FormState.lrn);
                    System.out.println("\u001b[47m\u001B[1mStrand | ➤ \u001B[0m " + FormState.strand + "\n");
                    if (!overwriteError.isEmpty()) { System.out.println("\u001b[41m\u001b[30m\u001B[37m" + overwriteError + " \u001B[0m"); overwriteError = ""; }
                    System.out.print("\u001b[41m\u001b[30m\u001B[37m[!] Student exists! Overwrite? [Y: yes, replace | N: no, keep existing] | ➤ \u001B[0m ");
                    overwrite = sc.nextLine().trim().toUpperCase();
                    if (overwrite.equals("Y")) break;
                    else if (overwrite.equals("N")) break;
                    else { overwriteError = "[!] Invalid choice! Please enter [Y: yes | N: no] only"; continue; }
                }
            }

            boolean fileSaved = false;
            String saveMessage = "";
            if (!studentFile.exists() || !overwrite.equals("N")) {
                try (FileWriter writer = new FileWriter(filename)) {
                    writer.write("Name: " + FormState.name + "\nLRN: " + FormState.lrn + "\nStrand: " + FormState.strand + "\n");
                    fileSaved = true; saveMessage = "Student saved to " + filename;
                } catch (IOException e) { saveMessage = "Error saving file: " + e.getMessage(); waitForEnter(); continue; }
            } else saveMessage = "Student information not saved";

            String continueAddingError = "", continueAdding;
            while (true) {
                clearScreen();
                System.out.println(banner + "\n");
                System.out.println(fileSaved ? "\u001b[47m\u001b[30m\u001B[30m" + saveMessage + "\u001B[0m\n" : "\u001b[41m\u001b[30m\u001B[37m" + saveMessage + "\u001B[0m\n");
                if (!continueAddingError.isEmpty()) { System.out.println("\u001b[41m\u001b[30m\u001B[37m" + continueAddingError + " \u001B[0m"); continueAddingError = ""; }
                System.out.print("\u001b[41m\u001b[30m\u001B[37mAdd another student? [Y: yes, add more | N: no, return to menu] | ➤ \u001B[0m ");
                continueAdding = sc.nextLine().trim().toUpperCase();
                if (continueAdding.equals("Y") || continueAdding.equals("N")) break;
                else continueAddingError = "[!] Invalid choice! Please enter [Y: yes | N: no] only";
            }
            if (continueAdding.equals("N")) addingStudent = false;
            FormState.reset();
        }
    }
    
    /*
    METHOD addStudent():
    CALL FormState.reset()
    addingStudent = true
    error = ""
    
    WHILE addingStudent:
        CLEAR_SCREEN()
        PRINT banner and "Add New Student" header
        IF error is not empty THEN PRINT error with red background
        
        // Name input
        IF FormState.name is empty:
            PRINT prompt for name
            READ input
            IF input is "X": SET addingStudent to false, CONTINUE loop
            IF input is empty: SET error, CONTINUE loop
            IF input contains non-letters: SET error, CONTINUE loop
            SET FormState.name to input, CONTINUE loop
        ELSE: PRINT current name
        
        // LRN input (similar validation for numbers only)
        // Strand input (similar validation for letters only)
        
        // Confirmation step
        confirmError = ""
        WHILE true:
            CLEAR_SCREEN()
            PRINT banner and confirmation header
            PRINT all entered student information
            IF confirmError is not empty: PRINT it
            PRINT prompt for confirmation (Y/N/X)
            READ confirmation input
            
            IF input is "X": SET addingStudent to false, BREAK inner loop
            IF input is "N": CALL FormState.reset(), BREAK inner loop
            IF input is "Y": BREAK inner loop
            ELSE: SET confirmError to invalid choice message
        
        IF not addingStudent OR confirmation was "N": CONTINUE main loop
        
        // File operations
        filename = "users/" + FormState.name + ".txt"
        IF file already exists:
            Ask user to confirm overwrite (Y/N)
        
        IF file doesn't exist OR user confirmed overwrite:
            TRY:
                OPEN file for writing
                WRITE student data to file
                SET fileSaved to true
                SET saveMessage to success message
            CATCH any errors: SET saveMessage to error message
        
        // Continue adding?
        Ask user if they want to add another student (Y/N)
        IF "N": SET addingStudent to false
        CALL FormState.reset()*/

    private static void viewAllStudents() {
        clearScreen();
        System.out.println(banner + "\n\n\u001B[1mAll Students:\u001B[0m\n");
        File[] files = folder.listFiles((d,n) -> n.endsWith(".txt"));
        if (files == null || files.length == 0) System.out.println("\u001b[43m\u001b[30m\u001B[30mNo students found.\u001B[0m");
        else for (File file : files) System.out.println("- " + file.getName().replace(".txt", ""));
        waitForEnter();
    }
    
    /*
    METHOD viewAllStudents():
    CLEAR_SCREEN()
    PRINT banner and "All Students" header
    
    files = LIST all .txt files in users folder
    IF no files: PRINT "No students found"
    ELSE: FOR each file: PRINT filename without .txt extension
    
    CALL waitForEnter()  // Press Enter to continue*/

    private static void deleteStudent() {
        boolean deleting = true;
        String deleteError = "";
        while (deleting) {
            clearScreen();
            System.out.println(banner + "\n\n\u001B[1mDelete Student:\u001B[0m\n");
            File[] delFiles = folder.listFiles((d,n) -> n.endsWith(".txt"));
            if (delFiles == null || delFiles.length == 0) {
                System.out.println("\u001b[41m\u001b[30m\u001B[37mNo students found to delete.\u001B[0m");
                waitForEnter(); deleting = false; continue;
            }

            System.out.println("\u001B[1mAvailable Students:\u001B[0m");
            for (int i = 0; i < delFiles.length; i++) System.out.println((i + 1) + ". " + delFiles[i].getName().replace(".txt", ""));
            System.out.println();
            if (!deleteError.isEmpty()) { System.out.println("\u001b[41m\u001b[30m\u001B[37m" + deleteError + " \u001B[0m"); deleteError = ""; }
            System.out.print("\u001b[47m\u001b[30m Enter student number [X: Back] | ➤ \u001B[0m ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("X")) { deleting = false; continue; }
            if (input.isEmpty()) { deleteError = "[!] Please enter a student number [1-" + delFiles.length + "] or [X: back to menu]"; continue; }

            try {
                int delChoice = Integer.parseInt(input);
                if (delChoice < 1 || delChoice > delFiles.length) { deleteError = "[!] Invalid student number! Please enter [1-" + delFiles.length + ": student options]"; continue; }
                String studentName = delFiles[delChoice-1].getName().replace(".txt", "");
                String confirmError = "";
                while (true) {
                    clearScreen();
                    System.out.println(banner + "\n\n\u001B[1mDelete Student:\u001B[0m\nSelected: " + studentName + "\n");
                    if (!confirmError.isEmpty()) { System.out.println("\u001b[41m\u001b[30m\u001B[37m" + confirmError + " \u001B[0m"); confirmError = ""; }
                    System.out.print("\u001b[41m\u001b[30m\u001B[37m[!] Delete " + studentName + "? [Y: yes, delete permanently | N: no, keep student] | ➤ \u001B[0m ");
                    String confirm = sc.nextLine().trim().toUpperCase();
                    if (confirm.equals("Y")) {
                        System.out.println(delFiles[delChoice-1].delete() ? "\u001b[47m\u001b[30m\u001B[30mDeleted " + studentName + "\u001B[0m" : "\u001b[41m\u001b[30m\u001B[37m[!] Could not delete file - check permissions\u001B[0m");
                        break;
                    } else if (confirm.equals("N")) break;
                    else { confirmError = "[!] Invalid choice! Please enter [Y: yes | N: no] only"; continue; }
                }

                String continueError = "";
                while (true) {
                    clearScreen();
                    System.out.println(banner + "\n\n\u001b[47m\u001b[30m\u001B[30mDeleted " + studentName + "\u001B[0m\n");
                    if (!continueError.isEmpty()) { System.out.println("\u001b[41m\u001b[30m\u001B[37m" + continueError + " \u001B[0m"); continueError = ""; }
                    System.out.print("\u001b[41m\u001b[30m\u001B[37mDelete another student? [Y: yes, delete more | N: no, return to menu] | ➤ \u001B[0m ");
                    String continueDelete = sc.nextLine().trim().toUpperCase();
                    if (continueDelete.equals("Y")) break;
                    else if (continueDelete.equals("N")) { deleting = false; break; }
                    else { continueError = "[!] Invalid choice! Please enter [Y: yes | N: no] only"; continue; }
                }
            } catch (NumberFormatException e) { deleteError = "[!] Please enter a valid number [1-" + delFiles.length + "] or [X: back to menu]"; continue; }
        }
    }
    
    /*
    METHOD deleteStudent():
    deleting = true
    
    WHILE deleting:
        CLEAR_SCREEN()
        PRINT banner and "Delete Student" header
        
        files = LIST all .txt files in users folder
        IF no files: PRINT error, CALL waitForEnter(), SET deleting to false, CONTINUE
        
        PRINT list of all students with numbers
        PRINT prompt to select student number or X to go back
        READ input
        
        IF input is "X": SET deleting to false, CONTINUE
        
        TRY:
            CONVERT input to number
            IF number is invalid: PRINT error, CONTINUE
            
            studentName = GET filename from files array
            Ask for confirmation to delete (Y/N)
            
            IF confirmed "Y":
                DELETE file
                PRINT success or error message
            
            Ask if user wants to delete another student (Y/N)
            IF "N": SET deleting to false
            
        CATCH number format exception: PRINT error message*/

    private static void viewStudentData() {
        boolean viewing = true;
        String viewError = "";
        while (viewing) {
            clearScreen();
            System.out.println(banner + "\n\n\u001B[1mView Student Data:\u001B[0m\n");
            File[] viewFiles = folder.listFiles((d,n) -> n.endsWith(".txt"));
            if (viewFiles == null || viewFiles.length == 0) {
                System.out.println("\u001b[43m\u001b[30m\u001B[30mNo students found.\u001B[0m");
                waitForEnter(); viewing = false; continue;
            }

            System.out.println("\u001B[1mAvailable Students:\u001B[0m");
            for (int i = 0; i < viewFiles.length; i++) System.out.println((i + 1) + ". " + viewFiles[i].getName().replace(".txt", ""));
            System.out.println();
            if (!viewError.isEmpty()) { System.out.println("\u001b[41m\u001b[30m\u001B[37m" + viewError + " \u001B[0m"); viewError = ""; }
            System.out.print("\u001b[47m\u001b[30m Enter student number [X: Back] | ➤ \u001B[0m ");
            String viewInput = sc.nextLine().trim();

            if (viewInput.equalsIgnoreCase("X")) { viewing = false; continue; }
            if (viewInput.isEmpty()) { viewError = "[!] Please enter a student number [1-" + viewFiles.length + "] or [X: back to menu]"; continue; }

            try {
                int viewChoice = Integer.parseInt(viewInput);
                if (viewChoice < 1 || viewChoice > viewFiles.length) { viewError = "[!] Invalid student number! Please enter [1-" + viewFiles.length + ": student options]"; continue; }
                String continueViewError = "";
                while (true) {
                    clearScreen();
                    System.out.println(banner + "\n\n\u001B[1mStudent Data:\u001B[0m\n");
                    try (Scanner fr = new Scanner(viewFiles[viewChoice-1])) { while (fr.hasNextLine()) System.out.println(fr.nextLine()); }
                    catch (Exception e) { System.out.println("\u001b[41m\u001b[30m\u001B[37mError reading file.\u001B[0m"); }
                    System.out.println();
                    if (!continueViewError.isEmpty()) { System.out.println("\u001b[41m\u001b[30m\u001B[37m" + continueViewError + " \u001B[0m"); continueViewError = ""; }
                    System.out.print("\u001b[41m\u001b[30m\u001B[37mView another student? [Y: yes, view more | N: no, return to menu] | ➤ \u001B[0m ");
                    String nextAction = sc.nextLine().trim().toUpperCase();
                    if (nextAction.equals("Y")) break;
                    else if (nextAction.equals("N")) { viewing = false; break; }
                    else { continueViewError = "[!] Invalid choice! Please enter [Y: yes | N: no] only"; continue; }
                }
            } catch (NumberFormatException e) { viewError = "[!] Please enter a valid number [1-" + viewFiles.length + "] or [X: back to menu]"; continue; }
        }
    }
    /*
    METHOD viewStudentData():
    viewing = true
    
    WHILE viewing:
        CLEAR_SCREEN()
        PRINT banner and "View Student Data" header
        
        files = LIST all .txt files in users folder
        IF no files: PRINT error, CALL waitForEnter(), SET viewing to false, CONTINUE
        
        PRINT list of all students with numbers
        PRINT prompt to select student number or X to go back
        READ input
        
        IF input is "X": SET viewing to false, CONTINUE
        
        TRY:
            CONVERT input to number
            IF number is invalid: PRINT error, CONTINUE
            
            OPEN selected file
            PRINT all contents of the file
            
            Ask if user wants to view another student (Y/N)
            IF "N": SET viewing to false
            
        CATCH number format exception: PRINT error message*/

    private static void exitProgram() {
        clearScreen();
        System.out.println(banner + "\n\n\u001b[47m\u001b[30m\u001B[30mExiting the program. Goodbye!\u001B[0m");
        System.exit(0);
    }

    private static void clearScreen() {
        try { new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); }
        catch (Exception e) { System.out.print("\033[H\033[2J"); System.out.flush(); }
    }

    private static void waitForEnter() {
        System.out.print("\n\u001b[47m\u001B[1mPress Enter to continue...\u001B[0m ");
        sc.nextLine();
        /*
METHOD exitProgram():
    CLEAR_SCREEN()
    PRINT banner and goodbye message
    TERMINATE program

METHOD clearScreen():
    TRY: Execute system command to clear screen (cls for Windows)
    CATCH: Use ANSI escape sequences to clear screen

METHOD waitForEnter():
    PRINT "Press Enter to continue..."
    READ input (wait for Enter key)*/
    }
}

/*JAVA STUDENT MANAGEMENT SYSTEM - CODE FLOW EXPLANATION
======================================================

PROGRAM START
-------------
1. Program begins execution in the main() method
2. Checks if "users" folder exists, creates it if not
3. Enters infinite loop to keep program running until user chooses to exit

MAIN LOOP FLOW
--------------
while (true) {
    clearScreen();
    showBannerAndMenu();
    if (errorExists) showError();
    askForMenuChoice();
    
    switch (userChoice) {
        case "1": addStudent(); break;
        case "2": viewAllStudents(); break;
        case "3": deleteStudent(); break;
        case "4": viewStudentData(); break;
        case "5": showMembers(); break;
        case "6": exitProgram(); break;
        default: setError("Invalid choice");
    }
}

ADD STUDENT FLOW (Case 1)
-------------------------
1. Reset form state (clear previous entries)
2. Enter form-filling loop
3. For each field (Name, LRN, Strand):
   - Show prompt
   - Read input
   - Validate input (check empty, check format)
   - If invalid: set error, restart loop
   - If valid: store value, move to next field
4. Show confirmation screen with all entered data
5. Ask user to confirm (Y/N/X)
   - Y: proceed to save
   - N: clear form, restart
   - X: cancel, return to main menu
6. If file exists, ask for overwrite confirmation
7. Save data to file (or show error if failed)
8. Ask if user wants to add another student
9. If yes: restart form; if no: return to main menu

VIEW ALL STUDENTS FLOW (Case 2)
-------------------------------
1. Clear screen
2. List all .txt files in "users" folder
3. Display each filename (without .txt extension)
4. Wait for user to press Enter
5. Return to main menu

DELETE STUDENT FLOW (Case 3)
----------------------------
1. Enter deletion loop
2. List all students with numbers
3. Ask user to select student number or 'X' to cancel
4. If valid number selected:
   - Ask for confirmation to delete (Y/N)
   - If Y: delete file, show success/error message
   - Ask to delete another student or return to menu
5. If invalid input: show error, restart loop
6. If 'X': return to main menu

VIEW STUDENT DATA FLOW (Case 4)
-------------------------------
1. Enter viewing loop
2. List all students with numbers
3. Ask user to select student number or 'X' to cancel
4. If valid number selected:
   - Read and display file contents
   - Ask to view another student or return to menu
5. If invalid input: show error, restart loop
6. If 'X': return to main menu

SHOW MEMBERS FLOW (Case 5)
--------------------------
1. Clear screen
2. Display banner and group members list
3. Wait for user to press Enter
4. Return to main menu

EXIT PROGRAM FLOW (Case 6)
--------------------------
1. Clear screen
2. Display goodbye message
3. Terminate program (System.exit(0))

ERROR HANDLING FLOW
-------------------
- Most methods have try-catch blocks for file operations
- Input validation at each user input point
- Error messages displayed with red background
- Program never crashes - errors are caught and handled gracefully
- User always returned to a stable state (menu or previous screen)

DATA FLOW
---------
Main Menu → Function Choice → Function Execution → 
   → Success: Show result → Return to Menu
   → Error: Show error → Return to previous screen
   
USER INTERACTION PATTERN
------------------------
1. Clear screen
2. Show header/information
3. Show prompt
4. Get input
5. Validate input
6. Process valid input OR show error and repeat

FILE OPERATION FLOW
-------------------
Save: Form data → FileWriter → Text file in "users" folder
Read: File name → Scanner → Display contents
Delete: File selection → file.delete() → Remove from disk

PROGRAM TERMINATION
-------------------
Only occurs when:
1. User selects option 6 (Exit)
2. System.exit(0) is called
3. Program closes cleanly with exit code 0 (success)*/


/* FLOW CHART :]
START
  │
  ├─> Check if "users" folder exists? ──No──> Create folder
  │         │
  │         Yes
  │         │
  ▼         ▼
MAIN LOOP: Show Menu & Get Input
  │
  ├─1─> ADD STUDENT
  │     │
  │     ├─> Reset form
  │     │
  │     ├─> For each field (Name, LRN, Strand):
  │     │     │
  │     │     ├─> Show prompt
  │     │     ├─> Get input
  │     │     ├─> Validate input
  │     │     │    ├─ Invalid → Show error → Repeat field
  │     │     │    └─ Valid → Store value → Next field
  │     │
  │     ├─> Show confirmation
  │     │     │
  │     │     ├─ 'Y' → Save to file → Ask "Add another?"
  │     │     │         ├─ Yes → Repeat ADD STUDENT
  │     │     │         └─ No → Return to MAIN LOOP
  │     │     │
  │     │     ├─ 'N' → Clear form → Repeat ADD STUDENT
  │     │     │
  │     │     └─ 'X' → Return to MAIN LOOP
  │
  ├─2─> VIEW ALL
  │     │
  │     ├─> List all .txt files in "users" folder
  │     │
  │     ├─> Wait for Enter key
  │     │
  │     └─> Return to MAIN LOOP
  │
  ├─3─> DELETE
  │     │
  │     ├─> List all students
  │     │
  │     ├─> Get selection (number or 'X')
  │     │     │
  │     │     ├─ 'X' → Return to MAIN LOOP
  │     │     │
  │     │     └─ Number → Confirm deletion
  │     │           │
  │     │           ├─ 'Y' → Delete file → Ask "Delete another?"
  │     │           │         ├─ Yes → Repeat DELETE
  │     │           │         └─ No → Return to MAIN LOOP
  │     │           │
  │     │           └─ 'N' → Ask "Delete another?"
  │
  ├─4─> VIEW ONE
  │     │
  │     ├─> List all students
  │     │
  │     ├─> Get selection (number or 'X')
  │     │     │
  │     │     ├─ 'X' → Return to MAIN LOOP
  │     │     │
  │     │     └─ Number → Show file contents → Ask "View another?"
  │     │           │
  │     │           ├─ Yes → Repeat VIEW ONE
  │     │           │
  │     │           └─ No → Return to MAIN LOOP
  │
  ├─5─> MEMBERS
  │     │
  │     ├─> Show group members list
  │     │
  │     ├─> Wait for Enter key
  │     │
  │     └─> Return to MAIN LOOP
  │
  └─6─> EXIT
        │
        ├─> Show goodbye message
        │
        └─> Terminate program
*/
