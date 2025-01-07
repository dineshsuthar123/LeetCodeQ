package Daily_questions;

import java.util.*;

public class _1408 {
    public static void main(String[] args) {
        String[][] testCases = {
                {"mass", "as", "hero", "superhero"},
                {"leetcode", "et", "code"},
                {"blue", "green", "bu"}
        };

        BruteForceSolution bruteForce = new BruteForceSolution();
        OptimizedSolution optimized = new OptimizedSolution();

        for (String[] testCase : testCases) {
            System.out.println("Input: " + Arrays.toString(testCase));
            System.out.println("Brute Force Output: " + bruteForce.stringMatching(testCase));
            System.out.println("Optimized Output: " + optimized.stringMatching(testCase));
            System.out.println();
        }
    }
}

class BruteForceSolution {
    public List<String> stringMatching(String[] words) {
        int n = words.length;
        List<String> ans = new ArrayList<>();

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i != j && words[j].contains(words[i])) {
                    ans.add(words[i]);
                    break;
                }
            }
        }

        return ans;
    }
}

class OptimizedSolution {
    public List<String> stringMatching(String[] words) {
        Arrays.sort(words, (a, b) -> a.length() - b.length());
        Set<String> result = new HashSet<>();
        int n = words.length;

        for (int i = 0; i < n - 1; i++) {
            String current = words[i];
            for (int j = i + 1; j < n; j++) {
                if (words[j].contains(current)) {
                    result.add(current);
                    break;
                }
            }
        }

        return new ArrayList<>(result);
    }
}