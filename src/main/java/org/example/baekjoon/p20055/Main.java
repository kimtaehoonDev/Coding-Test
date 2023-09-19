package org.example.baekjoon.p20055;

import java.util.Scanner;

// 성공/2시간
// 로봇이 이동이 불가능한 경우 처리를 실수해서 해당 오류 디버깅하는데 굉장히 오래 시간이 걸림
// 단순한 구현 문제인데 구현의 경우 더 철저하게 수도코드를 작성해야겠다

public class Main {
    static int level = 0;
    static int broken = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] containers = new int[2 * N];
        boolean[] robots = new boolean[2 * N];

        for (int i = 0; i < 2 * N; i++) {
            containers[i] = sc.nextInt();
        }

        int robotStartIdx = 0;
        int robotOutIdx = N - 1;

        // 입력
        while (true) {
            level++; //단계 업

            // 1. 회전한다
            robotStartIdx = (robotStartIdx + 2 * N - 1) % (2 * N);
            robotOutIdx = (robotOutIdx + 2 * N - 1) % (2 * N);
            robots[robotOutIdx] = false; // 로봇을 내린다

            for (int i = N - 1; i >= 0; i--) {
                int idx = (robotStartIdx + i) % (2 * N);
                if (!robots[idx]) {
                    continue;
                }
                // idx칸에 위치한 로봇을 이동한다
                if (containers[(idx + 1) % (2 * N)] < 1 || robots[(idx + 1) % (2 * N)]) { // 이동이 불가능한경우
                    // 로봇은 그 위치에 냅둔다
                    continue;
                }

                robots[idx] = false;
                idx = (idx + 1) % (2 * N);
                containers[idx]--;
                if (containers[idx] <= 0) {
                    broken++;
                }

                if (idx != robotOutIdx) {
                    // 내린다
                    robots[idx] = true;
                }
            }

            // 이동한 위치에서 내구도가 0이 되면 broken++;

            // startIdx에 robots를 위치시키고, 컨테이너 내구도 한 칸 부수기
            if (containers[robotStartIdx] > 0) {
                robots[robotStartIdx] = true;
                containers[robotStartIdx]--;
                if (containers[robotStartIdx] <= 0) {
                    broken++;
                }
            }

            if (broken >= K) {
                break;
            }

        }
        System.out.println(level);
    }
}
