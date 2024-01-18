package org.example.programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// info가 하나 입력되면 info로 만들 수 있는 Case를 만든다.
// ex. cpp, backend, junior, pizza 가 입력되면
// 만들 수 있는 Case에는 cpp and backend and junior and pizza부터, _ and backend ...., _ and _ and _ and _ and 까지 존재한다.
// 이 모든 Case에 학생의 Score를 추가한다. 이후 쿼리를 날릴 때, 해당 쿼리에서 점수가 X 이상인 값들을 조회하면 되는 문제
// 시간복잡도를 향상시키기 위해 모든 Case에 들어가는 List에 대해 정렬한다.

class 순위_검색 {
    static Map<String, List<Integer>> cache = new HashMap<>();
    static int[] scores;

    public int[] solution(String[] info, String[] query) {
        int[] answers = new int[query.length];

        init();
        scores = new int[info.length];

        int idx = -1;
        for(String each: info) {
            idx++;
            String[] temp = each.split(" ");
            int value = Integer.parseInt(temp[4]);
            // temp를 사용해서 쪼개쪼개
            // 그리고 점수는 따로 기록해
            cache.get(makeKey(temp[0], temp[1], temp[2], temp[3])).add(value);

            cache.get(makeKey("-", temp[1], temp[2], temp[3])).add(value);
            cache.get(makeKey(temp[0], "-", temp[2], temp[3])).add(value);
            cache.get(makeKey(temp[0], temp[1], "-", temp[3])).add(value);
            cache.get(makeKey(temp[0], temp[1], temp[2], "-")).add(value);

            cache.get(makeKey("-", "-", temp[2], temp[3])).add(value);
            cache.get(makeKey("-", temp[1], "-", temp[3])).add(value);
            cache.get(makeKey("-", temp[1], temp[2], "-")).add(value);
            cache.get(makeKey(temp[0], "-", "-", temp[3])).add(value);
            cache.get(makeKey(temp[0], "-", temp[2], "-")).add(value);
            cache.get(makeKey(temp[0], temp[1], "-", "-")).add(value);

            cache.get(makeKey("-","-","-", temp[3])).add(value);
            cache.get(makeKey("-","-",temp[2], "-")).add(value);
            cache.get(makeKey("-", temp[1], "-", "-")).add(value);
            cache.get(makeKey(temp[0], "-", "-", "-")).add(value);

            cache.get(makeKey("-", "-", "-", "-")).add(value);
        }
        // 전부 다 정렬해준다
        for (String key : cache.keySet()) {
            List<Integer> temp = cache.get(key);
            Collections.sort(temp);
        }

        idx = -1;
        for (String cmdPreprocess : query) {
            idx++;
            String[] temp = cmdPreprocess.split(" ");
            String cmd = String.join(" ", Arrays.copyOfRange(temp, 0, temp.length - 1));
            int value = Integer.parseInt(temp[temp.length - 1]);
            List<Integer> ids = cache.get(cmd);
            answers[idx] = calculateEqualOrGreaterThan(ids, value);
        }
        return answers;
    }

    private int calculateEqualOrGreaterThan(List<Integer> ids, int value) {
        int start = 0;
        int end = ids.size();
        while(start < end) {
            int mid = (start + end) / 2;
            if (ids.get(mid) >= value) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return ids.size() - start;
    }

    public void init() {
        List<String> languages = List.of("cpp", "java", "python", "-");
        List<String> types = List.of("backend", "frontend", "-");
        List<String> careers = List.of("junior","senior", "-");
        List<String> foods = List.of("chicken", "pizza", "-");

        for(String lang : languages) {
            for(String type: types) {
                for(String career: careers) {
                    for(String food: foods) {
                        cache.put(lang + " and " + type + " and " + career + " and " + food, new ArrayList<>());
                    }
                }
            }
        }
    }

    public String makeKey(String x1, String x2, String x3, String x4) {
        return x1 + " and " + x2 + " and " + x3 + " and " + x4;
    }
}