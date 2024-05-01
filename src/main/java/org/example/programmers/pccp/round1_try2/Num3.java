package org.example.programmers.pccp.round1_try2;

import java.util.*;

// 성공 / 33분 / 재귀
class Num3 {
    public String[] solution(int[][] queries) {
        String[] answer = new String[queries.length];
        int idx = 0;
        for(int[] query : queries) {
            query[1] -= 1;
            String result = recur(query);
            answer[idx++] = result;
        }

        return answer;
    }

    static int GENERATION = 0;
    static int ORDER = 1;

    String recur(int[] query) {
        if (query[GENERATION] == 1) {
            return "Rr";
        }

        int childCnt = query[ORDER] - (query[ORDER]/4) * 4;
        String parent = recur(new int[] {query[GENERATION] - 1, query[ORDER] / 4});
        // System.out.println(Arrays.toString(query) + "의 부모는 " + parent +", 자식 번호는 " + childCnt);
        if (parent.equals("RR") || parent.equals("rr")) {
            return parent;
        }
        if (childCnt == 0) {
            return "RR";
        } else if (childCnt == 3) {
            return "rr";
        } else {
            return "Rr";
        }
    }
}