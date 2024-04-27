package org.example.baekjoon.p20437;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringJoiner;

// 40분 / 실패
// 시간복잡도 딱 1억 나오길래 되겠다 싶었음.
// 그런데 T횟수만큼 반복해야 해서 시간초과 나오는듯.. 다른 풀이로 풀 예정
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        for (int i = 0; i < T; i++) {
            char[] str = br.readLine().toCharArray();
            int K = Integer.parseInt(br.readLine());
            int[] answers = solve(str, K);
            if (answers[0] == Integer.MAX_VALUE) {
                sj.add("-1");
            } else {
                sj.add(answers[0] + " " + answers[1]);
            }
        }
        System.out.println(sj.toString());
    }

    static int[] solve(char[] str, int K) {
        int[] alphas;
        int min = Integer.MAX_VALUE;
        int max = -1;
        for (int i = 0; i < str.length; i++) {
            alphas = new int[26];
            boolean thirdCondition = false;
            int cnt = 0;
            for (int j = i; j < str.length; j++) {
                int seqInAlpha = str[j] - 'a';
                alphas[seqInAlpha] += 1;
                if (alphas[seqInAlpha] == K) {
                    cnt++;
                    thirdCondition = true;
                } else if (alphas[seqInAlpha] > K) {
                    cnt--;
                    if (cnt == 0) {
                        thirdCondition = false;
                    }
                }
                if (thirdCondition) {
                    min = Math.min(min, j - i + 1);
//                    System.out.println(String.format("최소값은 %s(%d ~ %d)", Arrays.toString(Arrays.copyOfRange(str, i, j+1)), i, j));
                    if (str[i] == str[j] && alphas[seqInAlpha] == K) {
                        max = Math.max(max, j - i + 1);
//                        System.out.println(String.format("@@최대값은 %s(%d ~ %d)", Arrays.toString(Arrays.copyOfRange(str, i, j+1)), i, j));
                    }
                }

            }
        }
        int[] answers = new int[2];
        answers[0] = min;
        answers[1] = max;
        return answers;
    }

}
