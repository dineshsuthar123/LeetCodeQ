package Daily_questions;

public class Shifting_Letters_II {
    public static void main(String[] args) {
        LetterShifter shifter = new LetterShifter();

        // Test case 1
        String s1 = "abc";
        int[][] shifts1 = {{0,1,0}, {1,2,1}, {0,2,1}};
        System.out.println("Test case 1:");
        System.out.println("Input: s = \"" + s1 + "\"");
        System.out.println("Output: " + shifter.shiftingLetters(s1, shifts1));  // Expected: "ace"

        // Test case 2
        String s2 = "dztz";
        int[][] shifts2 = {{0,0,0}, {1,1,1}};
        System.out.println("\nTest case 2:");
        System.out.println("Input: s = \"" + s2 + "\"");
        System.out.println("Output: " + shifter.shiftingLetters(s2, shifts2));  // Expected: "catz"
    }
}

class LetterShifter {
    public String shiftingLetters(String s, int[][] shifts) {
        char[] chars = s.toCharArray();
        int[] Count = new int[chars.length + 1];

        for (int[] shift : shifts) {
            Count[shift[0]] += shift[2] == 1 ? 1 : -1;
            Count[shift[1] + 1] += shift[2] == 1 ? -1 : 1;
        }

        int sum = 0;
        for (int i = 0; i < chars.length; i++) {
            sum = ((sum + Count[i]) % 26 + 26) % 26;
            chars[i] = (char)('a' + ((chars[i] - 'a' + sum) % 26 + 26) % 26);
        }

        return new String(chars);
    }
}