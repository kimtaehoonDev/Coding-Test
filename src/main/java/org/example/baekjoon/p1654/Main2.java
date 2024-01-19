package org.example.baekjoon.p1654;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 11분 -> 범위 안살펴서 한 번 틀림
public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        long[] lans = new long[N];
        for(int i = 0; i<N; i++) {
            lans[i] = Long.parseLong(br.readLine());
        }
        Arrays.sort(lans);
//        System.out.println(Arrays.toString(lans));

        long low = 0;
        long high = lans[lans.length - 1] + 1; // 백만
        while(low < high) {
            long mid = (low + high) / 2;
            int cnt = countSeparatedLan(lans, mid);
            if (cnt < K) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        System.out.println(low - 1);
    }

    public static int countSeparatedLan(long[] lans, long mid) {
        long cnt = 0;
        for(long lan : lans) {
            cnt += lan / mid;
        }
        return (int) cnt;
    }
}
