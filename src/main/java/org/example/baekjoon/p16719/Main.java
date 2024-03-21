package org.example.baekjoon.p16719;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

// 1시간 2분 / 성공 / 구현(완탐)
public class Main {

    static char[] inputs;
    static boolean[] visited;
    static List<String> answers = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        inputs = sc.nextLine().toCharArray();
        visited = new boolean[inputs.length];
//        System.out.println(Arrays.toString(inputs));

        for (int i = 0; i < inputs.length; i++) {
            String answer = solve();
            answers.add(answer);
        }

//        System.out.println(answers);
        StringJoiner sj = new StringJoiner("\n");
        for (String answer : answers) {
            sj.add(answer);
        }
        System.out.println(sj.toString());
    }

    private static String solve() {
        // 후보 문자열(인덱스로 표현중 : List<Integer>)들을 만든다.
        List<StrInfo> cases = new ArrayList<>();
        for (int i = 0; i < inputs.length; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            List<Integer> idxesAboutStr = new ArrayList<>();
            for (int j = 0; j < inputs.length; j++) {
                if (visited[j]) {
                    idxesAboutStr.add(j);
                }
            }
            visited[i] = false;
            cases.add(new StrInfo(idxesAboutStr, i));
        }
//        System.out.println(cases);

        /**
         * 후보 문자열들 중 가장 사전상에서 앞에 오는 친구를 고른다
         */
        // idx 순서대로 비교할거다
        List<StrInfo> temp = new ArrayList<>();
        int length = cases.get(0).idxes.size();
        for (int idx = 0; idx < length; idx++) {
//            각 문자열의 idx 위치에서 가장 작은 문자를 찾는다
            char minChar = 'Z' + 1;
            for (StrInfo eachCase : cases) {
                if (minChar > eachCase.getChar(idx)) {
                    minChar = eachCase.getChar(idx);
                }
            }

            // idx위치에 minChar를 가지고 있는 애들을 찾는다(정답이 가능한 Case)
            for (StrInfo eachCase : cases) {
//                System.out.println(eachCase);
                if (minChar == eachCase.getChar(idx)) {
                    temp.add(eachCase.copy());
                }
            }
            cases = new ArrayList<>(temp);
//            System.out.println(cases);
        }
        StrInfo selected = cases.get(0); // ABCC -> ABC, ABC 2개 나왔을때 먼저 나온거 선택(2번째idx)
        visited[selected.added] = true;
        return selected.convertStr();
    }

    static class StrInfo {

        List<Integer> idxes;
        int added;

        public StrInfo(List<Integer> idxes, int added) {
            this.idxes = idxes;
            this.added = added;
        }

        // order 넘는경우 고려X
        char getChar(int order) {
            int inputsIdx = idxes.get(order);
            return inputs[inputsIdx];
        }

        @Override
        public String toString() {
            return "StrInfo{" +
                "idxes=" + idxes +
                ", added=" + added +
                '}';
        }

        public String convertStr() {
            StringBuilder sb = new StringBuilder();
            for (Integer idx : idxes) {
                sb.append(inputs[idx]);
            }
            return sb.toString();
        }

        public StrInfo copy() {
            List<Integer> temp = new ArrayList<>(this.idxes.size());
            temp.addAll(idxes);
            return new StrInfo(temp, added);
        }
    }
}
