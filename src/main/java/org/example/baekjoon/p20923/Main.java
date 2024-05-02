package org.example.baekjoon.p20923;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 57분 / 성공 / 구현
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        Deque<Integer> userDo = new LinkedList<>();
        Deque<Integer> userSu = new LinkedList<>();
        Deque<Integer> dummyUsedDo = new LinkedList<>();
        Deque<Integer> dummyUsedSu = new LinkedList<>();

        for (int i = 0; i < N; i++) { // 덱의 아래부터 카드를 넣는다. == 출구(First) 로 넣기
            st = new StringTokenizer(br.readLine());
            userDo.addFirst(Integer.parseInt(st.nextToken()));
            userSu.addFirst(Integer.parseInt(st.nextToken()));
        }

//        System.out.println("도도" + userDo); // 뒤로 들어가서 앞으로 나오는 큐(덱 아래 ... 덱 위)
//        System.out.println("수연" + userSu);

        String winner = null;
        int round = 0;
        while (round < R) { // R번 반복
            round++;
            Deque<Integer> targetUser = null;
            Deque<Integer> targetDummy = null;
            Deque<Integer> anotherDummy = null;
            if (round % 2 == 1) {
                // 도도의 턴
                targetUser = userDo;
                targetDummy = dummyUsedDo;
                anotherDummy = dummyUsedSu;
            } else {
                targetUser = userSu;
                targetDummy = dummyUsedSu;
                anotherDummy = dummyUsedDo;
            }

            // 타겟의 가장 위에서 카드를 한 장 뺀다.
            int card = targetUser.pollFirst();

            // 이 때, 카드가 0장이 되면 상대방이 승리한다.
            if (targetUser.size() == 0) {
                if (targetUser == userDo) {
                    winner = "su";
                } else {
                    winner = "do";
                }
                break;
            }

            // 뺀 카드를 더미의 가장 위에 놓는다.
            targetDummy.addLast(card);

            int top1 = targetDummy.getLast(); // 가장 마지막에 들어옴 == 더미 가장 위에 있음
            int top2 = 0;
            if (!anotherDummy.isEmpty()) {
                top2 = anotherDummy.getLast();
            }

            if (top1 == 5 || top2 == 5) {
                // 도도 라운드 승리
                // 수연 더미 뒤집어 넣기
                while (!dummyUsedSu.isEmpty()) {
                    userDo.addLast(dummyUsedSu.pollFirst());
                }
                // 도도 더미 뒤집어 넣기
                while (!dummyUsedDo.isEmpty()) {
                    userDo.addLast(dummyUsedDo.pollFirst());
                }
            } else if (top1 + top2 == 5) { // top2가 0이면 안됨.
                // 수연 라운드 승리
                // 도도 더미 뒤집어 넣기
                while (!dummyUsedDo.isEmpty()) {
                    userSu.addLast(dummyUsedDo.poll());
                }
                // 수연 더미 뒤집어 넣기
                while (!dummyUsedSu.isEmpty()) {
                    userSu.addLast(dummyUsedSu.poll());
                }
            }
        }
        if (winner == null) {
            if (userDo.size() == userSu.size()) {
                winner = "dosu";
            } else if (userDo.size() > userSu.size()) {
                winner = "do";
            } else {
                winner = "su";
            }
        }
        System.out.println(winner);
//        System.out.println(userDo);
//        System.out.println(userSu);
    }

}
