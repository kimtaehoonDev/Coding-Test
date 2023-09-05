package org.example.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// https://velog.io/@iamtaehoon/Group-Anagram-%EB%A6%AC%ED%8A%B8%EC%BD%94%EB%93%9C
class p49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        boolean[] checks = new boolean[strs.length];
        List<Node> nodes = new ArrayList<>(strs.length);
        for(String str: strs) {
            nodes.add(new Node(str));
        }

        Map<String, List<String>> hash = new HashMap<>();
        for(Node node: nodes) {
            if (!hash.containsKey(node.sorted)) {
                hash.put(node.sorted, new ArrayList<>());
            }
            hash.get(node.sorted).add(node.original);
        }

        List<List<String>> answers = new ArrayList<>();
        for(List<String> value: hash.values()) {
            answers.add(value);
        }
        return answers;
    }

    boolean isAnagram(Node n1, Node n2) {
        return true;
    }

    static class Node {
        String original;
        String sorted;

        public Node(String original) {
            this.original = original;
            char[] sortedTemp = original.toCharArray();
            Arrays.sort(sortedTemp);
            sorted = String.valueOf(sortedTemp);
        }
    }
}