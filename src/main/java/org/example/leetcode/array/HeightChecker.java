package org.example.leetcode.array;

import java.util.Arrays;

public class HeightChecker {

    public int heightChecker(int[] heights) {
        int[] expected = Arrays.copyOfRange(heights, 0, heights.length);
        Arrays.sort(expected);

        int i = 0;
        int cnt = 0;
        for (int x : expected) {
            if (x != heights[i++]) {
                cnt += 1;
            }
        }

        return cnt;
    }
}
