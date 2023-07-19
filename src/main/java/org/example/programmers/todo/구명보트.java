package org.example.programmers.todo;

import java.util.*;

// 실패한 문제. 아이디어부터 틀림. 아이디어를 참고해서 품
// 원래 생각했던 아이디어: 가장 무거운 애 + 남은 공간에 태울 수 있는 제일 무거운 애 + 그다음 무거운 애 ....
// 이런 방식이라서 링크드리스트를 사용하면서 넣었다 뺐다 했는데, 그냥 틀리고 시간초과도 뜸

class 구명보트 {
    public int solution(int[] peopleAry, int limit) {
        int answer = 0;
        List<Integer> people = new LinkedList<>();
        for(int each: peopleAry) {
            people.add(each);
        }

        // 내림차순 정렬한다(3210)
        Collections.sort(people, new Comparator<>() {
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        while(!people.isEmpty()) {
            int person = people.remove(0); // 가장 무거운 녀석을 고른다(리스트에서 제거)
            int remain = limit - person;
            int next;
            while (true) {
                next = search(people, remain);
                if (next == -1) {
                    // 더 태울 수 없다
                    break;
                }
                person = people.remove(next);
                remain -= person;
            }
            answer += 1;
        }

        return answer;
    }

    // people에서 target과 같은 값, 같은 값이 없으면 가장 가까운 작은 값의 인덱스를 구한다
    // 없으면 -1 반환
    public int search(List<Integer> people, int target) {
        if (people.isEmpty()) {
            return -1;
        }
        int low = 0;
        int high = people.size();
        return searchHelper(people, low, high, target);
    }

    public int searchHelper(List<Integer> people, int low, int high, int target) {
        if (low >= high) {
            return high - 1;
        }
        int mid = (low + high) / 2;
        int person = people.get(mid);
        // 찾으려는 사람의 몸무게가 가볍거나 같으면(목표지점)
        // 찾으려는 사람의 몸무게가 무거우면(정답아님)
        if (person > target) {
            high = mid;
        } else {
            low = mid + 1;
        }
        return searchHelper(people, low, high, target);
    }
}