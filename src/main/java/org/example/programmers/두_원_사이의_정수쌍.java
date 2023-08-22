package org.example.programmers;

// 엣지 케이스를 잘 처리해주지 못함.
// 나는 이렇게 풀었지만, 더 좋은 풀이가 있어 해당 방식으로도 풀어봄
public class 두_원_사이의_정수쌍 {
    class Solution {
        public long solution(int r1, int r2) {
            long answer = 0;

            for (long i = 0; i <= r2; i++) {
                if (i == 0) {
                    answer += 2 * (r2 - r1 + 1);
                    continue;
                }
                long start = (long) Math.ceil(Math.sqrt((long) r1 * r1 - (long) i * i));
                long end = (long) Math.floor(Math.sqrt((long) r2 * r2 - (long) i * i));

                long temp = 0;

                // x값이 i일 때, y가 양수일때와 음수일 때 두 가지 케이스가 존재한다.
                temp = 2 * (end - start + 1);

                // start == 0이라는 건 x좌표에 있는 점이다.
                // 즉, y가 양수일때나 음수일때 똑같은 점이기 때문에 한 개를 빼줘야 한다.
                if (start == 0) {
                    temp--;
                }

                // x가 양수일때와 음수일 때 두 가지 케이스가 존재한다.
                answer += (temp * 2);

            }

            return answer;
        }
    }
}
