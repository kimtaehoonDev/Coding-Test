package org.example.leetcode.array;

import java.util.ArrayList;
import java.util.List;

class FindAllNumbersDisappeared {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> answer = new ArrayList<>();
        boolean[] visited = new boolean[nums.length+1];

        for(int num: nums) {
            visited[num] = true;
        }

        for (int i = 1; i< visited.length; i++) {
            if (!visited[i]) {
                answer.add(i);
            }
        }

        return answer;
    }
}