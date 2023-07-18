package org.example.programmers;

import java.util.Arrays;
import java.util.Comparator;

public class 요격_시스템 {
//    https://school.programmers.co.kr/learn/courses/30/lessons/181188

//    [[0, 100000000], [4, 5], [4, 8], [10, 14], [11, 13], [5, 12], [3, 7], [1, 4]]
//    answer: 3

//    [[4, 5], [4, 8], [10, 14], [11, 13], [5, 12], [3, 7], [1, 4]]
//    answer: 3
    class Solution {
        public int solution(int[][] targets) {
            Arrays.sort(targets, new Comparator<>() {
                public int compare(int[] t1, int[] t2) {
                    return t1[0] - t2[0];
                }
            });

            int low = -1;
            int high = 200_000_000;
            int points = 1;
            for(int[] target: targets) {
                // System.out.println(Arrays.toString(target));
                if (high <= target[0]) {
                    points += 1;
                    low = target[0];
                    high = target[1];
                } else {
                    low = Math.max(target[0], low);
                    high = Math.min(target[1], high);
                }
            }

            return points;
        }
    }
}
