
// Equilibrium Point — index where left sum equals right sum
// Time: O(n) | Space: O(1)
import java.util.*;

public class equilibriumPt {

    static int findEquilibrium(int[] arr) {
        int totalSum = 0, leftSum = 0;
        for (int num : arr)
            totalSum += num;
        for (int i = 0; i < arr.length; i++) {
            totalSum -= arr[i]; // rightSum
            if (leftSum == totalSum)
                return i;
            leftSum += arr[i];
        }
        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        int idx = findEquilibrium(arr);
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println(
                idx != -1 ? "Equilibrium at index: " + idx + " (value " + arr[idx] + ")" : "No equilibrium point");

        sc.close();
    }
}
