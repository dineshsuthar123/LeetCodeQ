package Daily_questions;

class _1769 {
    static class MinBallOperations {
        public int[] minOperations(String boxes) {
            int n = boxes.length();
            int[] path = new int[n];

            int firstcount = 0;
            int firstsum = 0;

            for(int i = 0; i < n; ++i){
                path[i] = firstcount * i - firstsum;
                if(boxes.charAt(i) == '1'){
                    ++firstcount;
                    firstsum += i;
                }
            }

            int secondcount = 0;
            int secondsum = 0;

            for(int i = n - 1; i >= 0; --i){
                path[i] += secondsum - secondcount * i;
                if(boxes.charAt(i) == '1'){
                    ++secondcount;
                    secondsum += i;
                }
            }

            return path;
        }
    }

    public static void main(String[] args) {
        MinBallOperations solution = new MinBallOperations();
        String test = "110";
        int[] result = solution.minOperations(test);
        for(int x : result) {
            System.out.print(x + " ");
        }
    }
}