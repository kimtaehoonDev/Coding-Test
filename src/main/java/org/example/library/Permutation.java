package org.example.library;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutation {
    public static void main(String[] args) {
        int[] ary = new int[4];
        for (int i = 0; i < 4; i++) {
            ary[i] = i;
        }
        List<List<Integer>> permutation = permutation(ary, 2);
        for (List<Integer> each : permutation) {
            for (Integer integer : each) {
                System.out.print(integer);
            }
            System.out.println("\n---");
        }
        System.out.println("크기: " + permutation.size());
    }

    // nPr
    public static List<List<Integer>> permutation(int[] ary, int r) {
        List<List<Integer>> answer = new ArrayList<>();
        return permutationHelper(ary, r, 0, 0, answer);
    }

    private static List<List<Integer>> permutationHelper(int[] ary, int r, int depth,
                                                 int pointer, List<List<Integer>> answer) {
        int n = ary.length;
        if (r == depth) {
            List<Integer> result = new ArrayList<>();
            for(int i=0; i<r; i++) {
                result.add(ary[i]);
            }
            answer.add(result);
            return null;
        }

        for (int j = pointer; j < n; j++) {
            int temp = ary[pointer];
            ary[pointer] = ary[j];
            ary[j] = temp;
            permutationHelper(ary, r,depth+1, pointer + 1, answer);
            ary[j] = ary[pointer];
            ary[pointer] = temp;
        }
        return answer;
    }

}
