package org.example.baekjoon.p11399;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        // 각 사람마다 걸리느 시간을 입력받는다
        int[] times = new int[N];
        for(int i=0; i<N; i++) {
            times[i] = sc.nextInt();
        }

        // 0123 오름차순 정렬한다
        for(int target=0; target < N; target ++) {
            // 타겟이 0이면 정렬되었다 가정한다
            if (target == 0) {
                continue;
            }
//            times[target]

            // 타겟이 들어갈 위치를 찾는다(정렬된 애들중 나랑 같이 같거나 작은 애를 찾으면 타겟자리 == 그놈 다음 자리)
            int place = 0;
            for(int i = target - 1; i >= 0; i--) {
                if (times[i] <= times[target]) {
                    place = i + 1;
                    break;
                }
            }

            // place에 target을 넣고, 오른쪽에 위치한 애들을 한 칸씩 오른쪽으로 이동한다

            int newValue = times[target];
            for (int i = place; i <= target; i++) {
                int temp = times[i];
                times[i] = newValue;
                newValue = temp;
            }
        }

        int answer = 0;
        int waitingPeopleCnt = N;
        for(int time: times) {
            answer += (time * waitingPeopleCnt);
            waitingPeopleCnt--;
        }
        System.out.println(answer);
    }
}
