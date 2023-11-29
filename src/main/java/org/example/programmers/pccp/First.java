package org.example.programmers.pccp;

import java.util.*;

// 36분 / 엣지케이스 한 번 걸린 후 성공
class First {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int timeToComplete = bandage[0];
        int recoveryPerSecond = bandage[1];
        int bonus = bandage[2];
        int maxHealth = health;

        int[] dp = new int[1001];
        for(int[] attack: attacks) {
            dp[attack[0]] = attack[1];
        }

        int answer = 0;
        int bandagingTime = 0;
        int time = 0;
        while(time < attacks[attacks.length - 1][0]) { // 마지막 10초 -> 11초에 종료
            // System.out.println(time + "체력: " + health + " 밴디징 : "+bandagingTime);

            time++;
            if (dp[time] != 0) { // 몬스터가 공격             
                bandagingTime = 0;
                health -= dp[time];
                // System.out.println(dp[time] + " 공격받아 내 체력은 " + health);
                if (health <= 0) {
                    answer = -1;
                    break; // 죽음
                }
                continue;
            }
            health+=recoveryPerSecond;
            bandagingTime++;
            if (bandagingTime == timeToComplete) {
                health += bonus;
                bandagingTime = 0;
            }
            if (health > maxHealth) {
                health = maxHealth;
            }
        }
        if (answer != -1) {
            answer = health;
        }
        return answer;
    }
}