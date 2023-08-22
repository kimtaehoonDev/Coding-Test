package org.example.programmers;

// 엣지 케이스를 잘 처리해주지 못함.
// 나는 이렇게 풀었지만, 더 좋은 풀이가 있어 해당 방식으로도 풀어봄
public class 두_원_사이의_정수쌍 {
    class Solution {
        public long solution(int r1, int r2) {
            long cnt = 0;
            cnt += calculate(r2);
            cnt -= calculate2(r1);
            return cnt;
        }

        // 반지름 r일 때, 안에 있는 점들의 개수를 구한다
        public long calculate(int r) {
            long cnt = 0;
            for(long i=0; i<=r; i++) {
                if (i==0) { //엣지케이스
                    cnt += (2*r + 1);
                    continue;
                }

                long temp = find(i, r);
                cnt += 2 * temp;
            }
            return cnt;
        }

        // x값이 i일 때, 가능한 y값의 개수
        public long find(long i, long r) {
            long low = 0;
            long high = r;

            while(low < high) {
                long mid = (low + high) / 2;

                if (mid * mid <= r * r - i * i) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return (low - 1) * 2 + 1;
        }

        public long calculate2(int r) {
            long cnt = 0;
            for(long i=0; i<=r; i++) {
                if (i==0) { //엣지케이스
                    cnt += (2*r - 1);
                    continue;
                }

                long temp = find2(i, r);
                cnt += 2 * temp;
            }
            return cnt;
        }

        public long find2(long i, long r) {
            if (i == r) {
                return 0;
            }
            long low = 0;
            long high = r;

            while(low < high) {
                long mid = (low + high) / 2;

                if (mid * mid < r * r - i * i) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return (low - 1) * 2 + 1;
        }
    }

}
