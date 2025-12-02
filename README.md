📘 Student Management System – Assignment 4

A Java console-based program for managing student records using File Handling, Collections, Searching, Sorting, and Iterators. This project implements all required operations in a single Java file for simplicity.

🚀 Features

✔ Add Student
Prompts the user to enter:

* Roll Number
* Name
* Email
* Course
* Marks

✔ View All Students
* Displays all student records using an Iterator.

✔ Search by Name
* Case-insensitive search using partial match.

✔ Delete by Name
* Deletes a student (first matched record) using an Iterator safely.

✔ Sort by Marks
* Sorts student list using a Comparator (high-to-low order).

💾 File Handling (Required in Assignment)
The system uses:

1. BufferedReader / BufferedWriter
* Reads existing student records from students.txt
* Saves updated records on exit

3. File Class
Shows:

* Absolute path
* File length in bytes

3. RandomAccessFile
* Reads one random record from the file to demonstrate pointer-based access (as required).

🧰 Collections & OOP Features

* ArrayList for storage
* Iterator for traversal and deletion
* Comparable (sort by name)
* Comparator (sort by marks)
* Encapsulation (Student class)
  
📂 File Structure
* All logic is contained in one file:

StudentManagement.java

The file includes:

* Student class
* All comparators & Comparable
* File utilities (read/write/random access)
* Menu system
* Main driver code
  
▶️ How to Run
1. Save the file as:
2. StudentManagement.java

3. Compile:
   
javac StudentManagement.java

4.Run:
* java StudentManagement
* A file named students.txt will be created automatically.
  
📝 Sample Menu
===== Student Menu =====
1. Add Student
2. View All
3. Search by Name
4. Delete by Name
5. Sort by Marks
6. Save & Exit
Enter choice:

🎯 Learning Outcomes
*After completing this assignment, you will understand:

File handling in Java
BufferedReader, BufferedWriter, RandomAccessFile
Collections API (ArrayList, Iterator)
Searching and sorting with Comparator & Comparable
How to work with text files for record storage
How to design a menu-driven application
