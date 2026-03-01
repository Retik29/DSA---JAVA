
/**
 * Balanced Parenthesis Check
 * 
 * Problem: Given a string containing brackets '(', '{', '[', ')', '}', ']', 
 * determine if the input string is valid.
 * 
 * Logic: Use a Stack.
 * 1. Traverse the string character by character.
 * 2. If the character is an opening bracket ('(', '{', '['), push it onto the stack.
 * 3. IF the character is a closing bracket (')', '}', ']'):
 *    - If the stack is empty, it means there's no matching opening bracket -> NOT balanced.
 *    - Pop the top character from the stack.
 *    - If the popped character doesn't match the current closing bracket type -> NOT balanced.
 * 4. After the full traversal, if the stack is still not empty, some brackets weren't closed -> NOT balanced.
 * 
 * Time Complexity : O(n) - Single pass through the string.
 * Space Complexity: O(n) - In the worst case (all opening brackets), the stack stores n characters.
 */
import java.util.*;

public class BalancedParenthesis {

    /**
     * Checks if the given bracket expression is balanced.
     * 
     * @param s Input string
     * @return true if balanced, false otherwise
     */
    static boolean isBalanced(String s) {
        // Deque is preferred over Stack class in Java
        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            // Push opening brackets
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            // Handle closing brackets
            else if (c == ')' || c == '}' || c == ']') {
                // If stack is empty but we have a closing bracket, it's unbalanced
                if (stack.isEmpty())
                    return false;

                char top = stack.pop();

                // Mismatch check
                if ((c == ')' && top != '(') ||
                        (c == '}' && top != '{') ||
                        (c == ']' && top != '[')) {
                    return false;
                }
            }
        }

        // If stack is empty, all opening brackets were matched
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input Gathering
        System.out.print("Enter an expression containing brackets: ");
        String s = sc.nextLine();

        // Output results
        System.out.println("--- Balance Check Results ---");
        if (isBalanced(s)) {
            System.out.println("The expression \"" + s + "\" IS balanced.");
        } else {
            System.out.println("The expression \"" + s + "\" is NOT balanced.");
        }

        sc.close();
    }
}
