import java.util.ArrayList;

public class MyCollection<T> {

    public static <T> ArrayList<T> sortByRecordBookNumber(ArrayList<T> students) {
        return new ArrayList<>(students.stream().sorted().toList());
    }
}
