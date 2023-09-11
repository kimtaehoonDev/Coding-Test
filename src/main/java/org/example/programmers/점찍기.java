package org.example.programmers;

// 성공/30분
//
// d * d 옆에 long 붙이는 걸 깜빡해 오래 걸렸다
public class 점찍기 {

    class Solution {
        public long solution(int k, int d) {
            // y 고정
            long answer = 0L;
            for(long i=0L; i<=d; i+=k) {
                long top = (long) Math.floor(Math.sqrt((long)d * d - i * i));
                answer += top / k + 1;
            }
            return answer;
        }
    }
}
