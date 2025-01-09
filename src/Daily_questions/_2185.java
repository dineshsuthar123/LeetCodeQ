package Daily_questions;

public class _2185 {
    public static void main(String[] args) {
        PrefixWordCounter counter = new PrefixWordCounter();

        String[] words1 = {"pay", "attention", "practice", "attend"};
        String pref1 = "at";
        System.out.println("Test Case 1:");
        System.out.println("Expected: 2");
        System.out.println("Result: " + counter.prefixCount(words1, pref1));

        String[] words2 = {"leetcode", "win", "loops", "success"};
        String pref2 = "code";
        System.out.println("\nTest Case 2:");
        System.out.println("Expected: 0");
        System.out.println("Result: " + counter.prefixCount(words2, pref2));

        String[] words3 = {"cat", "car", "cart", "dog"};
        String pref3 = "car";
        System.out.println("\nTest Case 3:");
        System.out.println("Expected: 2");
        System.out.println("Result: " + counter.prefixCount(words3, pref3));
    }
}

class PrefixWordCounter {
    public int prefixCount(String[] words, String pref) {
        int count = 0;
        for (String word : words) {
            if (word.startsWith(pref)) {
                count++;
            }
        }
        return count;
    }
}