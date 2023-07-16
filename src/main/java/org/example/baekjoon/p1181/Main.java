package org.example.baekjoon.p1181;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<String, Word> words = new HashMap<>();
        for(int i=0; i<N;i++) {
            String key = br.readLine();
            words.put(key, new Word(key));
        }
        List<Word> wordList = words.values().stream().collect(Collectors.toList());
        Collections.sort(wordList);
        StringBuilder sb = new StringBuilder();
        for (Word word : wordList) {
            sb.append(word + "\n");
        }
        System.out.println(sb);
    }

    static class Word implements Comparable<Word>{
        String value;
        public Word(String value) {
            this.value = value;
        }

        public int size() {
            return value.length();
        }

        public int compareTo(Word o) {
            if (this.size() > o.size()) {
                return 1;
            } else if (this.size() == o.size()) {
                // 사전순서대로 정렬(a가 작고 z가 크다)
                return this.value.compareTo(o.value);
            }
            return -1;
        }

        public String toString() {
            return this.value;
        }
     }
}
