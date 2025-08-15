import java.io.*;
import java.util.*;

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
        "\u001b[47m\u001b[30m 2. \u001B[0m Johncint F. Bagonoc - \u001B[31mAssistant Developer\u001B[0m\n" +
        "\u001b[47m\u001b[30m 3. \u001B[0m Mico Clemente - \u001B[31mTester, Bug Catcher\u001B[0m\n" +
        "\u001b[47m\u001b[30m 4. \u001B[0m Harold Bazar - \u001B[31mTester\u001B[0m\n" +
        "\u001b[47m\u001b[30m 5. \u001B[0m Name - \u001B[31mMember\u001B[0m\n" +
        "\u001b[47m\u001b[30m 6. \u001B[0m Name - \u001B[31mMember\u001B[0m\n" +
        "\u001b[47m\u001b[30m 7. \u001B[0m Name - \u001B[31mMember\u001B[0m\n" +
        "\u001b[47m\u001b[30m 8. \u001B[0m Name - \u001B[31mMember\u001B[0m\n" +
        "\u001b[47m\u001b[30m 9. \u001B[0m Name - \u001B[31mMember\u001B[0m\n";
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
                default: menuError = "[!] Invalid choice! Please enter [1-5: menu options]"; break;
            }
        }
    }

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

    private static void viewAllStudents() {
        clearScreen();
        System.out.println(banner + "\n\n\u001B[1mAll Students:\u001B[0m\n");
        File[] files = folder.listFiles((d,n) -> n.endsWith(".txt"));
        if (files == null || files.length == 0) System.out.println("\u001b[43m\u001b[30m\u001B[30mNo students found.\u001B[0m");
        else for (File file : files) System.out.println("- " + file.getName().replace(".txt", ""));
        waitForEnter();
    }

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
    }
}
