
// Fractional Knapsack - Greedy Algorithm
// Time Complexity: O(n log n) | Space Complexity: O(n)
import java.util.*;

public class FractionalKnapsack {
    static class Item {
        int value, weight;
        double ratio;

        Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
            this.ratio = (double) value / weight;
        }
    }

    static double getMaxValue(int[] val, int[] wt, int capacity) {
        int n = val.length;
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++)
            items[i] = new Item(val[i], wt[i]);

        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        double totalValue = 0.0;
        for (Item item : items) {
            if (capacity >= item.weight) {
                capacity -= item.weight;
                totalValue += item.value;
            } else {
                totalValue += item.ratio * capacity;
                break;
            }
        }
        return totalValue;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of items: ");
        int n = sc.nextInt();
        int[] val = new int[n];
        int[] wt = new int[n];

        System.out.print("Enter values: ");
        for (int i = 0; i < n; i++)
            val[i] = sc.nextInt();
        System.out.print("Enter weights: ");
        for (int i = 0; i < n; i++)
            wt[i] = sc.nextInt();

        System.out.print("Enter knapsack capacity: ");
        int capacity = sc.nextInt();

        System.out.println("Maximum value in knapsack: " + getMaxValue(val, wt, capacity));
        sc.close();
    }
}
