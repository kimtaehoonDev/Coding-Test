package org.example.programmers.pccp.round1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 16분 / 성공 / 순열
// -> 순열 써야 하는데 조합으로 착각해서 시간을 약간 소모함
public class Second {

    static int N;
    static int R;
    static int[] selected;
    static List<int[]> cases = new ArrayList<>();

    public int solution(int[][] ability) {
        N = ability.length;
        R = ability[0].length;
        selected = new int[R];

        perm(0,0);
        // for(int[] each : cases) {
        // System.out.println(Arrays.toString(each));
        // }

        int max = -1;
        for(int[] each : cases) {
            // each의 i번째 종목에 each[i] 출전
            int score = 0;
            for(int i = 0; i<each.length; i++) {
                int person = each[i];
                score += ability[person][i];
            }
            max = Math.max(max, score);
        }

        return max;
    }

    public void perm(int depth, int flag) {
        if (depth == R) {
            // TODO
            cases.add(Arrays.copyOfRange(selected, 0, R));
            return;
        }
        for(int i = 0; i<N; i++) {
            if ((flag & (1<<i)) != 0) // 이미 i번째 뽑혀있을때
                continue;
            // i번째 원소를 depth 위치에 넣는다
            selected[depth] = i;
            perm(depth+1, flag | 1<<i);
        }
    }

}
