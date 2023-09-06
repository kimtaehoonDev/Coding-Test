package org.example.programmers;

// 성공 / 50분
// 25분 걸려 문제 풀었지만, 엣지 케이스 처리를 못해 25분 추가 고민을 했다
// 로직에는 문제가 없다는 확신이 들고 나서 코드를 살펴보니
// - 숫자 타입 변환시 문제가 발생했다는 걸 찾을 수 있었다.
// int * int 는 int가 나오는데 해당 부분을 생각하지 못했다...
public class 시소_짝꿍 {

    class Solution {
        static int[] dp;

        public long solution(int[] weights) {

            // dp[x] = n 이라면,
            // 몸무게 x인 사람은 n명 있다는 의미
            dp = new int[4001]; // 인덱스 200부터 4000까지 쓸 예정\
            for(int weight: weights) {
                dp[weight] ++;
                // System.out.println(weight + " " + dp[weight]);
            }

            long answer = 0;
            for(int i=0; i<4001; i++) {
                int[] temp = new int[3];
                if (i % 2 == 0) {
                    temp[0] = dp[i/2];
                }
                if (i%3 == 0) {
                    temp[1] = dp[i/3];
                }
                if (i%4 == 0) {
                    temp[2] = dp[i/4];
                }
                answer += ((long)temp[0] * temp[1] + (long)temp[1] * temp[2] + (long)temp[2] * temp[0]);
            }

            // System.out.println(answer);
            for(int i=100; i<1001; i++) {
                // 몸무게가 같은 사람이 있는 경우
                if (dp[i] > 1) {
                    answer += ((long)dp[i] * (dp[i] - 1)) / 2;
                }
            }

            return answer;
        }
    }
}
