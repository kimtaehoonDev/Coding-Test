package org.example.baekjoon.p2343;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 실패 / 반례 통해 찾음 / 풀이 시간 33분 / 고민 시간 : 1시간
// calcBluerayCnt에 문제가 있다는 건 알았지만(직감), 왜 문제인지는 찾지 못함
// 질문 게시판에 반례를 보고 나서야 원인을 알게 됨
// low값은 0이나 1이 아님. 가장 큰 강의의 크기임
// 블루레이 크기가 강의보다 작으면 그건 영영 넣을 수 없어서 이분탐색이 꼬이게 됨

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken()); // 기준값

        st = new StringTokenizer(br.readLine());
        int[] lectures = new int[N];
        int total = 0;
        int max = 0;
        for(int i = 0; i<N; i++) {
            lectures[i] = Integer.parseInt(st.nextToken());
            total += lectures[i];
            max = Math.max(lectures[i], max);
        }
//        System.out.println(Arrays.toString(lectures));

        int low = max;
        int high = total + 1;

        while(low < high) {
            int mid = (low + high)/2;
            int cnt = calcBluerayCnt(lectures, mid);
//            System.out.println(mid + "크기이면 개수 -> " + cnt);
            if (cnt > M) {
                // 개수 줄여야함 -> 블루레이 크기 키우기
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        System.out.println(low);
    }

    static int calcBluerayCnt(int[] lectures, int size) {
        int cnt = 0;
        int temp = 0;
        for(int each : lectures) {
            if (temp + each <= size) {
                temp += each;
            } else {
                temp = each;
                cnt++;
            }
        }
        if (temp != 0) {
            cnt++;
        }
        return cnt;
    }

}
