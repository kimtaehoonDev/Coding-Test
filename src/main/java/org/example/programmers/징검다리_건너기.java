package org.example.programmers;

import java.util.*;

class 징검다리_건너기 {
    public int solution(int[] stones, int k) {
        // 1. low, high 구한다
        int low = 1_000_000_000;
        int high = 0;
        int mid = 0;
        for(int stone : stones) {
            low = Math.min(stone, low);
            high = Math.max(stone, high);
        }
        high++;
        // System.out.println(low + " " +  high);

        // mid명의 사람들이 건널 수 있는지 확인한다.
        while(low < high) { // low, mid, high 관리하는 반복문
            mid = (low + high) / 2;
            // System.out.println("mid is " + mid + "can cross " + canCross(mid, stones, k));
            if (canCross(mid, stones, k)) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return low;
    }

    static boolean canCross(int peopleCnt, int[] stones, int k) {
        int now = -1;
        while(now < stones.length) {
            boolean canJump = false;
            for(int i=1; i<=k; i++) {
                if (now + i >= stones.length) {
                    now = stones.length;
                    canJump = true;
                    break;
                }
                if (stones[now + i] - peopleCnt > 0) {
                    // now + i로 건너갈 수 있음
                    now = now + i;
                    canJump = true;
                    break;
                }
            }
            if (!canJump) {
                return false;
            }
        }
        return true;
    }
}