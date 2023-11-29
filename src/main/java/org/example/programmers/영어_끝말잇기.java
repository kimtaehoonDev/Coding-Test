package org.example.programmers;


import java.util.HashSet;
import java.util.Set;

class 영어_끝말잇기 {
    public int[] solution(int n, String[] words) {
        Set<String> visitedWords = new HashSet<>();
        String lastWord = null;
        for(int idx = 0; idx<words.length; idx++) {
            // System.out.println(words[idx]);
            // 끝말잇기 규칙을 위반했거나 혹은 이미 말했던 단어를 말하면
            if (!isRule(lastWord, words[idx]) ||
                visitedWords.contains(words[idx])) {
                int num = idx % n + 1;
                int round = idx / n + 1;
                return new int[] {num, round};
            }

            visitedWords.add(words[idx]);
            lastWord = words[idx];
        }

        // 모든 사람이 끝말잇기 성공
        return new int[] {0, 0};
    }

    static boolean isRule(String prev, String now) {
        if (prev == null) {
            return true;
        }
        // lastWord의 마지막 단어랑 == words[idx]의 첫 번째 단어가 일치하니?
        char[] prevChar = prev.toCharArray();
        char[] nowChar = now.toCharArray();

        char prevLast = prevChar[prev.length() - 1];
        char nowFirst = nowChar[0];

        return prevLast == nowFirst;
    }
}
