package Daily_questions;

public class _2657 {
    public static void main(String[] args) {

        int[] A1 = {1, 3, 2, 4};
        int[] B1 = {3, 1, 2, 4};
        int[] A2 = {2, 3, 1};
        int[] B2 = {3, 1, 2};

        SolutionFreq sol1 = new SolutionFreq();
        SolutionBit sol2 = new SolutionBit();
        SolutionEfficient sol3 = new SolutionEfficient();

        System.out.println("Test Case 1:");
        printArray(sol1.findThePrefixCommonArray(A1, B1), "Frequency Array Solution");
        printArray(sol2.findThePrefixCommonArray(A1, B1), "Bit Manipulation Solution");
        printArray(sol3.findThePrefixCommonArray(A1, B1), "Efficient Boolean Solution");

        System.out.println("\nTest Case 2:");
        printArray(sol1.findThePrefixCommonArray(A2, B2), "Frequency Array Solution");
        printArray(sol2.findThePrefixCommonArray(A2, B2), "Bit Manipulation Solution");
        printArray(sol3.findThePrefixCommonArray(A2, B2), "Efficient Boolean Solution");
    }

    // Helper method to print arrays
    private static void printArray(int[] arr, String solutionName) {
        System.out.print(solutionName + ": [");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) System.out.print(", ");
        }
        System.out.println("]");
    }
}

// Solution using frequency array
class SolutionFreq {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] result = new int[n];
        int[] freq = new int[n + 1];
        int count = 0;

        for (int i = 0; i < n; i++) {
            freq[A[i]]++;
            if (freq[A[i]] == 2) count++;

            freq[B[i]]++;
            if (freq[B[i]] == 2) count++;

            result[i] = count;
        }

        return result;
    }
}

// Solution using bit manipulation
class SolutionBit {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        int[] C = new int[n];
        int mask1 = 0, mask2 = 0;

        for (int i = 0; i < n; i++) {
            mask1 |= (1 << A[i]);
            mask2 |= (1 << B[i]);

            int common = mask1 & mask2;
            C[i] = Integer.bitCount(common);
        }

        return C;
    }
}

// Most efficient solution using boolean array
class SolutionEfficient {
    public int[] findThePrefixCommonArray(int[] A, int[] B) {
        int n = A.length;
        boolean[] seen = new boolean[51];
        int[] ans = new int[n];
        int common = 0;

        for (int i = 0; i < n; i++) {
            if (seen[A[i]]) {
                common++;
            } else {
                seen[A[i]] = true;
            }
            if (seen[B[i]]) {
                common++;
            } else {
                seen[B[i]] = true;
            }
            ans[i] = common;
        }

        return ans;
    }
}