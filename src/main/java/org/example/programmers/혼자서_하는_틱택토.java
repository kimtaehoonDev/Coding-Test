package org.example.programmers;

// 실패 후 아이디어를 참고해서 문제를 풀었습니다.
// 문제를 쉽게 생각했지만 쉽지 않은 문제...

// 문제는 다음과 같은 케이스로 나누어진다

// O가 이긴 상황 :
// X는 정답의 개수가 0개어야함
// O의 개수 == X의 개수 + 1

// X가 이긴 상황 :
// O의 정답 개수는 0개어야함
// O의 개수 == X의 개수

// 이거 외에는 전부 불가능한 Case
public class 혼자서_하는_틱택토 {
    class Solution {
        static int TRUE = 1;
        static int FALSE = 0;

        char[][] board;
        int oAnswer = 0;
        int xAnswer = 0;

        int oCount = 0;
        int xCount = 0;

        public int solution(String[] boardInput) {
            board = new char[boardInput.length][boardInput[0].length()];

            int idx = 0;
            for(String line: boardInput) {
                board[idx++] = line.toCharArray();
                for(char each: board[idx-1]) {
                    if (each == 'O') {
                        oCount++;
                    } else if (each == 'X') {
                        xCount++;
                    }
                }
            }

            // 가로선을 확인한다
            for(int i=0; i<3; i++) {
                if (board[i][0] == '.') {
                    continue;
                }
                if (board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                    if (board[i][0] == 'O') {
                        oAnswer++;
                    } else {
                        xAnswer++;
                    }
                }
            }

            // 세로선을 확인한다
            for(int i=0; i<3; i++) {
                if (board[0][i] == '.') {
                    continue;
                }
                if (board[0][i] == board[1][i] && board[1][i] == board[2][i]) {
                    if (board[0][i] == 'O') {
                        oAnswer++;
                    } else {
                        xAnswer++;
                    }
                }
            }

            // 대각선을 확인한다
            if (board[0][0] != '.') {
                if (board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
                    if (board[0][0] == 'O') {
                        oAnswer++;
                    } else {
                        xAnswer++;
                    }
                }
            }

            if (board[0][2] != '.') {
                if (board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
                    if (board[0][2] == 'O') {
                        oAnswer++;
                    } else {
                        xAnswer++;
                    }
                }
            }


            if (oAnswer == 0 && xAnswer == 0) {
                if (oCount == xCount) {
                    return TRUE;
                }
                if (oCount == xCount + 1) {
                    return TRUE;
                }
                return FALSE;
            }

            if (oAnswer != 0 && xAnswer == 0) {
                if (oCount == xCount + 1) {
                    return TRUE;
                }

                return FALSE;
            }

            if (oAnswer == 0 && xAnswer != 0) {
                if (oCount == xCount) {
                    return TRUE;
                }
                return FALSE;
            }

            return FALSE;
        }
    }
}
