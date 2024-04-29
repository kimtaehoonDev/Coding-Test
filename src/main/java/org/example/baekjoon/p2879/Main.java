package org.example.baekjoon.p2879;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 성공 / 29분 / 그리디
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] originals = new int[N];
        int[] targets = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            originals[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            targets[i] = Integer.parseInt(st.nextToken());
        }
//        System.out.println("원래 " + Arrays.toString(originals));
//        System.out.println("이후 " + Arrays.toString(targets));
        int[] diff = new int[N];
        for (int i = 0; i < N; i++) {
            diff[i] = originals[i] - targets[i];
        }
        System.out.println("차이 " + Arrays.toString(diff));

        int prev = Math.abs(diff[0]);
        int modify = prev;
        for(int i = 1; i<N; i++) {
            if (diff[i -1] * diff[i] > 0) {
                if (Math.abs(diff[i - 1]) < Math.abs(diff[i])) {
                    modify += Math.abs(diff[i] - diff[i - 1]);
                    prev = Math.abs(diff[i]);
//                    System.out.println(i + "번째에서 " + modify);
                }
            } else if(diff[i-1] * diff[i] <= 0) {
                // 부호 다를 때
                prev = Math.abs(diff[i]);
                modify += prev;
            }
        }


        System.out.println(modify);
    }

}

//int modify = 0;
//        for(int i = 0; i<N; i++) { // 최대 80000번 연산
//            if (diff[i] > 0) {
//                // 탭을 한개씩 제거한다.
//                modify += diff[i];
//                while(diff[i] != 0) {
//                    for(int j = i; j<N; j++) {
//                        if (diff[j] <= 0) {
//                            break;
//                        }
//                        diff[j]--;
//                    }
//                }
//            } else if (diff[i] < 0) {
//                // 탭을 한개씩 더한다.
//                modify += (-diff[i]);
//                while(diff[i] != 0) {
//                    for(int j = i; j<N; j++) {
//                        if (diff[j] >= 0) {
//                            break;
//                        }
//                        diff[j]++;
//                    }
//                }
//            }
//            // 0일때는 컨티뉴
//        }