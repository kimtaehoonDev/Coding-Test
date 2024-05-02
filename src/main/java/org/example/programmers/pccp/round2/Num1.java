package org.example.programmers.pccp.round2;
import java.util.*;

// 성공 / 9분 / 구현
class Num1 {

    static int[] dx = new int[] {0,1,0,-1}; //상우하좌
    static int[] dy = new int[] {1,0,-1,0}; // (0,1) (1,0) (0,-1) (-1 0)

    public int[] solution(String command) {
        char[] cmds = command.toCharArray();
        int[] rocket = new int[] {0,0};
        int dir = 0; // 위를 바라본다.
        for(char cmd : cmds) {
            if (cmd == 'R') {
                dir = (dir + 1) % 4;
            } else if (cmd == 'L') {
                dir = (dir + 3) % 4;
            } else if (cmd == 'G') {
                rocket[0] += dx[dir];
                rocket[1] += dy[dir];
            } else { // B
                rocket[0] -= dx[dir];
                rocket[1] -= dy[dir];
            }
            // System.out.println("로켓 위치 + " + Arrays.toString(rocket));
        }

        return rocket;
    }
}