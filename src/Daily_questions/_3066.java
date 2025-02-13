package Daily_questions;

import java.util.PriorityQueue;
import java.util.Scanner;

class UniqueSolution3066 {
    public int minOperations(int[] nums, int k) {
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        for (int num : nums) {
            minHeap.add((long) num);
        }

        int operations = 0;
        while (minHeap.size() >= 2 && minHeap.peek() < k) {
            long x = minHeap.poll();
            long y = minHeap.poll();
            long combined = x * 2 + y;
            minHeap.add(combined);
            operations++;
        }
        return operations;
    }
}

public class _3066 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of elements: ");
        int n = scanner.nextInt();
        int[] nums = new int[n];
        System.out.println("Enter elements: ");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        System.out.print("Enter k: ");
        int k = scanner.nextInt();
        scanner.close();

        UniqueSolution3066 solution = new UniqueSolution3066();
        System.out.println("Minimum operations: " + solution.minOperations(nums, k));
    }
}
