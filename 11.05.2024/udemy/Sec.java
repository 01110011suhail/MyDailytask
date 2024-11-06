import java.util.Arrays;
import java.util.Random;

class Sec {
    public static void main(String[] args) {
        int[] firstarr = getRandomArr(10);
        System.out.println(Arrays.toString(firstarr));

        Arrays.sort(firstarr);
        System.out.println(Arrays.toString(firstarr));
        int[] secondarr = new int[10];
        System.out.println(Arrays.toString(secondarr));
        Arrays.fill(secondarr, 5);
        System.out.println(Arrays.toString(secondarr));

        int[] thirdarr = Arrays.copyOf(secondarr, 15);
        System.out.println(Arrays.toString(thirdarr));

    }

    private static int[] getRandomArr(int len) {
        Random ran = new Random();
        int[] arr = new int[len];

        for (int i = 0; i < len; i++) {
            arr[i] = ran.nextInt(100);
        }
        return arr; //
    }
}