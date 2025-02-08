package Daily_questions;

import java.util.*;

class NumberContainersSolution1 {
    private HashMap<Integer, Integer> indexToNumber;
    private HashMap<Integer, TreeSet<Integer>> numberToIndices;

    public NumberContainersSolution1() {
        indexToNumber = new HashMap<>();
        numberToIndices = new HashMap<>();
    }

    public void change(int index, int number) {
        if (indexToNumber.containsKey(index)) {
            int oldNumber = indexToNumber.get(index);
            TreeSet<Integer> oldIndices = numberToIndices.get(oldNumber);
            if (oldIndices != null) {
                oldIndices.remove(index);
                if (oldIndices.isEmpty()) {
                    numberToIndices.remove(oldNumber);
                }
            }
        }
        numberToIndices.computeIfAbsent(number, k -> new TreeSet<>()).add(index);
        indexToNumber.put(index, number);
    }

    public int find(int number) {
        TreeSet<Integer> indices = numberToIndices.get(number);
        return (indices == null || indices.isEmpty()) ? -1 : indices.first();
    }
}

class NumberContainersSolution2 {
    private Map<Integer, PriorityQueue<Integer>> res;
    private Map<Integer, Integer> index_val;

    public NumberContainersSolution2() {
        res = new HashMap<>();
        index_val = new HashMap<>();
    }

    public void change(int index, int number) {
        if (index_val.containsKey(index)) {
            int prevNum = index_val.get(index);
            if (prevNum == number) return;
            res.get(prevNum).remove(index);
        }

        res.computeIfAbsent(number, k -> new PriorityQueue<>()).offer(index);
        index_val.put(index, number);
    }

    public int find(int number) {
        PriorityQueue<Integer> pq = res.getOrDefault(number, new PriorityQueue<>());
        return pq.isEmpty() ? -1 : pq.peek();
    }
}

public class _2349 {
    public static void main(String[] args) {
        NumberContainersSolution1 solution1 = new NumberContainersSolution1();
        solution1.change(1, 10);
        solution1.change(2, 10);
        solution1.change(3, 20);
        System.out.println("Solution1 Find 10: " + solution1.find(10)); // Expected: 1
        System.out.println("Solution1 Find 20: " + solution1.find(20)); // Expected: 3

        NumberContainersSolution2 solution2 = new NumberContainersSolution2();
        solution2.change(1, 10);
        solution2.change(2, 10);
        solution2.change(3, 20);
        System.out.println("Solution2 Find 10: " + solution2.find(10)); // Expected: 1
        System.out.println("Solution2 Find 20: " + solution2.find(20)); // Expected: 3
    }
}
