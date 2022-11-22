public class Student implements Comparable<Student> {

    private int recordBookNumber;
    private String surname;
    private int course;
    private int group;

    public Student(int recordBookNumber, String surname, int course, int group) {
        this.recordBookNumber = recordBookNumber;
        this.surname = surname;
        this.course = course;
        this.group = group;
    }


    @Override
    public int compareTo(Student o) {
        return this.recordBookNumber - o.recordBookNumber;
    }

    @Override
    public String toString() {
        return surname + "( record book number: " + recordBookNumber + ", group " + group + ", course " + course + ")" + '\n';
    }
}
