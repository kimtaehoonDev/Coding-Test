package org.example.baekjoon.p20437;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

// 40분 / 실패
// 시간복잡도 딱 1억 나오길래 되겠다 싶었음.
// 그런데 T횟수만큼 반복해야 해서 시간초과 나오는듯.. 다른 풀이로 풀 예정

// 14분 / 성공 / 구현
// 앞뒤가 똑같은 경우만 확인해주면 되는 문제.
// -> 어떤 문자를 정확히 K개 포함 && 가장 짧은 연속 문자열은 x~~~x 이렇게 나올 수밖에 없음. x~~~xa 문자가 있다면, x라는 문자 개수는 똑같아도 길이가 더 길어진 경우
// -> 어떤 문자를 정확히 K개 포함 && 문자열 첫번째, 마지막 글자가 같은 경우는 마찬가지로 x~~x밖에 불가능(조건에서 앞뒤 같다고 명시)
// 풀이
// 1. 문자열 W에서 각각의 문자가 몇 번째 인덱스에 위치하는지 기록한다.
//    a : [2, 4, 5, 6] 이라면 a는 2번째, 4번째, 5번째, 6번째에 위치한다는 것
//    이 때, a가 3개 포함되는 경우는 2, 4, 5번째를 포함한 경우 -> 만들어지는 문자열은 2~5 까지 길이
//    해당 경우들의 최솟값, 최댓값을 기록하면 정답이 나온다.
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
        List<List<Integer>> totalIdxes = new ArrayList<>();
        for(int i = 0; i<26; i++) {
            totalIdxes.add(new ArrayList<>());
        }
        for(int i = 0; i<str.length; i++) {
            totalIdxes.get(str[i] - 'a').add(i);
        }
//        int z = 0;
//        for (List<Integer> idx : idxes) {
//            System.out.println((char) (z++ + 'a'));
//            System.out.println(idx);
//        }

        int min = Integer.MAX_VALUE;
        int max = 0;
        for(List<Integer> idxes : totalIdxes) {
            if (idxes.size() < K) {
                continue;
            }
            for(int i = 0; i<idxes.size(); i++) {
                // i부터 i+K-1 원소 포함
                if ((i + K - 1) >= idxes.size()) {
                    break;
                }
                int length = idxes.get(i + K - 1) - idxes.get(i) + 1;
                min = Math.min(min, length);
                max = Math.max(max, length);
            }
        }
        int[] answers = new int[2];
        answers[0] = min;
        answers[1] = max;
        return answers;
    }

}
