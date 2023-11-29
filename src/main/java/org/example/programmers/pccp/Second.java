package org.example.programmers.pccp;

import java.util.*;

// 성공 / 30분
class Second {
    static int[][] land;
    static Map<Integer, Integer> amountInLand = new HashMap<>();
    static int[][] visited;
    static int idx = 1;
    static int[] dx = new int[] {1,0,-1,0};
    static int[] dy = new int[] {0,1,0,-1};

    public int solution(int[][] land) {
        this.land = land;
        this.visited = new int[land.length][land[0].length];

        for(int i=0; i<land.length; i++) {
            for(int j=0; j<land[0].length; j++) {
                if (land[i][j] == 0) {
                    // 석유가 없는 땅
                    continue;
                }
                if (visited[i][j] != 0) {
                    // 이미 방문한 땅
                    continue;
                }

                // 방문안한경우 && 석유도 있는 경우
                int amount = bfs(i,j);
                amountInLand.put(idx++, amount);
            }
        }

        // for(Integer each : amountInLand.keySet()) {
        // System.out.println(each + "each" + amountInLand.get(each));
        // }

        int answer = 0;
        for(int j=0; j<land[0].length; j++) {
            Set<Integer> alreadyPoll = new HashSet<>();
            int total = 0;
            for(int i=0; i<land.length; i++) {
                if (land[i][j] == 0) {
                    continue;
                }
                if (alreadyPoll.contains(visited[i][j])) {
                    continue;
                }

                // 석유가 있어 && 지금 시추한적도 없어
                int amount = amountInLand.get(visited[i][j]);
                alreadyPoll.add(visited[i][j]);
                total += amount;
            }
            // System.out.println(j + "위치에서는 " + total + " 만큼 팜. 그리고 " + alreadyPoll + "에서 채취");
            answer = Math.max(total, answer);
        }

        return answer;
    }

    public int bfs(int x, int y) {
        int cnt = 1;
        Queue<Spot> queue = new LinkedList<>();
        queue.add(new Spot(x,y));
        visited[x][y] = idx;

        while(!queue.isEmpty()) {
            Spot spot = queue.poll();
            for(int i=0; i<4; i++) {
                int nx = spot.x + dx[i];
                int ny = spot.y + dy[i];
                if (nx < 0 || ny < 0 || visited.length <= nx || visited[0].length <= ny) {
                    continue;
                }
                if (visited[nx][ny] == 0 && land[nx][ny] == 1) {// 방문한적 없고, 석유가 있는 땅이면
                    queue.offer(new Spot(nx, ny));
                    cnt++;
                    visited[nx][ny] = idx;
                }
            }
        }
        return cnt;
    }

    static class Spot {
        int x;
        int y;

        public Spot(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}