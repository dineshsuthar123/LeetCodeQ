package Daily_questions;

import java.util.*;

class _3174 {
    public static void main(String[] args) {
        String input = "a1b2c3";
        UniqueSolution solution = new UniqueSolution();
        System.out.println(solution.clearDigitsStack(input));
        System.out.println(solution.clearDigitsList(input));
    }
}

class UniqueSolution {
    public String clearDigitsStack(String s) {
        StringBuilder stack = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                if (!stack.isEmpty()) {
                    stack.deleteCharAt(stack.length() - 1);
                }
            } else {
                stack.append(c);
            }
        }
        return stack.toString();
    }

    public String clearDigitsList(String s) {
        List<Character> list = new ArrayList<>();
        for (char c : s.toCharArray()) {
            list.add(c);
        }

        while (true) {
            int digitIndex = -1;
            for (int i = 0; i < list.size(); i++) {
                if (Character.isDigit(list.get(i))) {
                    digitIndex = i;
                    break;
                }
            }
            if (digitIndex == -1) {
                break;
            }

            int leftNonDigit = -1;
            for (int j = digitIndex - 1; j >= 0; j--) {
                if (!Character.isDigit(list.get(j))) {
                    leftNonDigit = j;
                    break;
                }
            }

            if (leftNonDigit != -1) {
                list.remove(leftNonDigit);
                list.remove(digitIndex - 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char c : list) {
            sb.append(c);
        }
        return sb.toString();
    }
}
