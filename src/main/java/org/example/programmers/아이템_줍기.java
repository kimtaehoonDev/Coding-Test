package org.example.programmers;

import java.util.*;

// 실패 / BFS / 1시간 13분
// 풀이를 잘못 생각함. 사각형이 겹쳐있을 때 외부로만 이동 가능한데, 내 풀이에서는 내부 선을 타고 이동 가능

class 아이템_줍기 {
    static final int LEFT = 0;
    static final int BOT = 1;
    static final int RIGHT = 2;
    static final int TOP = 3;
    static Map<Integer, Set<Integer>> graph = new HashMap<>();
    static int[] visited = new int[2500];

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = -1;

        for(int[] each : rectangle) {
            for(int i = each[BOT]; i<each[TOP]; i++) {
                int x1 = calcIdx(each[LEFT], i);
                int y1 = calcIdx(each[LEFT], i+1);
                if (!graph.containsKey(x1)) {
                    graph.put(x1, new HashSet<>());
                }
                graph.get(x1).add(y1);
                if (!graph.containsKey(y1)) {
                    graph.put(y1, new HashSet<>());
                }
                graph.get(y1).add(x1);

                x1 = calcIdx(each[RIGHT], i);
                y1 = calcIdx(each[RIGHT], i+1);
                if (!graph.containsKey(x1)) {
                    graph.put(x1, new HashSet<>());
                }
                graph.get(x1).add(y1);
                if (!graph.containsKey(y1)) {
                    graph.put(y1, new HashSet<>());
                }
                graph.get(y1).add(x1);
            }
            for(int i = each[LEFT]; i<each[RIGHT]; i++) {
                int x1 = calcIdx(i, each[BOT]);
                int y1 = calcIdx(i+1, each[BOT]);
                if (!graph.containsKey(x1)) {
                    graph.put(x1, new HashSet<>());
                }
                graph.get(x1).add(y1);
                if (!graph.containsKey(y1)) {
                    graph.put(y1, new HashSet<>());
                }
                graph.get(y1).add(x1);

                x1 = calcIdx(i, each[TOP]);
                y1 = calcIdx(i+1, each[TOP]);
                if (!graph.containsKey(x1)) {
                    graph.put(x1, new HashSet<>());
                }
                graph.get(x1).add(y1);
                if (!graph.containsKey(y1)) {
                    graph.put(y1, new HashSet<>());
                }
                graph.get(y1).add(x1);
            }
        }
        // 그래프 생성 끝
        // System.out.println(graph);

        // start -> end까지 도착하는 최단거리
        int start = calcIdx(characterX, characterY);
        int end = calcIdx(itemX, itemY);

        Queue<int[]> queue = new PriorityQueue<>(new Comparator<>() {
            @Override
            public int compare(int[] a1, int[] a2) {
                return a1[1] - a2[1];
            }
        });
        queue.add(new int[] {start, 0}); // start까지 0번 이동
        visited[start] = 0;

        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            if (now[0] == end) {
                answer = now[1];
                break;
            }
            for(Integer next : graph.get(now[0])) {
                if (visited[next] != 0) {
                    continue;
                }
                queue.add(new int[] {next, now[1] + 1});
                visited[next] = visited[now[0]] + 1;
            }
        }

        return answer;
    }

    static int calcIdx(int x, int y) {
        return 50 * (x-1) + (y-1);
    }
}