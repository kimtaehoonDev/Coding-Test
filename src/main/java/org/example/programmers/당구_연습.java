package org.example.programmers;

// 성공 / 33분
// 단순한 구현문제
// 정답률이 낮은데, 그정도 난이도는 아니라고 생각함.

// 양쪽 네 개 벽을 기준으로 시작공을 데칼코마니해주면 됨
// 쿠션이 일어나는 벽에서 데칼코마니를 하면, 거리는 똑같이 나올 수밖에 없음

// 다만 고려해야 할 건 공이 같은 라인에 위치할 때
// (3,3)에서 (7,3)에 위치한 공을 치려할 때 (7,3)을 통과할 수 없음. 이 부분만 고려해서 문제를 풀면 됨

public class 당구_연습 {
    class Solution {
        public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
            int[] answer = new int[balls.length];
            int idx = 0;

            int[][] startBalls = new int[4][2];
            startBalls[0] = new int[] {-startX, startY};
            startBalls[1] = new int[] {2 * m - startX, startY};
            startBalls[2] = new int[] {startX, -startY};
            startBalls[3] = new int[] {startX, 2 * n - startY};

            for(int[] ball: balls) {
                int min = 1000000000;
                for(int i=0; i<4; i++) {
                    if (startY == ball[1]) {
                        // startX < ball[0] 타겟 -> 목적지공
                        if (startX < ball[0] && i==1) {
                            continue; // 이쪽 방향으로 쿠셔닝 불가능
                        } else if (startX > ball[0] && i == 0) {
                            continue;
                        }
                    } else if (startX == ball[0]) {
                        if(startY < ball[1] && i == 3) {
                            continue;
                        } else if (startY > ball[1] && i == 2) {
                            continue;
                        }
                    }
                    int[] startBall = startBalls[i];
                    int distance = calculate(startBall, ball);
                    min = Math.min(distance, min);
                    // System.out.println(Arrays.toString(startBall) + " -> " + Arrays.toString(ball) + ": Distace : " + distance);
                }

                answer[idx++] = min;
            }
            return answer;
        }

        public int calculate(int[] start, int[] target) {
            return (int) Math.pow(start[0] - target[0], 2) + (int) Math.pow(start[1] - target[1], 2);
        }
    }
}
