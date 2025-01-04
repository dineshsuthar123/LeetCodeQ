package Daily_questions;

import java.util.*;

class _3Palindromic_Subsequences {
    public static void main(String[] args) {
        Solution1 sol1 = new Solution1();
        Solution2 sol2 = new Solution2();

        String[] testCases = {"aabca", "adc", "bbcbaba"};

        for (String test : testCases) {
            System.out.println("Test case: " + test);
            System.out.println("Solution1 output: " + sol1.countPalindromicSubsequence(test));
            System.out.println("Solution2 output: " + sol2.countPalindromicSubsequence(test));
            System.out.println();
        }
    }
}

class Solution1 {
    public int countPalindromicSubsequence(String s) {
        int[] firstOccurrence = new int[26];
        int[] lastOccurrence = new int[26];
        Arrays.fill(firstOccurrence, -1);
        Arrays.fill(lastOccurrence, -1);

        for (int i = 0; i < s.length(); i++) {
            int charIndex = s.charAt(i) - 'a';
            if (firstOccurrence[charIndex] == -1) {
                firstOccurrence[charIndex] = i;
            }
            lastOccurrence[charIndex] = i;
        }

        int result = 0;
        for (int i = 0; i < 26; i++) {
            if (firstOccurrence[i] != -1 && lastOccurrence[i] != firstOccurrence[i]) {
                Set<Character> uniqueMiddleChars = new HashSet<>();
                for (int j = firstOccurrence[i] + 1; j < lastOccurrence[i]; j++) {
                    uniqueMiddleChars.add(s.charAt(j));
                }
                result += uniqueMiddleChars.size();
            }
        }

        return result;
    }
}

class Solution2 {
    public int countPalindromicSubsequence(String s) {
        int[] arr = new int[26];
        for (char u : s.toCharArray()) {
            arr[u - 'a']++;
        }
        int[] arr1 = new int[26];
        HashSet<Integer> hash = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            int arr3 = s.charAt(i) - 'a';
            arr[arr3]--;
            for (int j = 0; j < 26; j++) {
                if (arr1[j] > 0 && arr[j] > 0) {
                    hash.add(26 * arr3 + j);
                }
            }
            arr1[arr3]++;
        }

        return hash.size();
    }
}