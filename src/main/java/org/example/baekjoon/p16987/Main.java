package org.example.baekjoon.p16987;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N; // 계란 개수(8개까지)
    static List<Egg> eggs = new ArrayList<>();
    static int answer = 0;
    static boolean isAnswer = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int hp = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            eggs.add(new Egg(hp, weight));
        }
        solve(0);
        System.out.println(answer);

    }

    public static void solve(int picked) {
        if (isAnswer) {
            return;
        }
        if (picked == eggs.size()) {
            validate();
            return;
        }

        Egg pickedEgg = eggs.get(picked);
        if (pickedEgg.isBroken()) {
            solve(picked + 1); // 이 계란은 글렀다
            return;
        }

        boolean allBroken = true;
        int startIdx = 0;
        for (int i = 0; i < eggs.size(); i++) {
            if (i == picked) {
                continue;
            }
            boolean broken = eggs.get(i).isBroken();
            if (!broken) {
                allBroken = false;
                startIdx = i;
                break;
            }
        }
        if (allBroken) {
            validate();
            return;
        }

        for (int i = startIdx; i < eggs.size(); i++) {
            if (i == picked) {
                continue;
            }
            Egg target = eggs.get(i);
            if (target.isBroken()) {
                continue;
            }
            pickedEgg.hit(target);
            solve(picked + 1);
            pickedEgg.restore(target);
        }
    }

    static void validate() {
        int cnt = 0;
        for (int i = 0; i < eggs.size(); i++) {
            boolean broken = eggs.get(i).isBroken();
            if (broken) {
                cnt++;
            }
        }
        answer = Math.max(answer, cnt);
        if (answer == eggs.size()) {
            isAnswer = true;
        }
    }

    static class Egg {
        int hp;
        int weight;

        public Egg(int hp, int weight) {
            this.hp = hp;
            this.weight = weight;
        }

        public boolean isBroken() {
            return hp <= 0;
        }

        public void hit(Egg target) {
            this.hp -= target.weight;
            target.hp -= this.weight;
        }

        public void restore(Egg target) {
            this.hp += target.weight;
            target.hp += this.weight;
        }
    }
}
