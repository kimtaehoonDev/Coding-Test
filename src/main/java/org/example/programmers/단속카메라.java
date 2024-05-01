package org.example.programmers;

import java.util.*;

// 20분 / 성공 / 그리디
class 단속카메라 {
    public int solution(int[][] routes) {
        // 차 빨리 나가는 순 정렬
        Arrays.sort(routes, (r1, r2) -> r1[1] - r2[1]);
        // for(int[] route : routes) {
        //     System.out.println(Arrays.toString(route));
        // }

        int answer = 0;
        int now = 0;
        while(true) {
            answer++;
            int camera = routes[now][1];
            // System.out.println(camera);
            // car[1]에 카메라 설치
            boolean isEnd = true;
            for(int i = now + 1; i<routes.length; i++) {
                if (routes[i][0] > camera) { // 캡쳐 불가능
                    now = i;
                    isEnd = false;
                    break;
                }
            }
            if (isEnd) {
                break;
            }
        }

        return answer;
    }
}