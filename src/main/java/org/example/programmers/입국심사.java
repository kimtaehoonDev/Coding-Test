package org.example.programmers;

import java.util.*;

class 입국심사 {
    public long solution(int n, int[] times) {
        long start = 1L;
        long end = 1_000_000_000L * 10_000 + 1;
        while(start < end) {
            long mid = (start + end) / 2; // mid분
            long completePeopleCnt = calculateCompletePeople(mid, n, times);
            if (completePeopleCnt >= n) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

    public long calculateCompletePeople(long mid, int n, int[] times) {
        long completePeopleCnt = 0L;
        for(int time : times) {
            completePeopleCnt += mid / time;
        }
        // System.out.println(mid + "명 입력되었을 떄 "+ completePeopleCnt);
        return completePeopleCnt;
    }
}