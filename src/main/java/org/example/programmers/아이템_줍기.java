package org.example.programmers;

import java.util.*;

// 아이디어 참조 후 성공
// https://arinnh.tistory.com/88
// 2배를 해주는 이유
// 첫 번째 입출력 예시에서 (3,5) -> (4,5)로 이동해야 함.
// 그런데 우리는 Edge를 저장한게 아니라 Spot을 저장했음. 그렇기 때문에 (3,5) -> (3, 6)으로 점프뛰는 걸 막을 수 없음.
// 둘 사이에 연결되어 있지 않음에도, 노드만으로 그래프를 표현하다보니 생긴 문제.
// 이에 대한 해결책으로 값을 노드들 간 거리를 2배 늘려준다.
// -> (3,5) -> (3.5,5) -> (4,5) 이렇게 이동 가능 but (3,5) -> (3, 5.5)[존재X] 이렇게 점프 불가능해짐.

class 아이템_줍기 {

    static int[][] map;
    static int answer;
    int[][] visited;

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        answer = 0;
        map = new int[101][101];

        for(int i = 0; i<rectangle.length; i++) {
            fill(2 * rectangle[i][0], 2 * rectangle[i][1], 2 * rectangle[i][2], 2 * rectangle[i][3]);
        }

        bfs(2 * characterX, 2 * characterY, 2 * itemX, 2 * itemY);
        return visited[2 * itemX][2 * itemY] / 2;
    }

    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    public void bfs(int startX, int startY, int endX, int endY) {
        visited = new int[101][101];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startX);
        queue.add(startY);

        while(!queue.isEmpty()) {
            int x = queue.poll();
            int y = queue.poll(); // x값 y값을 뺀다

            for(int i = 0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (outOfRange(nx, ny))
                    continue;
                // 테두리가 아니면 이동불가능, 이미 방문했으면 갈 필요 없음
                if (map[nx][ny] != 1 || visited[nx][ny] != 0)
                    continue;

                visited[nx][ny] = visited[x][y] + 1;
                // System.out.println(x +", "+ y + " 위치에서 " + nx + ", " + ny + "로 이동 : " + visited[nx][ny]);
                if (nx == endX && ny == endY) {
                    return;
                }

                queue.add(nx);
                queue.add(ny);
            }
        }
    }

    public boolean outOfRange(int x, int y) {
        return x < 0 || x >= 101 || y < 0 || y >= 101;
    }

    // 만약 사각형 2개가 겹쳤다? 이후에 들어온 사각형의 테두리를 1로 만들어줌. 근데 이미 값이 2(첫번째 사각형의 내부) -> 무시
    public void fill(int x1, int y1, int x2, int y2) {
        for(int i = x1; i<=x2; i++) {
            for(int j = y1; j<=y2; j++) {
                if (map[i][j] == 2)
                    continue;
                map[i][j] = 2;
                if(i == x1 || i == x2 || j == y1 || j == y2) {
                    map[i][j] = 1;
                }
            }
        }
    }
}