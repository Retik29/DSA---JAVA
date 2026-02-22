
// Allocate Minimum Pages — Binary Search on Answer
// Time: O(n × log(sum - max)) | Space: O(1)
import java.util.*;

public class AllocateMinimumPages {

    // Check if allocation is feasible with max pages = mid
    static boolean isFeasible(int[] pages, int students, int mid) {
        int cnt = 1, sum = 0;
        for (int p : pages) {
            sum += p;
            if (sum > mid) {
                cnt++;
                sum = p;
            }
        }
        return cnt <= students;
    }

    static int allocatePages(int[] pages, int students) {
        if (students > pages.length)
            return -1;
        int lo = Arrays.stream(pages).max().getAsInt(); // at least max page
        int hi = Arrays.stream(pages).sum(); // at most total
        int res = hi;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (isFeasible(pages, students, mid)) {
                res = mid;
                hi = mid - 1;
            } else
                lo = mid + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of books: ");
        int n = sc.nextInt();
        int[] pages = new int[n];
        System.out.print("Enter pages of " + n + " books: ");
        for (int i = 0; i < n; i++)
            pages[i] = sc.nextInt();
        System.out.print("Enter number of students: ");
        int students = sc.nextInt();

        System.out.println("Books: " + Arrays.toString(pages));
        System.out.println("Min max pages allocated: " + allocatePages(pages, students));

        sc.close();
    }
}
