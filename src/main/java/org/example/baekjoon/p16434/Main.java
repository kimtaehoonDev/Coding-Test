package org.example.baekjoon.p16434;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 성공/1시간 24분
// 처음 이분탐색으로 풀까 했는데, 1부터 9경까지의 값을 확인해야 했음
// 해당 방식은 아니라고 생각해 그리디로 문제를 품
// 가장 최소의 체력으로 몬스터를 죽이려면 체력을 1 남겨두고 살아남아야 한다는 부분에서 아이디어를 얻음

public class Main {
    static int N;
    static Long PLAYER_POWER;
    static Long PLAYER_HP = 0L;
    static Long PLAYER_MAX_HP = 0L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 방의 개수
        PLAYER_POWER = Long.parseLong(st.nextToken()); // 플레이어의 파워

        for(int i=0; i<N; i++) {
            enter(br.readLine());
//            System.out.println(i + "단계 : " + PLAYER_HP + "/" + PLAYER_MAX_HP + "- 공격력 : " + PLAYER_POWER);
        }
        System.out.println(PLAYER_MAX_HP);
    }

    private static void enter(String input) {
        StringTokenizer st = new StringTokenizer(input);
        int type = Integer.parseInt(st.nextToken()); // 1: 몬스터 2:물약
        if (type == 1) {
            fight(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        } else {
            recovery(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

    }

    private static void recovery(int powerPotion, int hpPotion) {
        PLAYER_POWER += powerPotion;

        PLAYER_HP += hpPotion;
        if (PLAYER_HP > PLAYER_MAX_HP) {
            PLAYER_HP = PLAYER_MAX_HP;
        }
    }

    private static void fight(int monsterPower, int monsterHP) {
        // 싸움에서 지면 최대체력을 갱신할거야
        long hitCnt = ((long) monsterHP + PLAYER_POWER - 1) / PLAYER_POWER; // 해당 몬스터를 잡으려면 hitCnt때려야함
        long minHitValue = (hitCnt - 1) * monsterPower;
//        System.out.println("떄려야 하는 횟수 " + hitCnt);
//        System.out.println("최소 체력 : " + minHpForWin);
        if (minHitValue + 1 > PLAYER_HP) {
            PLAYER_MAX_HP += (minHitValue - PLAYER_HP) + 1;
            PLAYER_HP = 1L;
        } else { // 체력을 갱신하지 않아도 돼
            // 현재 남은 체력 4, 몬스터 잡을때 필요한거 2
            PLAYER_HP -= minHitValue;
        }

    }
}
