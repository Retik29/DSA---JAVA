
// Group Anagrams - Group strings that are anagrams of each other
// Time Complexity: O(n * k log k) where k is max length of string | Space Complexity: O(n * k)
import java.util.*;

public class GroupAnagrams {

    static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0)
            return new ArrayList<>();

        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String sorted = new String(chars);

            if (!map.containsKey(sorted))
                map.put(sorted, new ArrayList<>());
            map.get(sorted).add(s);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of strings: ");
        int n = sc.nextInt();
        String[] strs = new String[n];
        System.out.println("Enter " + n + " strings:");
        for (int i = 0; i < n; i++)
            strs[i] = sc.next();

        List<List<String>> grouped = groupAnagrams(strs);
        System.out.println("Grouped Anagrams: " + grouped);

        sc.close();
    }
}
