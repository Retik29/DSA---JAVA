
// Leaders in Array — element greater than all to its right
// Time: O(n) | Space: O(1) excluding output
import java.util.*;

public class LeadersInArray {

    // Traverse right to left, track max from right
    static List<Integer> findLeaders(int[] arr) {
        List<Integer> leaders = new ArrayList<>();
        int maxRight = arr[arr.length - 1];
        leaders.add(maxRight);
        for (int i = arr.length - 2; i >= 0; i--) {
            if (arr[i] > maxRight) {
                maxRight = arr[i];
                leaders.add(arr[i]);
            }
        }
        Collections.reverse(leaders);
        return leaders;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        System.out.print("Enter " + n + " elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Leaders: " + findLeaders(arr));

        sc.close();
    }
}
