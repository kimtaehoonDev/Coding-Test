package org.example.baekjoon.p9663;

import java.util.Arrays;
import java.util.Scanner;

// 핵심 아이디어는 한 row에 하나의 Queen만 위치할 수 있다는 것이다.
// 즉, 1차원 배열만을 사용해서 2차원에 존재하는 Queen들의 위치를 표현할 수 있다
// cols[0] = 1 의 의미는 Row 0, Col 1에 Queen이 존재한다는 뜻

public class Main {
    public static int answer = 0;
    public static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int[] cols = new int[N]; // 인덱스는 rows
        Arrays.fill(cols, -1);
        nQueens(0, cols);
        System.out.println(answer);
    }

    // queens의 인덱스 : x, queens의 값: y값
    public static void nQueens(int targetRow, int[] cols) {
        if (targetRow == N) {
            answer++;
            return;
        }

        // queens
        for(int targetCol=0; targetCol<N; targetCol++) {
            cols[targetRow] = targetCol;
            if (canAttack(targetRow, cols)) {
                continue;
            }
            cols[targetRow] = targetCol;
            nQueens(targetRow + 1, cols);
            cols[targetRow] = -1;
        }
    }

    // x위치에 y를 넣어줄거다
    public static boolean canAttack(int targetRow, int[] cols) {
        // 다른 Queen들과 비교한다(targetRow 앞까지가 현재 선택된 케이스)
        for (int i = 0; i < targetRow; i++) {
            if (cols[targetRow] == cols[i]) {
                return true;
            }
            if (Math.abs(targetRow - i) == Math.abs(cols[targetRow] - cols[i])) {
                return true;
            }
        }
        return false;
    }

}
