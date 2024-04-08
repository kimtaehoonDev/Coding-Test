package org.example.baekjoon.p1107;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

// 2회 실패 후 해결 / 완전탐색 / 53분(2차까지)+ 재풀이(3분)
// 틀렸던 이유 -> split(0) -> List.of(0)을 만들어야 하는데 이걸 제대로 만들지 못해 두 번 틀림
public class Main2 {

    // 100에서 target을 만들어야 함. 이 때 최소 버튼 클릭 횟수를 구하라
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int target = Integer.parseInt(br.readLine()); // 0<=,<=50만
        int cnt = Integer.parseInt(br.readLine());
        Set<Integer> broken = new HashSet<>();
        if (cnt != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < cnt; i++) {
                broken.add(Integer.parseInt(st.nextToken()));
            }
        }

        int[] dp = new int[1_000_000];
        Arrays.fill(dp, -1);

//        System.out.println("==0");
        // 100에서 출발해서 +, - 만 누른 경우
        for (int i = 0; i < dp.length; i++) {
            dp[i] = Math.abs(100 - i);
        }

        // 숫자만 눌러서 만드는 경우
//        System.out.println("==1");
        for(int i = 0; i<dp.length; i++) {
            boolean canClick = true;
            List<Integer> nums = split(i);
            for (Integer num : nums) {
                if (broken.contains(num)) {
                    canClick = false;
                    break;
                }
            }
            if (canClick) {
                dp[i] = Math.min(dp[i], nums.size());
            }
        }

//        System.out.println("--2");
        int answer = 100000000;
        // 특정 숫자를 만들고 -> +와 -를 누르는 경우
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] == -1) {
                continue;
            }
            answer = Math.min(answer, dp[i] + Math.abs(target - i));
        }
        System.out.println(answer);
    }

    static List<Integer> split(int x) {
        List<Integer> result = new ArrayList<>();
        if (x == 0) {
            result.add(0);
            return result;
        }
        while(x > 0) {
            result.add(x % 10);
            x /= 10;
        }
        return result;
    }
}
