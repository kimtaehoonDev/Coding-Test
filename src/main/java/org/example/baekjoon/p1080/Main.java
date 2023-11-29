package org.example.baekjoon.p1080;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[][] sames = new boolean[N][M];
        int[][] aryA = new int[N][M];
        // 1. 입력을 받아 sames를 만든다
        for(int i = 0; i<N; i++) {
            String input = br.readLine();
            for(int j = 0; j<M; j++) {
                aryA[i][j] = Integer.parseInt(input.substring(j, j+1));
            }
        }
        for(int i = 0; i<N; i++) {
            String input = br.readLine();
            for(int j = 0; j<M; j++) {
                if (Integer.parseInt(input.substring(j, j+1)) == aryA[i][j]) {
                    sames[i][j] = true;
                } else {
                    sames[i][j] = false;
                }
            }
        }
        int answer = 0;
        // 2. 한칸씩 이동하며 해당 블록을 뒤집어야 하는지 확인한다
        for(int i = 0; i<N; i++) {
            if (i + 2 >= N) {
                break;
            }
            for(int j = 0; j<M; j++) {
                if (j + 2 >= M) {
                    break;
                }
                if (sames[i][j]) {
                    continue;
                }
                // 뒤집어야 할 경우
                answer++;
                for(int newI = i; newI < i + 3; newI++) {
                    for(int newJ = j; newJ < j + 3; newJ++) {
                        sames[newI][newJ] = !sames[newI][newJ];
                    }
                }
            }
        }

        // 3. 완료된 후, Sames를 순회하며 모두 t인지 확인한다 -> cnt 반환
        for(int i = 0; i<N; i++) {
            for(int j = 0; j<M; j++) {
                if (!sames[i][j]) {
                    answer = -1;
                    break;
                }
            }
        }
        //   -> same이 false인게 있다면 -> -1 반환
        System.out.println(answer);
    }
}
