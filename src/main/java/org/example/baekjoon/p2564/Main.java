package org.example.baekjoon.p2564;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 성공 / 50분 / 구현
// 어떻게 하면 쉬울까 고민하다가 그냥 막구현이 답이라는 걸 깨달음.
// 동북서남을 4132 에서 0123으로 변경했었는데 이거 때문에 헷갈려서 오래 걸림(초반에 잘못 생각해서 좌표를 바꿨다가 참사가 남)
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // ㄱㅏ로
        int M = Integer.parseInt(st.nextToken()); // 세로
        int K = Integer.parseInt(br.readLine());
        int[][] cities = new int[K][2];
        for(int i = 0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int dirInput = Integer.parseInt(st.nextToken());
            int dir = changeDir(dirInput); // 입력값이 난잡해서 반시계 방향으로 수정 (동북서남 0123)
            int loc = Integer.parseInt(st.nextToken());
            cities[i][0] = dir;
            cities[i][1] = loc;
        }
        st = new StringTokenizer(br.readLine());
        int userDir = changeDir(Integer.parseInt(st.nextToken()));
        int userLoc = Integer.parseInt(st.nextToken());
//        for(int[] city : cities) {
//            System.out.println(Arrays.toString(city));
//        }
//        System.out.println(userDir + ", " + userLoc);

        int answer = 0;
        for(int[] city : cities) {
            if (city[0] == userDir) { // 검토완
                answer += Math.abs(userLoc - city[1]);
            } else if ((city[0] + 1)%4 == userDir || (userDir + 1) % 4 == city[0]) {
                // 한칸 인접
                if (city[0] == 0) {
                    answer += (N - userLoc);
                } else if (city[0] == 1) {
                    answer += userLoc;
                } else if (city[0] == 2) {
                    answer += userLoc;
                } else {
                    answer += (M - userLoc);
                }

                if (userDir == 0) {
                    answer += (N - city[1]);
                } else if (userDir == 1) {
                    answer += city[1];
                } else if (userDir == 2) {
                    answer += city[1];
                } else {
                    answer += (M - city[1]);
                }

            } else {
                if (city[0] == 0 || city[0] == 2) {
                    answer += Math.min(city[1] + userLoc, 2 * M - city[1] - userLoc) + N;
                } else {
                    answer += Math.min(city[1] + userLoc, 2 * N - city[1] - userLoc) + M;
                }
            }
//            System.out.println(Arrays.toString(city) + "갈 때 중간값 " + answer);
        }
        System.out.println(answer);
    }

    static int changeDir(int input) {
        if (input == 4) { // 동
            return 0;
        } else if (input == 1) { // 북
            return 1;
        } else if (input == 3) { // 서
            return 2;
        } else { // 남(2 -> 3)
            return 3;
        }
    }
}
