import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Third {
    public static void main(String[] args) {
        String[] ListofItem = { "apples", "banana", "mango", "peach" };

        List<String> list = List.of(ListofItem);
        System.out.println(list);
        ArrayList<String> list2 = new ArrayList<>(list);
        list2.add("yogurt");
        System.out.println(list2);

        list2.clear();
        System.out.println(list2);

        list2.addAll(List.of("apple", "mango", "orange", "pineapple"));

        list2.addAll(Arrays.asList("peach", "leach"));
        System.out.println(list2);
        list2.sort(Comparator.naturalOrder());
        System.out.println(list2);

        list2.sort(Comparator.reverseOrder());
        System.out.println(list2);

    }

}
