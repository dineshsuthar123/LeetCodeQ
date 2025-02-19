package Daily_questions;

import java.util.*;

class UniqueSolution1415 {
    public String getHappyString(int n, int k) {
        List<String> happyStrings = new ArrayList<>();
        generateHappyStrings(happyStrings, new StringBuilder(), n, ' ');
        if (k > happyStrings.size()) {
            return "";
        }
        return happyStrings.get(k - 1);
    }

    private void generateHappyStrings(List<String> happyStrings, StringBuilder current, int n, char lastChar) {
        if (current.length() == n) {
            happyStrings.add(current.toString());
            return;
        }

        for (char c : new char[] {'a', 'b', 'c'}) {
            if (c != lastChar) {
                current.append(c);
                generateHappyStrings(happyStrings, current, n, c);
                current.deleteCharAt(current.length() - 1);
            }
        }
    }
}

public class _1415 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = scanner.nextInt();
        System.out.print("Enter k: ");
        int k = scanner.nextInt();
        scanner.close();

        UniqueSolution1415 solution = new UniqueSolution1415();
        System.out.println("Happy String: " + solution.getHappyString(n, k));
    }
}
