package org.example.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 성공/24분

// 문제 파악을 잘못함
// skillInput을 통해 skill간의 우선순위를 정해주고, skill_tree가 적합한지 확인해야 하는데
// skill_tree마다 위상정렬을 하며 우선순위를 만들고 skill이 적절한지 확인해줌

// 그래서 로직을 변경
public class 스킬_트리 {
    class Solution {
        static char[] skill;
        static int[] visited;
        static Map<Character, List<Character>> graph;

        public int solution(String skillInput, String[] skill_trees) {
            int answer = 0;
            skill = skillInput.toCharArray();

            graph = new HashMap<>();
            visited = new int[26];

            Character prev = null;
            for(char each: skill) {
                if (prev == null) {
                    prev = each;
                    continue;
                }
                visited[each - 'A'] ++;
                if (!graph.containsKey(prev)) {
                    graph.put(prev, new ArrayList<>());
                }
                graph.get(prev).add(each);
                prev = each;
            }


            for(String skillTree: skill_trees) {
                boolean isAnswer = validate(skillTree.toCharArray());
                if (isAnswer) {
                    answer++;
                }
            }
            return answer;
        }

        public boolean validate(char[] skillTree) {
            int[] visitedCopy = Arrays.copyOfRange(visited, 0, visited.length);
            // skillTree가 가능한지 확인한다
            for(char each: skillTree) {
                if (visitedCopy[each - 'A'] != 0) {
                    return false;
                }

                List<Character> connects = graph.get(each);
                if (connects == null) {
                    continue;
                }
                for(Character connect: connects) {
                    visitedCopy[connect - 'A'] --;
                }
            }
            return true;
        }
    }
}
