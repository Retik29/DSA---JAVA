
/**
 * Fractional Knapsack Problem (Greedy Algorithm)
 * 
 * Problem: Given weights and values of 'n' items, we need to put these items 
 * in a knapsack of capacity 'W' to get the maximum total value in the knapsack.
 * Unlike 0/1 Knapsack, here we CAN break items for a fraction of the weight 
 * and value.
 * 
 * Logic (Greedy Strategy):
 * 1. Ratio Calculation: For each item, calculate Value/Weight ratio (density).
 * 2. Sorting: Sort all items in descending order of their Value/Weight ratio.
 * 3. Pick Items:
 *    - If the current item's weight is less than or equal to the remaining capacity, 
 *      take the whole item.
 *    - Else, take only a fraction of the item to fill the remaining capacity.
 * 
 * Why Value/Weight Ratio? 
 * This ratio represents the "value density". By picking items with the highest 
 * density first, we ensure we are getting the maximum possible value for 
 * every unit of capacity used.
 * 
 * Complexity:
 * - Time Complexity : O(n log n) - primarily due to sorting items by ratio.
 * - Space Complexity: O(n) - to store the item objects.
 */
import java.util.*;

public class FractionalKnapsack {

    /**
     * Internal class to represent an item and its value-to-weight density.
     */
    static class Item {
        int value, weight;
        double ratio;

        Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
            this.ratio = (double) value / weight; // Value per unit weight
        }
    }

    /**
     * Calculates the maximum value that can be fit into a knapsack of given
     * capacity.
     */
    static double getMaxValue(int[] values, int[] weights, int capacity) {
        int n = values.length;
        Item[] items = new Item[n];

        for (int i = 0; i < n; i++) {
            items[i] = new Item(values[i], weights[i]);
        }

        // Sort items by value/weight ratio in descending order
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        double totalValue = 0.0;
        int remainingCapacity = capacity;

        for (Item item : items) {
            if (remainingCapacity <= 0)
                break;

            if (item.weight <= remainingCapacity) {
                // If the whole item can fit, take it
                remainingCapacity -= item.weight;
                totalValue += item.value;
            } else {
                // If only a fraction fits, take that fraction
                totalValue += (item.ratio * remainingCapacity);
                remainingCapacity = 0; // Knapsack is now full
            }
        }
        return totalValue;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Fractional Knapsack Solver ---");
        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int[] val = new int[n];
        int[] wt = new int[n];

        System.out.print("Enter values of " + n + " items: ");
        for (int i = 0; i < n; i++)
            val[i] = sc.nextInt();

        System.out.print("Enter weights of " + n + " items: ");
        for (int i = 0; i < n; i++)
            wt[i] = sc.nextInt();

        System.out.print("Enter total knapsack capacity: ");
        int capacity = sc.nextInt();

        // Execution
        double maxVal = getMaxValue(val, wt, capacity);

        System.out.println("\n--- Result Summary ---");
        System.out.printf("Maximum value achieved in knapsack: %.2f\n", maxVal);

        sc.close();
    }
}
