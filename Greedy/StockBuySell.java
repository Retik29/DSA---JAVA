
// Stock Buy and Sell — maximize profit
// Time: O(n) | Space: O(1)
import java.util.*;

public class StockBuySell {

    // Max profit with one transaction
    static int maxProfitOne(int[] prices) {
        int minPrice = prices[0], maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }
        return maxProfit;
    }

    // Max profit with unlimited transactions (buy-sell on every rise)
    static int maxProfitUnlimited(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++)
            if (prices[i] > prices[i - 1])
                profit += prices[i] - prices[i - 1];
        return profit;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of days: ");
        int n = sc.nextInt();
        int[] prices = new int[n];
        System.out.print("Enter " + n + " prices: ");
        for (int i = 0; i < n; i++)
            prices[i] = sc.nextInt();

        System.out.println("Prices: " + Arrays.toString(prices));
        System.out.println("Max profit (1 txn):      " + maxProfitOne(prices));
        System.out.println("Max profit (unlimited):  " + maxProfitUnlimited(prices));

        sc.close();
    }
}
