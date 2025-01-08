package Daily_questions;

public class _3042 {
    static class PrefixSuffixCounter {
        public int countPrefixSuffixPairs(String[] words) {
            int count = 0;
            int n = words.length;

            for (int i = 0; i < n - 1; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (isPrefixAndSuffix(words[i], words[j])) {
                        count++;
                    }
                }
            }
            return count;
        }

        private boolean isPrefixAndSuffix(String str1, String str2) {
            if (str1.length() > str2.length()) {
                return false;
            }
            return str2.startsWith(str1) && str2.endsWith(str1);
        }
    }

    public static void main(String[] args) {
        PrefixSuffixCounter counter = new PrefixSuffixCounter();


        String[][] testCases = {
                {"a", "aba", "ababa", "aa"},
                {"pa", "papa", "ma", "mama"},
                {"abab", "ab"}
        };

        for (int i = 0; i < testCases.length; i++) {
            int result = counter.countPrefixSuffixPairs(testCases[i]);
            System.out.printf("Test Case %d: %d%n", i + 1, result);
        }
    }
}