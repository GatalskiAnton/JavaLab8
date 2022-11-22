import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MyReader {
    public static ArrayList<Student> readStudentFromFile(File file) {
        ArrayList<Student> students = new ArrayList<>();
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                int recordBookNumber = sc.nextInt();
                String surname = sc.next();
                int course = sc.nextInt();
                int group = sc.nextInt();
                students.add(new Student(recordBookNumber, surname, course, group));
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return students;
    }
}
