package org.example.baekjoon.p2110;

import java.io.*;
import java.util.*;

// 상한선을 찾는다
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //집의 개수
        int C = Integer.parseInt(st.nextToken());
        int[] houses = new int[N];
        for(int i=0; i<N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(houses);

        int min = 1;
        int max = (houses[N-1] - houses[0]) / (C-1) + 1; //10억까지
        int middle = -1;
        boolean isPossible = false;
        while (min < max) {
            middle = (max - min) / 2 + min; // middle: 선택한 공유기간 최소 거리

            int prev = houses[0];
            int selectedCnt = 1;
            for(int i=1; i<N;i++) {
                // 이전 집과 지금 집과의 거리 계산한다
                int distance = houses[i] - prev;
                if (distance >= middle) {
                    //해당 집은 최소거리를 넘겼다 == 공유기 설치가 가능한 최초 지점
                    prev = houses[i];
                    selectedCnt += 1;
                    if (selectedCnt == C) {
                        break;
                    }
                }
            }
            if (selectedCnt < C) {
                isPossible = false;
            } else {
                isPossible = true;
            }

            if (isPossible) {
                min = middle + 1;
            } else {
                max = middle;
            }
        }

        System.out.println(min - 1);
    }

}