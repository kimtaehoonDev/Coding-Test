package org.example.baekjoon.p20366;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

// 실패
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] snowballs = new int[N];
        // 입력을 받는다
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<N; i++) {
            int x = Integer.parseInt(st.nextToken());
            snowballs[i] = x;
        }

        // 정렬한다 // TODO int[] Comparator로 정렬하는법, 뒤집는법
        Arrays.sort(snowballs);
//        System.out.println(Arrays.toString(snowballs));

        // nC3 고른다
//        int[][] result = new int[(N * (N-1) * (N-2)) / 6][3];
        List<int[]> result = new ArrayList<>();
        comb(N, 3, 0, 0, result, new int[3]);
//        for (int[] ints : result) {
//            System.out.println(Arrays.toString(ints));
//        }

        // 3. 순회하며 a,b,c 인덱스에 대해 ary[b] + ary[c] , ary[a], ary[c+1]의 gap을 구한다.
        //    그걸 통해 gap의 최소를 구한다
        int min = Integer.MAX_VALUE;
        for(int[] eachCase : result) {
            if (eachCase[2] == N - 1) {
                continue;
            }
            int snowman1Length = snowballs[eachCase[1]] + snowballs[eachCase[2]]; // 최대 20억
            int snowman2Length = snowballs[eachCase[0]] + snowballs[eachCase[2] + 1];
            int gap = Math.abs(snowman1Length - snowman2Length);

//            System.out.println(min + "/" + gap);
            min = Math.min(gap, min);
        }
        System.out.println(min);

    }

    static void comb(int n, int r, int start, int depth, List<int[]> result, int[] stored) {
        if (depth == r) {
            result.add(Arrays.copyOfRange(stored, 0, stored.length));
            return;
        }
        for(int i = start; i<n; i++) {
            stored[depth] = i;
            comb(n, r, i + 1, depth + 1, result, stored);
        }
    }
}
