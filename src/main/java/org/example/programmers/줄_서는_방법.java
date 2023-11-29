package org.example.programmers;

import java.util.*;

// 아이디어는 잘 떠올렸는데 groupCnt를 잘못 계산함
// 예를 들어 원소가 5개고, 첫 원소가 선택되면 각 그룹은 4!만큼 크기가 생기는데 이걸 잘못 계산함
class 줄_서는_방법 {
    public int[] solution(int n, long k) {
        // ary에 1부터 n까지 값을 넣는다
        List<Integer> ary = new LinkedList<>();
        int[] answer = new int[n];
        int idx = 0;
        long groupCnt = 1L;
        for(int i = 1; i<=n; i++) {
            ary.add(i);
            groupCnt *= i;
        }

        k--;

        while(answer[answer.length - 1] == 0) {
            groupCnt /= n;

            int selectedIdx = (int) (k / groupCnt);
            answer[idx++] = ary.get(selectedIdx);
            ary.remove(selectedIdx);


            k %= groupCnt;
            n--;
        }


        return answer;
    }


}