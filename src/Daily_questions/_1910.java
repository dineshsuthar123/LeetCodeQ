package Daily_questions;

import java.util.*;

class UniqueSolution1910 {
    public String removeOccurrences(String s, String part) {
        String current = s;
        int partLength = part.length();
        while (current.contains(part)) {
            int index = current.indexOf(part);
            current = current.substring(0, index) + current.substring(index + partLength);
        }
        return current;
    }
}

class UniqueSolutionStack1910 {
    public String removeOccurrences(String s, String part) {
        char[] input = s.toCharArray();
        char[] target = part.toCharArray();
        char[] resultStack = new char[input.length];
        int stackSize = 0, targetLength = target.length;
        char targetEndChar = target[targetLength - 1];

        for (char currentChar : input) {
            resultStack[stackSize++] = currentChar;
            if (currentChar == targetEndChar && stackSize >= targetLength) {
                int i = stackSize - 1, j = targetLength - 1;
                while (j >= 0 && resultStack[i] == target[j]) {
                    i--;
                    j--;
                }
                if (j < 0) {
                    stackSize = i + 1;
                }
            }
        }
        return new String(resultStack, 0, stackSize);
    }
}

public class _1910 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter string: ");
        String s = scanner.nextLine();
        System.out.print("Enter part to remove: ");
        String part = scanner.nextLine();
        scanner.close();

        UniqueSolution1910 solution1 = new UniqueSolution1910();
        UniqueSolutionStack1910 solution2 = new UniqueSolutionStack1910();

        System.out.println("Result using String replace method: " + solution1.removeOccurrences(s, part));
        System.out.println("Result using Stack approach: " + solution2.removeOccurrences(s, part));
    }
}
