package org.example.programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 50분
// 해당 풀이로 풀면 시간 초과가 날 걸 예상했지만, 다른 풀이가 떠오르지 않아 시도했고 역시나 틀림
class 순위_검색 {
    static Map<String, List<Integer>> languages = new HashMap<>();
    static Map<String, List<Integer>> types = new HashMap<>();
    static Map<String, List<Integer>> careers = new HashMap<>();
    static Map<String, List<Integer>> foods = new HashMap<>();
    static Scores scores = new Scores();
    // 점수별로 정렬

    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        int idx = -1;
        for(String each: info) {
            idx++;
            String[] temp = each.split(" ");
            addValue(temp[0], languages, idx);
            addValue(temp[1], types, idx);
            addValue(temp[2], careers, idx);
            addValue(temp[3], foods, idx);

            scores.add(new Node(idx, Integer.parseInt(temp[4]))); // 정렬해서 넣는다
        }
        scores.sort();

        idx = -1;
        for(String cmd: query) {
            idx++;

            String[] temp = cmd.split(" and ");
            String[] temp2 = temp[temp.length - 1].split(" ");

            Integer score = Integer.parseInt(temp2[1]);
            temp[temp.length - 1] = temp2[0];

            // 명령을 알아서 실행한다. temp에는 각 조건들이 있고, score에는 점수가 있다
            List<Integer> result = scores.findEqualsAndGreaterThan(score); // 가격을 가지고 초기화
            // System.out.println("시작");
            // System.out.println(result);
            if (!temp[0].equals("-")) {
                if (!languages.containsKey(temp[0])) {
                    answer[idx] = 0;
                    continue;
                }
                result.retainAll(languages.get(temp[0]));
            }
            // System.out.println(result);
            if (!temp[1].equals("-")) {
                if (!types.containsKey(temp[1])) {
                    answer[idx] = 0;
                    continue;
                }
                result.retainAll(types.get(temp[1]));
            }
            // System.out.println(result);
            if (!temp[2].equals("-")) {
                if (!careers.containsKey(temp[2])) {
                    answer[idx] = 0;
                    continue;
                }
                result.retainAll(careers.get(temp[2]));
            }
            // System.out.println(result);
            if (!temp[3].equals("-")) {
                if (!foods.containsKey(temp[3])) {
                    answer[idx] = 0;
                    continue;
                }
                result.retainAll(foods.get(temp[3]));
            }
            // System.out.println(result);
            answer[idx] = result.size();
        }

        return answer;
    }

    void addValue(String value, Map<String, List<Integer>> store, int idx) {
        if (!store.containsKey(value)) {
            store.put(value, new ArrayList<>());
        }
        store.get(value).add(idx);
    }

    static class Node {
        int idx;
        int score;

        public Node(int idx, int score) {
            this.idx = idx;
            this.score = score;
        }
    }

    static class Scores {
        List<Node> store = new ArrayList<>();

        public void add(Node node) {
            store.add(node);
        }

        // 작은 값이 우선순위를 갖도록 정렬된다
        public void sort() {
            Collections.sort(store, (n1, n2) -> {
                return n1.score - n2.score;
            });
        }

        public List<Integer> findEqualsAndGreaterThan(int value) {
            // score에서 하한 이분탐색을 때리면 될듯?
            int start = 0;
            int end = store.size();

            while(start < end) {
                int mid = (start + end) / 2;
                if (store.get(mid).score >= value) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
            }

            List<Integer> result = new ArrayList<>();
            for(int i=start; i<store.size(); i++) {
                result.add(store.get(i).idx);
            }
            return result;
        }
    }
}