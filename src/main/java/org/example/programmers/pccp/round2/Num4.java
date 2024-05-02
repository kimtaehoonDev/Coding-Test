package org.example.programmers.pccp.round2;

import java.util.PriorityQueue;
import java.util.Queue;

// 실패
class Num4 {

     static int[] dx = new int[] {1,0,-1,0};
     static int[] dy = new int[] {0,1,0,-1};

     public int solution(int N, int M, int[][] hole) {
         boolean[][] graph = new boolean[N][M];
         for(int[] eachHole : hole) {
             graph[eachHole[0] - 1][eachHole[1] - 1] = true; // 장애물 O
         }
         // for(boolean[] line : graph) {
         //     System.out.println(Arrays.toString(line));
         // }

         boolean[][] visited = new boolean[N][M];

         Queue<Node> heap = new PriorityQueue<>((n1, n2) -> {
             if (n1.moving != n2.moving) {
                 return n1.moving - n2.moving;
             }
             // 무빙이 같다면...
             if (n1.used && !n2.used) {
                 // n2를 아직 안썼으니까 우선순위
                 return 1;
             } else {
                 return -1; // n1을 안썼으니까 n1에게 우선순위
             }
         });
         heap.offer(new Node(0,0,0,false, 1000000, false)); // 불가능한 방향
         visited[0][0] = true;


         int answer = -1;
         while(!heap.isEmpty()) {
             Node now = heap.poll();
             if (now.x == N-1 && now.y == M-1) {
                 answer = now.moving;
                 if (now.canUse && !now.used) {
                     answer--;
                 }
                 break;
             }

             for(int d = 0; d<4; d++) {
                 int nx = now.x + dx[d];
                 int ny = now.y + dy[d];
                 if (nx < 0 || ny < 0 || nx >= N || ny >=M) {
                     continue; // out of range
                 }
                 if (visited[nx][ny]) {
                     continue;
                 }

                 if (now.used && graph[nx][ny]) { // 장애물을 만났는데 이미 장화 사용했으면
                     continue;
                 }

                 if (graph[nx][ny]) { // 장애물이라면, now.used == false인 상태
                     // 장화를 사용한다.
                     nx += dx[d];
                     ny += dy[d];
                     if (nx < 0 || ny < 0 || nx >= N || ny >=M) {
                         continue; // out of range
                     }
                     if (visited[nx][ny]) {
                         continue;
                     }
                     if (graph[nx][ny]) {
                         continue;
                     }
                     heap.offer(new Node(nx, ny, now.moving + 1, true, d, false));
                 } else {
                     heap.offer(new Node(nx, ny, now.moving + 1, now.used, d, now.canUse || now.prevDir == d));
                 }
                 visited[nx][ny] = true;
             }
         }

         return answer;
     }

     static class Node {
         int x;
         int y;
         int moving;
         boolean used;
         int prevDir;
         boolean canUse;

         public Node(int x, int y, int moving, boolean used, int prevDir, boolean canUse) {
             this.x = x;
             this.y = y;
             this.moving = moving;
             this.used = used;
             this.prevDir = prevDir;
             this.canUse = canUse;
         }
     }

 }