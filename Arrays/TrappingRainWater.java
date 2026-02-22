
// Trapping Rain Water — two pointer approach
// Time: O(n) | Space: O(1)
import java.util.*;

public class TrappingRainWater {

    static int trap(int[] height) {
        int lo = 0, hi = height.length - 1;
        int leftMax = 0, rightMax = 0, water = 0;
        while (lo < hi) {
            if (height[lo] < height[hi]) {
                leftMax = Math.max(leftMax, height[lo]);
                water += leftMax - height[lo++];
            } else {
                rightMax = Math.max(rightMax, height[hi]);
                water += rightMax - height[hi--];
            }
        }
        return water;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();
        int[] height = new int[n];
        System.out.print("Enter " + n + " heights: ");
        for (int i = 0; i < n; i++)
            height[i] = sc.nextInt();

        System.out.println("Heights: " + Arrays.toString(height));
        System.out.println("Trapped water: " + trap(height));

        sc.close();
    }
}
