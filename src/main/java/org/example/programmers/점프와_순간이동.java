package org.example.programmers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

// 실패 / 40분
// 원인 찾은 후 문제 해결

// 전제조건 : 짝수가 나오면 무조건 순간이동을 하는게 좋다

//예를 들어 66이라는 숫자가 있습니다.
//처음 생각했을 때 64를 만들고 그 때부터 2를 나누어가는게 효율적이라고 생각함

// 짝수인 경우 2로 나눠준 케이스(33), 1을 빼준 케이스(65) 둘 다 확인해야 한다고 생각했으나...

//해당 문제에서 가장 효율적인 방법은 66 -> 33(배터리1) -> 32 -> 16 -> 8 -> 4 -> 2 -> 1 -> 0(배터리2)
//66 -> 65(배터리1) -> 64(배터리2) -> 32 -> ... -> 1 -> 0(배터리3) 보다 위 방법이 더 효율적임

// 66 -> 65 -> 64 -> 32 보다, 66 -> 33 -> 32가 연산을 한 번 줄일 수 있음. (66 -> 65 -> 64 가 33 -> 32 로 압축되었다고 생각해도 무방)
public class 점프와_순간이동 {
    public class Solution {
        public int solution(int n) {
            int answer = 0;
            while(n != 0) {
                if (n % 2 == 0) {
                    n/=2;
                } else {
                    n--;
                    answer++;
                }
            }
            return answer;
        }
    }

}
