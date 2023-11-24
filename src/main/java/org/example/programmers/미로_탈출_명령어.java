package org.example.programmers;

public class 미로_탈출_명령어 {

}

//class Solution {
//    static int DOWN_CNT;
//    static int LEFT_CNT;
//    static int RIGHT_CNT;
//    static int UPP_CNT;
//
//    static int[] dx = new int[] {1, 0, 0, -1}; // d, l, r, u
//    static int[] dy = new int[] {0, -1, 1, 0};
//
//    // 최단거리와 k가 홀수개 차이나면 impossible 반환
//    public String solution(int n, int m, int x, int y, int r, int c, int k) {
//        x--;
//        y--;
//        r--;
//        c--;
//        List<Integer> directions = findMinRoutes(n,m,x,y,r,c);
//        // System.out.println(directions);
//        if ((k - directions.size()) % 2 == 1) {
//            // 홀수일 경우 Impossible
//            return "impossible";
//        }
//
//        List<Integer> answerDirections
//
//
//
//        String answer = "";
//        return answer;
//    }
//
//    public int findPair(int x) {
//        if (x == 0) {
//            return 3;
//        }
//        if (x == 1) {
//            return 2;
//        }
//        if (x == 2) {
//            return 1;
//        }
//        return 0;
//    }
//
//    public List<Integer> findMinRoutes(int n, int m, int x, int y, int r, int c) {
//        boolean[][] visited = new boolean[n][m];
//
//        Queue<Spot> spots = new LinkedList<>();
//        spots.add(new Spot(x,y, null, null));
//        visited[x][y] = true;
//        Spot now = null;
//        while(!spots.isEmpty()) {
//            now = spots.poll();
//            if (now.x == r && now.y == c) {
//                // 목표 달성
//                break;
//            }
//            for(int i = 0; i<4; i++) {
//                int nx = now.x + dx[i];
//                int ny = now.y + dy[i];
//                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
//                    //if out of range
//                    continue;
//                }
//                if (visited[nx][ny]) {
//                    continue;
//                }
//                spots.add(new Spot(nx, ny, now, i));
//
//                visited[nx][ny] = true;
//            }
//        }
//        // now는 목적지임
//        List<Integer> directions = new ArrayList<>();
//        Spot temp = now;
//        while(temp.dir != null) {
//            directions.add(temp.dir);
//            temp = temp.prev;
//        }
//        // 뒤집기
//        Collections.reverse(directions);
//        return directions;
//    }
//
//    static class Spot {
//        int x;
//        int y;
//        Spot prev;
//        Integer dir;
//
//        public Spot(int x, int y, Spot prev, Integer dir) {
//            this.x = x;
//            this.y = y;
//            this.prev = prev;
//            this.dir = dir;
//        }
//
//        public Spot(int x, int y) {
//            this.x = x;
//            this.y = y;
//        }
//
//    }
//}
//
//
//        // List<Integer> addedDirections = new ArrayList<>(); // TODO 링크드?
//        // int movingCnt = k - directions.size();
//        // int nowIdx = 0;
//        // Spot now = new Spot(x,y);
//        // for(int i = 0; i<movingCnt/2;i++) {
//        //     for(int dir = 0; dir < 4; dir++) {
//        //         int nx = now.x + dx[i];
//        //         int ny = now.y + dy[i];
//        //         if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
//        //             //if out of range
//        //             continue;
//        //         }
//        //         // 해당 방향으로 이동이 가능하다?
//        //         addedDirections.add(nowIdx++, dir);
//        //         boolean isLast = true;
//        //         for(int j = nowIdx; j<addedDirections.size(); j++) {
//        //             if (dir <= addedDirections.get(j)) {
//        //                 addedDirections.add(j, findPair(dir));
//        //                 isLast = false;
//        //                 break;
//        //             }
//        //         }
//        //         if (isLast) {
//        //             addedDirections.add(findPair(dir));
//        //         }
//        //         now = new Spot(nx, ny);
//        //     }
//        // }
//        // System.out.println(directions);
//        // System.out.println(addedDirections);
