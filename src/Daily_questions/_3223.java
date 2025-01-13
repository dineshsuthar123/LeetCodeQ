package Daily_questions;

import java.util.HashMap;

public class _3223 {
    public static void main(String[] args) {
        String test1 = "abaacbcbb";
        String test2 = "aa";
        String test3 = "cabaabac";

        FrequencyMapSolution solution1 = new FrequencyMapSolution();
        CountArraySolution solution2 = new CountArraySolution();

        System.out.println("Testing FrequencyMapSolution:");
        System.out.println("Test 1: " + test1 + " -> " + solution1.minimumLength(test1));
        System.out.println("Test 2: " + test2 + " -> " + solution1.minimumLength(test2));
        System.out.println("Test 3: " + test3 + " -> " + solution1.minimumLength(test3));

        System.out.println("\nTesting CountArraySolution:");
        System.out.println("Test 1: " + test1 + " -> " + solution2.minimumLength(test1));
        System.out.println("Test 2: " + test2 + " -> " + solution2.minimumLength(test2));
        System.out.println("Test 3: " + test3 + " -> " + solution2.minimumLength(test3));
    }
}

class FrequencyMapSolution {
    public int minimumLength(String s) {
        HashMap<Character, Integer> count = new HashMap<>();
        for (char c : s.toCharArray()) {
            count.put(c, count.getOrDefault(c, 0) + 1);
        }
        int minus = 0;
        for (int value : count.values()) {
            while (value >= 3) {
                minus += 2;
                value -= 2;
            }
        }
        return s.length() - minus;
    }
}

class CountArraySolution {
    public int minimumLength(String s) {
        int countFreq[] = new int[26];
        int total = 0;
        for(char c : s.toCharArray()){
            countFreq[c - 'a']++;
        }
        for(int freq : countFreq){
            if(freq == 0){
                continue;
            }
            if(freq % 2 == 0){
                total += 2;
            }
            else{
                total += 1;
            }
        }
        return total;
    }
}