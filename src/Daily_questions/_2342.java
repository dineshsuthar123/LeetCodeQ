package Daily_questions;

import java.util.*;

class UniqueSolution2342_1 {
    public int maximumSum(int[] nums) {
        int[] max = new int[82];
        int ans = -1;
        for (int x : nums) {
            int sum = 0;
            int temp = x;
            while (temp != 0) {
                sum += temp % 10;
                temp /= 10;
            }
            if (max[sum] != 0) {
                ans = Math.max(ans, x + max[sum]);
            }
            max[sum] = Math.max(max[sum], x);
        }
        return ans;
    }
}

class UniqueSolution2342_2 {
    public static int sumOfDigits(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }

    public int maximumSum(int[] nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int num : nums) {
            int s = sumOfDigits(num);
            if (!map.containsKey(s)) {
                List<Integer> list = new ArrayList<>();
                list.add(num);
                map.put(s, list);
            } else {
                List<Integer> list = map.get(s);
                list.add(num);
                Collections.sort(list, Collections.reverseOrder());
                if (list.size() > 2) {
                    list.remove(2);
                }
            }
        }

        int maxSum = -1;
        for (List<Integer> list : map.values()) {
            if (list.size() >= 2) {
                int sum = list.get(0) + list.get(1);
                maxSum = Math.max(maxSum, sum);
            }
        }

        return maxSum;
    }
}

public class _2342 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of elements: ");
        int n = scanner.nextInt();
        int[] nums = new int[n];
        System.out.println("Enter elements: ");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }
        scanner.close();

        UniqueSolution2342_1 solution1 = new UniqueSolution2342_1();
        UniqueSolution2342_2 solution2 = new UniqueSolution2342_2();

        System.out.println("Result using array approach: " + solution1.maximumSum(nums));
        System.out.println("Result using HashMap approach: " + solution2.maximumSum(nums));
    }
}
