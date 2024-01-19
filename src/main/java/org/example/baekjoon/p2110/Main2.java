package org.example.baekjoon.p2110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 아이디어 또 떠올리지 못함. 공유기의 개수를 변수로 둘 생각을 하지 못함. 이 생각 하기 어려운 거 같음
public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 집개수
        int C = Integer.parseInt(st.nextToken()); // 공유기 개수
        int[] routers = new int[N];
        for(int i = 0; i<N; i++) {
            routers[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(routers);
//        System.out.println(Arrays.toString(routers));

        int low = 1;
        int high = routers[routers.length - 1] - routers[0] + 1;
        while(low < high) {
            // mid는 길이임
            int mid = (low + high) / 2;
            int cnt = calRouterCnt(routers, mid);
            if (cnt < C) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        System.out.println(low - 1);
    }

    public static int calRouterCnt(int[] routers, int gap) {//
        int cnt = 1;
        int prev = routers[0];
        for(int i = 1; i<routers.length; i++) {
            if (routers[i] - prev >= gap) {
                // 설치 가능
                cnt++;
                prev = routers[i];
            }
        }
        return cnt;
    }
}
