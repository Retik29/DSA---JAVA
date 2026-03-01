
/**
 * Stock Buy and Sell (Greedy Strategy)
 * 
 * Problem: Given an array of stock prices for 'n' days, find the maximum profit 
 * that can be achieved.
 * 
 * Logic (Two Common Variations):
 * 1. Single Transaction (Only one buy and one sell):
 *    - Strategy: Keep track of the minimum price seen so far ('minPrice').
 *    - For each day, calculate potential profit: (currentPrice - minPrice).
 *    - Maximize this profit across all days.
 * 
 * 2. Multiple Transactions (Unlimited buy/sell, but must sell before buying again):
 *    - Strategy (Local Peaks): Accumulate profit whenever the price today is 
 *      higher than the price yesterday.
 *    - This effectively captures every "climb" in the price chart.
 * 
 * Complexity:
 * - Time Complexity : O(n) - Single pass through the prices array.
 * - Space Complexity: O(1) - Constant auxiliary space.
 */
import java.util.*;

public class StockBuySell {

    /**
     * Case 1: Max profit with exactly ONE transaction.
     */
    static int maxProfitSingleTransaction(int[] prices) {
        if (prices.length == 0)
            return 0;

        int minPrice = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            // Update the minimum price seen so far
            minPrice = Math.min(minPrice, prices[i]);

            // Calculate potential profit if sold today
            int currentProfit = prices[i] - minPrice;

            // Update global max profit
            maxProfit = Math.max(maxProfit, currentProfit);
        }
        return maxProfit;
    }

    /**
     * Case 2: Max profit with UNLIMITED transactions (Valley-Peak Approach).
     * We buy at every local valley and sell at every local peak.
     */
    static int maxProfitMultipleTransactions(int[] prices) {
        int totalProfit = 0;

        for (int i = 1; i < prices.length; i++) {
            // If price increases compared to yesterday, "book" that profit
            if (prices[i] > prices[i - 1]) {
                totalProfit += (prices[i] - prices[i - 1]);
            }
        }
        return totalProfit;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Stock Buy and Sell Optimizer ---");
        System.out.print("Enter number of days: ");
        int n = sc.nextInt();

        int[] prices = new int[n];
        System.out.print("Enter stock prices for " + n + " days: ");
        for (int i = 0; i < n; i++) {
            prices[i] = sc.nextInt();
        }

        // Execution
        int singleTxnProfit = maxProfitSingleTransaction(prices);
        int multiTxnProfit = maxProfitMultipleTransactions(prices);

        // Result Output
        System.out.println("\n--- Profit Analysis ---");
        System.out.println("Daily Prices: " + Arrays.toString(prices));
        System.out.println("Maximum Profit (Single Transaction):     " + singleTxnProfit);
        System.out.println("Maximum Profit (Multiple Transactions):   " + multiTxnProfit);

        sc.close();
    }
}
