import java.util.Arrays;

class First {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        System.out.println(arr[0]);
        int len = arr.length;
        System.out.println(" " + len);

        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + " ");
        }

        for (int op : arr) {
            System.out.println(op);
        }

    }
}