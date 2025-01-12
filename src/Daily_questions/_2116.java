package Daily_questions;

public class _2116 {
    public static void main(String[] args) {
        // Test cases
        TestCases testCases = new TestCases();
        testCases.runTests();
    }
}

class ParenthesesValidator {
    public boolean canBeValid(String s, String locked) {
        if (s.length() % 2 != 0) {
            return false;  // Odd length strings can't be valid
        }

        // Left to right pass to check opening parentheses
        if (!checkBalance(s, locked, '(')) {
            return false;
        }

        // Right to left pass to check closing parentheses
        if (!checkBalance(new StringBuilder(s).reverse().toString(),
                new StringBuilder(locked).reverse().toString(), ')')) {
            return false;
        }

        return true;
    }

    private boolean checkBalance(String s, String locked, char open) {
        int balance = 0;  // Track the balance of parentheses
        int wild = 0;     // Count of positions we can change (locked[i] == '0')

        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            char lock = locked.charAt(i);

            if (lock == '1') {
                if (curr == open) {
                    balance++;
                } else {
                    balance--;
                }
            } else {
                wild++;
            }

            if (balance + wild < 0) {
                return false;
            }
        }

        return Math.abs(balance) <= wild && (wild - Math.abs(balance)) % 2 == 0;
    }
}

// Alternative Solution
class Solution {
    public boolean canBeValid(String parentheses, String status) {
        int length = parentheses.length();
        if (length % 2 != 0) return false;
        int minBalance = 0, maxBalance = 0;
        for (int i = 0; i < length; i++) {
            boolean isOpening = parentheses.charAt(i) == '(';
            boolean isUnlocked = status.charAt(i) == '0';
            minBalance += (!isOpening || isUnlocked) ? -1 : 1;
            maxBalance += (isOpening || isUnlocked) ? 1 : -1;
            if (maxBalance < 0) return false;
            minBalance = Math.max(minBalance, 0);
        }
        return minBalance == 0;
    }
}

class TestCases {
    private ParenthesesValidator validator = new ParenthesesValidator();
    private Solution solution = new Solution();

    public void runTests() {
        // Test Case 1
        testCase("))()))", "010100", true);

        // Test Case 2
        testCase("()()", "0000", true);

        // Test Case 3
        testCase(")", "0", false);

        // Additional Test Cases
        testCase("()", "11", true);
        testCase("((", "11", false);
        testCase("))", "11", false);
    }

    private void testCase(String s, String locked, boolean expectedResult) {
        boolean result1 = validator.canBeValid(s, locked);
        boolean result2 = solution.canBeValid(s, locked);

        System.out.println("Test Case:");
        System.out.println("String: " + s);
        System.out.println("Locked: " + locked);
        System.out.println("Expected: " + expectedResult);
        System.out.println("ParenthesesValidator Result: " + result1 +
                " " + (result1 == expectedResult ? "(Correct)" : "(Wrong)"));
        System.out.println("Alternative Solution Result: " + result2 +
                " " + (result2 == expectedResult ? "(Correct)" : "(Wrong)"));
        System.out.println();
    }
}