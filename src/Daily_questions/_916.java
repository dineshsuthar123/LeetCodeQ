package Daily_questions;

import java.util.*;

class WordSubsetsSolution {
    public List<String> wordSubsets(String[] words1, String[] words2) {
        List<String> list = new ArrayList();
        int n = words1.length;
        int m = words2.length;

        int[] Max = new int[26];

        for(int i = 0; i < m; i++) {
            int[] arr = new int[26];
            for(char ch : words2[i].toCharArray()) {
                int idx = ch - 'a';
                arr[idx]++;
                Max[idx] = (Max[idx] < arr[idx]) ? arr[idx] : Max[idx];
            }
        }

        for(int i = 0; i < n; i++) {
            int[] aArr = new int[26];

            for(char ch : words1[i].toCharArray()) {
                aArr[ch - 'a']++;
            }

            if(checkSubset(Max, aArr)) {
                list.add(words1[i]);
            }
        }

        return list;
    }

    boolean checkSubset(int[] arr, int[] aArr) {
        for(int i = 0; i < 26; i++) {
            if(arr[i] > aArr[i]) {
                return false;
            }
        }
        return true;
    }
}

public class _916 {
    public static void main(String[] args) {
        // Test case
        String[] words1 = {"amazon","apple","facebook","google","leetcode"};
        String[] words2 = {"e","o"};

        WordSubsetsSolution solution = new WordSubsetsSolution();
        List<String> result = solution.wordSubsets(words1, words2);

        System.out.println("Words that are universal for words2:");
        for(String word : result) {
            System.out.println(word);
        }
    }
}