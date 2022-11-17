import by.gsu.pms.Student;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.*;

public class Runner {

    public static void main(String[] args) {
        try {
            File input = new File("MOCK_DATA.csv");
            Scanner sc = new Scanner(input);
            ArrayList<Student> students = new ArrayList<>();

            System.out.print("Data reading...\t");
            while (sc.hasNextLine()) {
                String str = sc.nextLine();
                String[] studentArgs = str.split(",");
                students.add(new Student(
                        studentArgs[0],studentArgs[1],studentArgs[2], LocalDate.parse(studentArgs[3]),
                        studentArgs[4],studentArgs[5],studentArgs[6],studentArgs[7],studentArgs[8]));
            }
            sc.close();
            System.out.println("Done");

//            System.out.println("\nBefore sorting:");
//            for (Student student: students) {
//                System.out.println(student);
//            }

            System.out.print("Sorting...\t");
            Collections.sort(students);
            System.out.println("Done");

//            System.out.println("\nAfter sorting:");
//            for (Student student: students) {
//                System.out.println(student);
//            }

            System.out.print("Data writing...\t");
            FileWriter fw = new FileWriter("SORTED_DATA.txt");
            for (Student student: students) {
                fw.write(student.toString());
                fw.write('\n');
            }
            fw.close();
            System.out.println("Done");

            System.out.println("\nStudents of fac1:");
            String fac = "fac1";
            for (Student student : students) {
                if (Objects.equals(student.getFaculty(), fac)) System.out.println(student);
            }

            System.out.println("\nStudents List:");
            Dictionary<String, Dictionary<String, ArrayList<Student>>> dict = new Hashtable<>();
            for (Student student : students) {
//                Add faculties to the dictionary
                boolean facIsAdded = false;
                for (Enumeration<String> faculties = dict.keys(); faculties.hasMoreElements();) {
                    if (faculties.nextElement().equals(student.getFaculty())) {
                        facIsAdded = true;
                        break;
                    }
                }
                if (!facIsAdded) dict.put(student.getFaculty(), new Hashtable<>());

//                Add curses to the dictionary
                boolean cursIsAdded = false;
                for (Enumeration<String> curses = dict.get(student.getFaculty()).keys(); curses.hasMoreElements();) {
                    if (curses.nextElement().equals(student.getYear())) {
                        cursIsAdded = true;
                        break;
                    }
                }
                if (!cursIsAdded) dict.get(student.getFaculty()).put(student.getYear(), new ArrayList<>());

//                Add students to the dictionary
                dict.get(student.getFaculty()).get(student.getYear()).add(student);
            }

            for (Enumeration<String> faculties = dict.keys();faculties.hasMoreElements();) {
                String faculty = faculties.nextElement();
                System.out.printf("Faculty: %s%n", faculty);
                for (Enumeration<String> years = dict.get(faculty).keys(); years.hasMoreElements();) {
                    String year = years.nextElement();
                    System.out.printf("\tYear: %s%n", year);
                    int studentNumber = 0;
                    for (Student student : dict.get(faculty).get(year)) {
                        System.out.printf("\t\t[%d] %s%n", ++studentNumber, student);
                    }
                }
            }

            System.out.println("\nStudents who had been born after 2020-10-10:");
            for (Student student : students) {
                if (student.getBirthday().isAfter(LocalDate.parse("2020-10-10"))) {
                    System.out.println(student);
                }
            }

            System.out.println("\nStudents of gr1:");
            for (Student student : students) {
                if (student.getGroup().equals("gr1")) {
                    System.out.println(student);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
