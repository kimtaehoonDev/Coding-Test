package org.example.programmers.pccp.round1_try2;

import java.util.*;

// 성공 / 14분 / 순열
class Num2 {

    static List<int[]> cases = new ArrayList<>();

    public int solution(int[][] ability) {
        int studentCnt = ability.length;
        int exerciseCnt = ability[0].length;
        perm(studentCnt, exerciseCnt, 0, 0, new int[exerciseCnt]);
        // for(int[] eachCase : cases) {
        //     System.out.println(Arrays.toString(eachCase));
        // }
        // System.out.println(cases.size());

        int max = 0;
        for(int[] eachCase : cases) {
            int score = 0;
            for(int i = 0; i<eachCase.length; i++) {
                // i번째 종목에서 eachCase[i] 학생이 뽑힘
                score += ability[eachCase[i]][i];
            }
            // System.out.println(Arrays.toString(eachCase) + "-> 점수 : " + score);
            max = Math.max(max, score);
        }

        return max;
    }

    static void perm(int N, int R, int depth, int flag, int[] store) {
        if (depth == R) {
            cases.add(Arrays.copyOfRange(store, 0, store.length));
            return;
        }

        for(int i = 0; i<N; i++) {
            if ((flag & 1<<i) != 0) { // i번째 원소를 이미 뽑았다면
                continue;
            }
            store[depth] = i;
            perm(N, R, depth + 1, (flag | 1<<i), store);
        }
    }
}