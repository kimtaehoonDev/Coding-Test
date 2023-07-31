package org.example.leetcode.array;

import java.util.Arrays;

class SquaresOfASortedArray {
    public int[] sortedSquares(int[] nums) {
        int[] squares = new int[nums.length];
        int i = 0;
        for(int num: nums) {
            squares[i++] = num * num;
        }
        Arrays.sort(squares);

        return squares;
    }
}