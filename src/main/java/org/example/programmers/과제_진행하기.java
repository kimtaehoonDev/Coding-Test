package org.example.programmers;
import java.util.*;

// 걀과 : 성공
// 풀이시간 : 1시간 12분
public class 과제_진행하기 {

    class Solution {
        static int NAME = 0;
        static int START = 1;
        static int PLAYTIME = 2;

        // 중요: 모든 plan은 시작시간이 다르다!

        public String[] solution(String[][] plans) {
            String[] answer = new String[plans.length];

            // 빨리 시작하는 작업 순으로 정렬합니다.
            PriorityQueue<MyPlan> queue = new PriorityQueue<>((o1, o2) -> o1.start.value - o2.start.value);
            for(String[] plan: plans) {
                queue.add(new MyPlan(plan));
            }

            // 작업하다가 멈추면 Stack에 저장합니다.
            Stack<MyPlan> stack = new Stack<>();

            // 각 작업들을 확인합니다.
            int idx = 0;
            while(!queue.isEmpty()) {
                MyPlan now = queue.poll();

                // 마지막 작업이라면 정답리스트에 추가한다
                // 마지막 작업이면 작업을 뺏길 일 없이 끝까지진 행할 수 있습니다.
                if (queue.isEmpty()) {
                    answer[idx++] = now.name;
                    continue;
                }

                // 비교를 위해 다음 작업을 가져옵니다.
                MyPlan next = queue.peek();

                // 작업을 끝낼 수 있는 상황입니다.
                // 12시에 시작하고 30분 걸리는 작업일 때, 다음 작업이 12시 40분인 그런 상황입니다.
                if (isNowEnd(now,next)) {
                    // 지금 작업을 성공적으로 끝낸다
                    answer[idx++] = now.name;

                    // 남은 시간동안 아직 덜끝낸 작업들을 한다
                    while(!stack.isEmpty()) {
                        // 스택 가장 위에 있던 작업을 실행합니다.
                        // 해당 작업을 시작하는 시간은 갱신되기 전 now 작업이 끝난 시간입니다.
                        MyTime ending = new MyTime(now.start.value + now.playtime);
                        now = stack.pop();
                        now.start = ending;

                        // 스택에 있던 작업을 끝낼 수 있으면 끝냅니다.
                        if (isNowEnd(now, next)) {
                            answer[idx++] = now.name;
                        } else {
                            // 다음 작업이 시작하기 전까지, 작업을 끝내지 못하는 상황입니다.
                            // 할 수 있는 만큼 일하고 stack에 남은 작업을 넣습니다.
                            now.playtime -= (next.start.value - now.start.value);
                            stack.push(now);

                            // 스택에 있는 작업들을 더 이상 수행할 수 없으므로 break로 탈출합니다.
                            break;
                        }
                    }
                } else { // 지금 작업을 끝낼 수 없는 상황입니다.
                    // 할수 있는 만큼 일하고 stack에 남은 작업을 넣습니다.
                    int workingTime = next.start.value - now.start.value;
                    now.playtime -= workingTime;
                    stack.add(now);
                }
            }

            // 스택에 있는거 다 추가한다
            while(!stack.isEmpty()) {
                MyPlan plan = stack.pop();
                answer[idx++] = plan.name;
            }
            return answer;
        }

        public boolean isSame(MyPlan now, MyPlan next) {
            return now.start.value + now.playtime == next.start.value;
        }
        public boolean isNowEnd(MyPlan now, MyPlan next) {
            return now.start.value + now.playtime <= next.start.value;
        }

        static class MyPlan {
            String name;
            MyTime start;
            int playtime;

            public MyPlan(String[] args) {
                this.name = args[0];
                this.start = new MyTime(args[1]);
                this.playtime = Integer.parseInt(args[2]);
            }
        }

        static class MyTime {
            int value;

            public MyTime(String time) {
                String[] temps = time.split(":");
                int hour = Integer.parseInt(temps[0]);
                int minute = Integer.parseInt(temps[1]);

                this.value = hour * 60 + minute;
            }

            public MyTime(int value) {
                this.value = value;
            }

            public boolean equals(MyTime obj) {
                return this.value == obj.value;
            }
        }
    }

}
