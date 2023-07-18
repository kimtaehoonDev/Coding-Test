package org.example.baekjoon.p9251;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Main {
    static List<String> maxList;
    static List<String> minList;
    static Map<String, PriorityQueue<Integer>> hash;
    static Integer maxLength = 1;

    public static void main(String[] args) throws IOException {
        // 입력 받는다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();

        // 중복되는 애들만 남긴다
        Set<String> duplicatedChar = findDuplicatedChar(str1, str2);
        List<String> first = remainDuplicatedChar(str1, duplicatedChar);
        List<String> second = remainDuplicatedChar(str2, duplicatedChar);


        if (first.size() < second.size()) {
            maxList = second;
            minList = first;
        } else {
            maxList = first;
            minList = second;
        }

        // 더 큰 배열로 HashTable을 만든다
        hash = makeHashTable(maxList);

        findMaxLength(0, 0, 0);
        System.out.println(maxLength);
    }

    public static void findMaxLength(int depth, int endIdx, int selectedCnt) {
        maxLength = Math.max(maxLength, selectedCnt);

        // 만들 수 있는 길이가 최대값보다 작거나 같아지면 종료한다(이미 답이 나옴)
        if (selectedCnt + (minList.size() - depth) <= maxLength) {
            return;
        }
        if (minList.size() <= depth) {
            return;
        }

        String target = minList.get(depth);
        PriorityQueue<Integer> queue = hash.get(target);
        // 큐를 전부 확인해야 함.
        List<Integer> temp = new ArrayList<>();
        while(!queue.isEmpty()) {
            Integer idx = queue.poll();
            temp.add(idx);
            if (idx >= endIdx) {
                findMaxLength(depth + 1, idx, selectedCnt + 1);
                break;
            }
        }
        queue.addAll(temp);
        // 해당 원소를 안뽑는ㄴ다
        findMaxLength(depth + 1, endIdx, selectedCnt);

    }

    public static Map<String, PriorityQueue<Integer>> makeHashTable(List<String> chars) {
        Map<String, PriorityQueue<Integer>> result = new HashMap<>();
        int idx = -1;
        for (String each : chars) {
            idx += 1;
            if (result.get(each) == null) {
                result.put(each, new PriorityQueue<>());
            }
            PriorityQueue<Integer> queue = result.get(each);
            queue.add(idx);
        }
        return result;
    }

    public static Set<String> findDuplicatedChar(String str1, String str2) {
        Set<String> result = new HashSet<>();
        String[] total = str1.split("");
        Set<String> duplicates = new HashSet<>();
        for(String each: total) {
            duplicates.add(each);
        }
        String[] second = str2.split("");
        for(String each: second) {
            if (duplicates.contains(each)) {
                result.add(each);
            }
        }
        return result;
    }

    public static List<String> remainDuplicatedChar(String str, Set<String> duplicates) {
        List<String> result = new ArrayList<>();
        String[] total = str.split("");
        for(String each: total) {
            if (duplicates.contains(each)) {
                result.add(each);
            }
        }
        return result;
    }
}
