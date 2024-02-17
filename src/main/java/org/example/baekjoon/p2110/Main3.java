package org.example.baekjoon.p2110;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 성공 / 17분
public class Main3 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] locations = new int[N];
        for(int i = 0; i<N; i++) {
            locations[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(locations);

        int low = 1;
        int high = locations[locations.length - 1] - locations[0] + 1;
        while (low < high) {
            int mid = (low + high) / 2;
            int x = countMachine(mid, locations);
            if (x < C) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        System.out.println(low - 1);
    }

    static int countMachine(int gap, int[] locations) {
        int count = 1;
        int prev = locations[0];
        for(int i = 1; i<locations.length; i++) {
            if (locations[i] - prev < gap) {
                continue;
            }
            prev = locations[i];
            count++;
//            System.out.println(prev);
        }
//        System.out.println(gap + "일 때, " + count);
        return count;
    }
}
