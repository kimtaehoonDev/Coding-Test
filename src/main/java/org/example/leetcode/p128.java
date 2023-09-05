package org.example.leetcode;

import java.util.HashMap;
import java.util.Map;

class p128 {
    public int longestConsecutive(int[] nums) {
        // -10억부터 10억의 범위를 갖기 때문에 배열을 만들 수 없습니다.
        // 배열 대신 Map을 사용해 연결을 확인합니다.
        Map<Integer, Length> hash = new HashMap<>();

        int max = 0;
        for(int num: nums) {
            // 해당 위치에 값이 처음 나오면 왼쪽 오른쪽을 연결한다
            if (!hash.containsKey(num)) {
                // 둘 다 값이 있으면
                if (hash.containsKey(num - 1) && hash.containsKey(num + 1)) {

                    Length left = hash.get(num - 1);
                    Length right = hash.get(num + 1);

                    // left += (right + 1)
                    left.add(right.value + 1);

                    // right를 left로 변경합니다.
                    // 내부적으로 포인터처럼 연결해줍니다.
                    right.change(left);

                    // 나자신은 연결 값 그대로
                    hash.put(num, left);
                } else if (hash.containsKey(num - 1)) {
                    // 왼쪽으로 이미 연결된 값이 있으면 걔에 + 1
                    // 그리고 나도 왼쪽 녀석 값 넣기
                    Length left = hash.get(num - 1);
                    left.add(1);

                    hash.put(num, left);
                } else if (hash.containsKey(num + 1)) {
                    Length right = hash.get(num + 1);
                    right.add(1);

                    hash.put(num, right);
                } else {
                    // 아무것도 연결된 값이 없으면
                    hash.put(num, new Length(1));
                }
            }
            // 최대길이를 갱신
            max = Math.max(max, hash.get(num).value);
        }

        return max;
    }

    static class Length {
        int value;
        Length length;

        public Length(int value) {
            this.value = value;
        }

        public void add(int x) {
            if (length != null) {
                length.add(x);
                value = length.value;
            } else {
                value += x;
            }
        }

        public void change(Length length) {
            this.length = length;
        }
    }
}