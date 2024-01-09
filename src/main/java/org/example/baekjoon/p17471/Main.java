package org.example.baekjoon.p17471;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

// 게리맨더링
// 실패, 추후에 다시 풀기 / 16퍼에서 틀리는데 원인을 알 수 없음
// 조합으로 모든 경우의 수를 구함
// gap이 작은게 우선순위를 갖는 큐에 경우의 수들을 입력
// 순회를 돌며, 해당 조합이 가능한 조합인지 확인(묶음이 두 개 나오는지)
// 만약 모든 케이스를 확인해도 정답이 없다 -> -1 출력

// 로직은 잘 만든거 같은데 어디에서 틀린지 알 수 없다.
// 사람들이 올려준 모든 반례에 성공. 아이디어를 찾아봐도 틀린 게 없음

public class Main {

    static int N;
    static boolean[][] graph;
    static int[] costs;

    public static void main(String[] args) throws IOException {
        int answer = -1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        costs = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
        }
        graph = new boolean[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for (int j = 0; j < cnt; j++) {
                // i 와 connected 연결
                int connected = Integer.parseInt(st.nextToken());
                graph[i][connected] = true;
//                graph[connected][i] = true;
            }
        }
//        for (boolean[] booleans : graph) { // 검증
//            System.out.println(Arrays.toString(booleans));
//        }
        Queue<Case> heap = new PriorityQueue<>((c1, c2) -> {
            return c1.gap - c2.gap;
        });

        // costs, graph 초기화
        // costs : N+1크기 int 배열 , costs[i] 는 i번째 인구수
        // graph는 [N+1][N+1] int 2차원배열, graph[i][j] 는 연결되어있는지 여부
        // heap은 gap이 작은 순서대로 반환함.
        for (int bit = 1; bit < Math.pow(2, N) - 1; bit++) {
            // bit를 2진수로
            heap.add(makeCaseUsingBit(bit));
        }
        while (!heap.isEmpty()) {
            Case eachCase = heap.poll();
            int[] parents = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                parents[i] = i;
            }
            boolean[] bits = eachCase.bits;
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
//                    System.out.println("input" + i + ", " + j);
                    if (!graph[i][j]) {
                        continue;
                    }
                    if (bits[i - 1] != bits[j - 1]) {
                        continue;
                    }
//                    System.out.println("output" + i + ", " + j);
                    // 연결되어 있으면 부모 병합
                    parents[i] = Math.max(parents[i], parents[j]);
                    parents[j] = Math.max(parents[i], parents[j]);
                }
            }
            for (int i = 1; i <= N; i++) {
                findParent(i, parents);
            }

            Set<Integer> set = new HashSet<>();
            for (int each : parents) {
                if (each == 0) {
                    continue;
                }
                set.add(each);
            }

//            System.out.println("@@@");
//            System.out.println(eachCase);
//            System.out.println(Arrays.toString(parents));
//            System.out.println("원소 개수" + set.size());
//            System.out.println("===");
            if (set.size() == 2) { // 0 이랑 두 개 영역
                answer = eachCase.gap;
                break;
            }
        }

        System.out.println(answer);
    }

    static int findParent(int i, int[] parents) {
        if (i != parents[i]) {
            parents[i] = findParent(parents[i], parents);
        }
        return parents[i];
    }

    static Case makeCaseUsingBit(int bit) {
        boolean[] bits = new boolean[N]; // true면 1, false면 0
        int idx = 0;
        while (bit > 0) {
            bits[idx++] = (bit % 2) == 1;
            bit /= 2;
        }
        idx = 0;
        int gap = 0;
        for (boolean each : bits) {
            if (each) { // 1일 경우
                gap += costs[++idx];
            } else {
                gap -= costs[++idx];
            }
        }
        return new Case(Math.abs(gap), bits);
    }

    static class Case {

        int gap;
        boolean[] bits;

        public Case(int gap, boolean[] bits) {
            this.gap = gap;
            this.bits = bits;
        }

        //        @Override
//        public String toString() {
//            return "[gap = " + gap + ", bits = " + Arrays.toString(bits) + "]";
//        }
    }
}
