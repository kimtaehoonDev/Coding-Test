package org.example.leetcode;

import java.util.ArrayList;
import java.util.List;

//    https://leetcode.com/problems/generate-parentheses/?envType=study-plan-v2&envId=top-100-liked
// 원래 풀었던 방법보다 더 효율적인 방법을 찾아 풀이 변경
class p22 {

    static List<String> answers;

    public List<String> generateParenthesis(int n) {
        answers = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        sb.append('(');
        make(sb, n - 1, n);

        return answers;
    }

    // openCnt: 사용가능한 남아있는 ( 개수
    // closeCnt: 사용가능한 남아있는 ) 개수
    public void make(StringBuilder sb, int openCnt, int closeCnt) {
        if (openCnt == 0 && closeCnt == 0) {
            answers.add(sb.toString());
            return;
        }
        // 열림 괄호를 넣는다
        if (openCnt != 0) {
            sb.append('(');
            make(sb, openCnt - 1, closeCnt);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (openCnt < closeCnt) {
            sb.append(')');
            make(sb, openCnt, closeCnt - 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

