package Daily_questions;

public class _2429 {
    public static void main(String[] args) {
        int num1 = 3, num2 = 5;

        XORMinimizer solution1 = new XORMinimizer();
        System.out.println("Original Solution Result: " + solution1.minimizeXor(num1, num2));

        AlternativeXORMinimizer solution2 = new AlternativeXORMinimizer();
        System.out.println("Alternative Solution Result: " + solution2.minimizeXor(num1, num2));
    }
}

class XORMinimizer {
    public int minimizeXor(int num1, int num2) {
        int a = Integer.bitCount(num1);
        int b = Integer.bitCount(num2);
        int res = num1;
        for (int i = 0; i < 32; i++) {
            if (a > b && ((1 << i) & num1) > 0) {
                res ^= 1 << i;
                a--;
            }
            if (a < b && ((1 << i) & num1) == 0) {
                res ^= 1 << i;
                a++;
            }
        }
        return res;
    }
}

class AlternativeXORMinimizer {
    public int minimizeXor(int num1, int num2) {
        int targetBits = Integer.bitCount(num2);

        if (targetBits == Integer.bitCount(num1)) {
            return num1;
        }

        int result = 0;
        int remainingBits = targetBits;

        for (int i = 31; i >= 0 && remainingBits > 0; i--) {
            if ((num1 & (1 << i)) != 0) {
                result |= (1 << i);
                remainingBits--;
            }
        }

        if (remainingBits > 0) {
            for (int i = 0; i < 32 && remainingBits > 0; i++) {
                if ((num1 & (1 << i)) == 0) {
                    result |= (1 << i);
                    remainingBits--;
                }
            }
        }

        return result;
    }
}