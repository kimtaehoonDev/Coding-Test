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
        List<int[]> permutation = permutation(ary, 2);
        for (int[] each : permutation) {
            System.out.println(Arrays.toString(each));
        }
        System.out.println("크기: " + permutation.size());
    }

    // nPr
    public static List<int[]> permutation(int[] ary, int r) {
        List<int[]> answer = new ArrayList<>();
        return permutationHelper(ary, r, 0, 0, answer);
    }

    private static List<int[]> permutationHelper(int[] ary, int r, int depth,
                                                 int pointer, List<int[]> answer) {
        int n = ary.length;
        if (r == depth) {
            int[] result = new int[r];
            for(int i=0; i<r; i++) {
                result[i] = ary[i];
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
