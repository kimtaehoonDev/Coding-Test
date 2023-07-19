package org.example.programmers;

import java.util.ArrayList;
import java.util.List;

class 기능개발 {
    public int[] solution(int[] progresses, int[] speeds) {
        //작업 완료 날짜 계산(y)
        List<Integer> completes = new ArrayList<>();
        for(int i=0; i<speeds.length; i++) {
            // TODO speed 0이면 안됨(조건엔 없는데 그럴리가 없음)
            int complete = (100 - progresses[i]) / speeds[i];
            if (((100-progresses[i]) % speeds[i]) != 0) {
                complete += 1;
            }
            completes.add(complete);
        }

        List<Integer> answers = new ArrayList<>();
        if (completes.size() == 0) {
            // 작업이 없으면 비어있는 배열 반환
            return new int[] {};
        }
        Integer prev = completes.get(0);
        int cnt = 1;

        for(int i=0; i<completes.size(); i++) {
            if (i == 0) {
                continue;
            }

            if (prev < completes.get(i)) {
                answers.add(cnt);
                prev = completes.get(i);
                cnt = 1;
                continue;
            }
            // 앞에서 한 작업과 같이 끝날 기능들의 개수
            cnt += 1;
        }

        answers.add(cnt);

        return answers.stream().mapToInt(i->i).toArray();
    }
}
