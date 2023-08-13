package org.example.baekjoon.p14499;

import java.util.*;
import java.io.*;

// 성공. 풀이시간 40분
//
// 이 문제에서 핵심은 구조체를 만드는 것.
// 주사위를 배열에 넣고 굴리는 거 로직짤 수 없음. 그냥 구조체를 만들어서 주사위가 회전하는 상황에 맞게 값들을 변환하면 됨

// 이런 구현 문제들은(사실 구현 뿐만 아니더라도) 문제를 주의깊게 읽어야함
public class Main {
    public static int[] dx = new int[] {0, 0, -1, 1};
    public static int[] dy = new int[] {1, -1, 0, 0};

    public static void main(String []args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] graph = new int[N][M];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] orders = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<K; i++) {
            orders[i] = Integer.parseInt(st.nextToken());
        }


        List<Integer> answers = new ArrayList<>();
        Dice dice = new Dice();
        for (int i=0; i<K; i++) {
            int direction = orders[i] - 1;
            int nx = x + dx[direction];
            int ny = y + dy[direction];
            if (nx < 0 || ny <0 || N<= nx || M <= ny) {
                continue;
            }
            answers.add(dice.roll(direction));
            x = nx;
            y = ny;

            if (graph[x][y] == 0) {
                graph[x][y] = dice.bot;
            } else {
                dice.bot = graph[x][y];
                graph[x][y] = 0;
            }


        }

        StringJoiner ss = new StringJoiner("\n");
        for(Integer answer: answers) {
            ss.add(String.valueOf(answer));
        }
        System.out.println(ss);
    }

    public static class Dice {
        int top = 0;
        int bot = 0;
        int front = 0;
        int back = 0;
        int left = 0;
        int right = 0;

        public Dice() {}

        public int roll(int direction) {
            if (direction == 0) { // right
                int temp = top;
                top = left;
                left = bot;
                bot = right;
                right = temp;
            } else if (direction == 1) {
                int temp = top;
                top = right;
                right = bot;
                bot = left;
                left = temp;
            } else if (direction == 2) {
                int temp = top;
                top = front;
                front = bot;
                bot = back;
                back = temp;
            } else {
                int temp = top;
                top = back;
                back = bot;
                bot = front;
                front = temp;
            }

            return top;
        }
    }
}