package org.example.baekjoon.p18111;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 성공 / 31분 / 브루트포스
// 이분탐색이라 생각했는데 low, high를 조정할만한 규칙성이 없음
// 높이가 0~256까지의 좁은 범위라서 브루트포스가 가능하겠다 생각함
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int inventory = Integer.parseInt(st.nextToken()); // 인벤토리에 있는 블록 개수

        int[][] graph = new int[N][M];
        for(int i = 0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
//        for(int[] line : graph) {
//            System.out.println(Arrays.toString(line));
//        }

        int minTime = Integer.MAX_VALUE;
        int answer = -1;
        for(int i = 256; i>=0; i--) {
            int[] blocks = work(graph, i); // 그래프 땅 높이를 i로 바꾸려면 blocks[0]개 쌓고, blocks[1]개 제거하면 된다.
            if (blocks[ADD] > blocks[REMOVE] + inventory) {
                continue;// 만들 수 없음
            }
            int time = calcTime(blocks);
//            System.out.println("높이 " +i+"에서 "+ Arrays.toString(blocks) + "더하고 뺄때, 소요 시간 " + time);
            if (minTime > time) {
                minTime = time;
                answer = i;
            }
        }
        System.out.println(minTime + " " + answer);
    }

    static int calcTime(int[] blocks) {
        return blocks[ADD] + blocks[REMOVE] * 2;
    }

    static int ADD = 0;
    static int REMOVE = 1;

    static int[] work(int[][] graph, int height) {
        int[] results = new int[2];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                if (height > graph[i][j]) {
                    results[ADD] += (height - graph[i][j]);
                } else if (height < graph[i][j]) {
                    results[REMOVE]+= (graph[i][j] - height);
                }
            }
        }
        return results;
    }
}
