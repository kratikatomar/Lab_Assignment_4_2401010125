package lassignment4;

import java.io.*;
import java.util.*;

public class StudentManagement {

    // ---------------- Student Class ----------------
    static class Student implements Comparable<Student> {
        private int rollNo;
        private String name;
        private String email;
        private String course;
        private double marks;

        public Student(int rollNo, String name, String email, String course, double marks) {
            this.rollNo = rollNo;
            this.name = name;
            this.email = email;
            this.course = course;
            this.marks = marks;
        }

        public int getRollNo() { return rollNo; }
        public String getName() { return name; }
        public double getMarks() { return marks; }

        public void display() {
            System.out.println("Roll No : " + rollNo);
            System.out.println("Name    : " + name);
            System.out.println("Email   : " + email);
            System.out.println("Course  : " + course);
            System.out.println("Marks   : " + marks);
            System.out.println("-----------------------------------");
        }

        @Override
        public int compareTo(Student s) {
            return this.name.compareToIgnoreCase(s.name);
        }

        // Comparator for sorting by Marks DESC
        public static Comparator<Student> BY_MARKS =
                (s1, s2) -> Double.compare(s2.getMarks(), s1.getMarks());
    }

    // File name
    private static final String FILE_NAME = "students.txt";

    // Data store
    static List<Student> students = new ArrayList<>();

    // ---------------- Load from File ----------------
    public static void loadFromFile() {
        File file = new File(FILE_NAME);

        try {
            if (!file.exists()) file.createNewFile();

            System.out.println("File Path   : " + file.getAbsolutePath());
            System.out.println("File Length : " + file.length() + " bytes");
            System.out.println("-----------------------------------");

            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
            String line;

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] p = line.split("\\|");
                if (p.length == 5) {
                    int roll = Integer.parseInt(p[0]);
                    String name = p[1];
                    String email = p[2];
                    String course = p[3];
                    double marks = Double.parseDouble(p[4]);

                    students.add(new Student(roll, name, email, course, marks));
                }
            }
            br.close();

            if (!students.isEmpty()) {
                System.out.println("Students Loaded:");
                Iterator<Student> it = students.iterator();
                while (it.hasNext()) it.next().display();
            } else {
                System.out.println("No existing students in file.");
            }

            // RandomAccessFile demo
            readRandomRecord();

        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }

    // ---------------- Save to File ----------------
    public static void saveToFile() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME));
            for (Student s : students) {
                bw.write(s.getRollNo() + "|" + s.getName() + "|" +
                        s.email + "|" + s.course + "|" + s.marks);
                bw.newLine();
            }
            bw.close();

            File file = new File(FILE_NAME);
            System.out.println("Saved Successfully!");
            System.out.println("Updated File Length: " + file.length() + " bytes");
            System.out.println("-----------------------------------");

        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    // ---------------- Random Access File ----------------
    public static void readRandomRecord() {
        try {
            RandomAccessFile raf = new RandomAccessFile(FILE_NAME, "r");

            List<Long> positions = new ArrayList<>();
            String line;
            while ((line = raf.readLine()) != null) {
                positions.add(raf.getFilePointer());
            }

            if (positions.isEmpty()) return;

            Random r = new Random();
            long pos = positions.get(r.nextInt(positions.size()));
            raf.seek(pos);

            System.out.println("Random Record (RAF):");
            String rec = raf.readLine();
            if (rec != null) System.out.println(rec);
            System.out.println("-----------------------------------");

            raf.close();

        } catch (Exception e) {
            System.out.println("RAF Error: " + e.getMessage());
        }
    }

    // ---------------- Add Student ----------------
    public static void addStudent(Scanner sc) {
        try {
            System.out.print("Enter Roll No: ");
            int roll = Integer.parseInt(sc.nextLine().trim());

            System.out.print("Enter Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Email: ");
            String email = sc.nextLine();

            System.out.print("Enter Course: ");
            String course = sc.nextLine();

            System.out.print("Enter Marks: ");
            double marks = Double.parseDouble(sc.nextLine().trim());

            students.add(new Student(roll, name, email, course, marks));
            System.out.println("Student Added Successfully!");

        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Try again.");
        }
    }

    // ---------------- View All Students ----------------
    public static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No Students Found.");
            return;
        }
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) it.next().display();
    }

    // ---------------- Search by Name ----------------
    public static void searchByName(Scanner sc) {
        System.out.print("Enter Name: ");
        String key = sc.nextLine().toLowerCase();

        boolean found = false;
        for (Student s : students) {
            if (s.getName().toLowerCase().contains(key)) {
                s.display();
                found = true;
            }
        }

        if (!found) System.out.println("No student found.");
    }

    // ---------------- Delete by Name ----------------
    public static void deleteByName(Scanner sc) {
        System.out.print("Enter Name: ");
        String key = sc.nextLine().toLowerCase();

        Iterator<Student> it = students.iterator();
        boolean deleted = false;

        while (it.hasNext()) {
            Student s = it.next();
            if (s.getName().toLowerCase().equals(key)) {
                it.remove();
                System.out.println("Student Deleted: " + s.getName());
                deleted = true;
                break;
            }
        }

        if (!deleted) System.out.println("No student found with that name.");
    }

    // ---------------- Sort by Marks ----------------
    public static void sortByMarks() {
        if (students.isEmpty()) {
            System.out.println("No students to sort.");
            return;
        }
        Collections.sort(students, Student.BY_MARKS);
        System.out.println("Sorted By Marks (DESC):");
        viewStudents();
    }

    // ---------------- MAIN MENU ----------------
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        loadFromFile();

        while (true) {
            System.out.println("\n===== Student Menu =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All");
            System.out.println("3. Search by Name");
            System.out.println("4. Delete by Name");
            System.out.println("5. Sort by Marks");
            System.out.println("6. Save & Exit");
            System.out.print("Enter choice: ");

            int ch;
            try {
                ch = Integer.parseInt(sc.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid Choice!");
                continue;
            }

            switch (ch) {
                case 1 -> addStudent(sc);
                case 2 -> viewStudents();
                case 3 -> searchByName(sc);
                case 4 -> deleteByName(sc);
                case 5 -> sortByMarks();
                case 6 -> {
                    saveToFile();
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid Option!");
            }
        }
    }
}