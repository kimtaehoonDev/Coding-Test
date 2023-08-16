package org.example.baekjoon.p15684;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 첫 번째 풀이가 끝나는 데에만 50분 소요됨(한 가지 예외를 잡지 못함)
// 게다가 사다리 맨 밑에줄에서 <-> 가로로 움직일 수 있다는 걸 간과함
// 디버깅을 통해서 찾았는데, 실제 문제였다면 풀지 못했을 듯...
public class Main {
    public static int N; // 세로선의 개수
    public static int M; // 설치할거 개수
    public static int H; // 가로선 위치 개수
    public static boolean[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        // i -> j로 갈 수 있다
        graph = new boolean[N*H][N*H];

        // 세로선을 긋는다
        for(int i=0; i< N; i++) {
            for(int j=0; j< H; j++) {
                if (j + 1 >= H) {
                    continue;
                }
                graph[i+ j * N][i + (j+1) * N] = true;
            }
        }


        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            graph[a * N + b][a * N + b + 1] = true;
            graph[a * N + b + 1][a * N + b] = true;
        }



//        // 로그성
//        for(int i=0; i<N*H; i++) {
//            System.out.println(Arrays.toString(graph[i]));
//        }
//        //로그성끝

        int answer = -1;

        for(int i=0; i<=3;i++) {
            boolean result = solve(i);
            if (result) {
                answer = i;
                break;
            }
        }
        System.out.println(answer);
    }

    // cnt 개 설치해서 답이 나오는가?
    public static boolean solve(int cnt) {
        if (cnt == 0) {
            return check();
        }
        // 조합으로 구하기
        List<int[]> cases = new ArrayList<>();
        comb(N*H, cnt, 0, 0, cases, new int[cnt]);
//        System.out.println(cases.size());


        //설치하기
        for(int[] eachCases: cases) {
            boolean canMake = true;
            for(int each: eachCases) {
                // 이미 설치되어 있으면
                if (graph[each][each + 1]) {
                    canMake = false;
                    break;
                }
                if ((each %N)!= 0 && graph[each-1][each]) {
                    canMake = false;
                    break;
                }

                if ((each%N)!= (N-2) && graph[each+1][each+2]) {
                    canMake = false;
                    break;
                }
            }

            if (!canMake) {
                continue;
            }

            // 만들수 있으면 설치
            for(int each: eachCases) {
                graph[each][each + 1] = true;
                graph[each+1][each] = true;
            }
            boolean result = check();
            if (result) {
                return true;
            }

            for(int each: eachCases) {
                graph[each][each + 1] = false;
                graph[each+1][each] = false;
            }
        }

        return false;
    }

    public static boolean bfs(int start) {
        boolean alreadyMoveGaro = false;
        int now = start;
        while (true) {
            if (!alreadyMoveGaro && now != 0 && graph[now][now - 1]) {
                now -= 1;
                alreadyMoveGaro = true;
                continue;
            }
            if (!alreadyMoveGaro && now + 1 != N*H && graph[now][now + 1]) {
                now += 1;
                alreadyMoveGaro = true;
                continue;
            }

            if (now / N == H - 1) {
                if (!alreadyMoveGaro) {

                }
                return start == now % N;
            }

            if (graph[now][now + N]) {
                now += N;
                alreadyMoveGaro = false;
                continue;
            }
            // 무한루프 방지
            return false;
        }

    }

    public static boolean check() {
        // 그래프에서 갈 수 있는지 확인하기
        for(int i=0; i<N;i++) { // 각 점에서 출발
            //밑으로 ~ 옆으로~
            boolean result = bfs(i);
            if (!result) {
                return false;
            }
        }
        return true;
    }

    public static void comb(int n, int r, int depth, int start, List<int[]> answer, int[] output) {
        if (depth == r) {
            answer.add(Arrays.copyOfRange(output, 0, output.length));
            return;
        }

        for(int i=start; i<n; i++) {
            if ((i+ 1) % N == 0) {
                continue;
            }
            output[depth] = i;
            comb(n, r, depth + 1, i + 1, answer, output);
            output[depth] = 0; //초기화 (필요없는 작업)
        }
    }


}
