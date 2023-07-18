package org.example.library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combination {
    public static void main(String[] args) {
        int[] ary = new int[5];
        for(int i = 0 ; i < 5; i++) {
            ary[i] = i;
        }
        List<boolean[]> combination = combination(ary, 3);
        for (boolean[] each : combination) {
            System.out.println(Arrays.toString(each));
        }
        System.out.println("크기: " + combination.size());
    }

    // nCr
    public static List<boolean[]> combination(int[] ary, int r) {
        int n = ary.length;
        if (n <= r) {
            throw new IllegalArgumentException("r은 배열의 크기보다 작거나 같아야 합니다");
        }
        if (r < 0) {
            throw new IllegalArgumentException("r은 0 이상의 수여야 합니다");
        }

        List<boolean[]> cases = new ArrayList<>();
        boolean[] isChecked = new boolean[n];
        combinationHelper(n, r, cases, isChecked, 0, 0);

        return cases;
    }

    /**
     * 메서드가 한 번 실행될 때마다 depth를 1 늘린다
     * isChecked에 depth개의 원소가 뽑혀있다
     * start 이전까지 원소들은 고려하지 않고, start 이상 모든 ary의 원소들을 뽑고 다음 depth로 이동한다
     */
    private static void combinationHelper(int n, int r, List<boolean[]> results,
                                   boolean[] isChecked, int depth, int start) {
        if (depth == r) {
            results.add(Arrays.copyOfRange(isChecked, 0, isChecked.length));
            return;
        }
        // Index 범위 벗어남
        if (start >= n) {
            return;
        }

        // start부터 배열의 마지막 원소까지 하나씩 뽑는다
        for(int i=start; i<n; i++) {
            isChecked[i] = true;
            combinationHelper(n, r, results, isChecked, depth+1, i+1);
            isChecked[i] = false;
        }
    }
}
