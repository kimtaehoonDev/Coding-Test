package org.example.programmers;

// 성공 / 14분
public class 연속_펄스_부분_수열의_합 {
    class Solution {
        public long solution(int[] sequence) {
            int[] first = new int[sequence.length];
            int[] second = new int[sequence.length];
            for(int i=0; i<sequence.length;i++) {
                if (i%2 == 0) {
                    first[i] = sequence[i];
                    second[i] = -sequence[i];
                } else {
                    first[i] = -sequence[i];
                    second[i] = +sequence[i];
                }
            }

            // System.out.println(Arrays.toString(first));
            // System.out.println(Arrays.toString(second));
            long maxFirst = 0;
            long nowFirst = 0;
            long maxSecond = 0;
            long nowSecond = 0;

            for(int i=0; i<sequence.length;i++) {
                if (nowFirst + first[i] < 0) {
                    nowFirst = 0;
                } else {
                    nowFirst += first[i];
                    maxFirst = Math.max(maxFirst, nowFirst);
                }
                if (nowSecond + second[i] < 0) {
                    nowSecond = 0;
                } else {
                    nowSecond += second[i];
                    maxSecond = Math.max(maxSecond, nowSecond);
                }
            }

            return Math.max(maxFirst, maxSecond);
        }
    }
}
