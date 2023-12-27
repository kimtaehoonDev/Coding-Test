package org.example.baekjoon.p14719;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 구현 / 풀이 참고 후 성공
// 초기 접근 방식이 잘못되었음. 조금 더 설계를 탄탄하게 해야 함. 요즘 설계가 잘 안되는 느낌이 있으니 조금 신경써서 해야할 듯
public class Main {
    public static void main(String[] args) throws IOException {
        // 입력을 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] blocks = new int[W];
        int[] lefts = new int[W];
        int[] rights = new int[W];
        for(int i = 0; i < W; i++) {
            blocks[i] = Integer.parseInt(st.nextToken());
        }
//        System.out.println(Arrays.toString(blocks));

        // 각 지점에서 양 끝으로 이동한다. 현재 블록에서 물이 몇 칸 차야하는지 연산한다
        int high = -1;
        for(int i = 0; i<W; i++) {
            if (high < blocks[i]) {
                high = blocks[i];
            }
            lefts[i] = high;
        }
        high = blocks[W - 1];
        for(int i = W -1; i>=0; i--) {
            if (high < blocks[i]) {
                high = blocks[i];
            }
            rights[i] = high;
        }
//        System.out.println(Arrays.toString(lefts));
//        System.out.println(Arrays.toString(rights));
        int total = 0;
        for(int i = 0; i<W; i++) {
            total += (Math.min(lefts[i], rights[i]) - blocks[i]);
        }
        System.out.println(total);
    }
}
