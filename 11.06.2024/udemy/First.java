import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class First {
    public static void main(String[] args) {
        String[] Gitems = new String[] { "First", "Second", "Third" };
        var oglist = Arrays.asList(Gitems);

        oglist.set(0, "one");
        System.out.println(Arrays.toString(Gitems));
        System.out.println(oglist);

        oglist.sort(Comparator.naturalOrder());
        System.out.println(oglist);

    }

}
