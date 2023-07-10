package org.example.baekjoon.p12891;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 입력
        Scanner sc = new Scanner(System.in);
        int dnaStrLength = sc.nextInt();
        int passwordLength = sc.nextInt();
        char[] dna = sc.next().toCharArray();
        int minSizeOfA = sc.nextInt();
        int minSizeOfC = sc.nextInt();
        int minSizeOfG = sc.nextInt();
        int minSizeOfT = sc.nextInt();

        int answer = 0;
        PasswordCombination combination = new PasswordCombination(minSizeOfA, minSizeOfC, minSizeOfG, minSizeOfT);
        // 로직 진행
        for (int i = 0; i < dnaStrLength - passwordLength + 1; i++) {
            if (i == 0) {
                for (int j = 0; j < passwordLength; j++) {
                    combination.add(dna[j]);
                }
            } else { // 슬라이딩
                //dna[i]가 빠지고, dna[i+passwordLength-1]가 들어간다
                combination.replace(dna[i-1], dna[i+passwordLength - 1]);
            }

            // 비밀번호 조건이 맞는지 확인한다
            if (combination.canMakePassword()) {
                answer += 1;
            }
        }

        System.out.println(answer);
    }

    static class PasswordCombination {
        private int[] store = new int[4]; // int라서 0으로 초기화가 된다
        private int[] condition = new int[4];

        public PasswordCombination(int minSizeOfA,int minSizeOfC,int minSizeOfG,int minSizeOfT) {
            condition[0] = minSizeOfA;
            condition[1] = minSizeOfC;
            condition[2] = minSizeOfG;
            condition[3] = minSizeOfT;
        }

        public boolean canMakePassword() {
            for(int i=0; i<4; i++) {
                if (store[i] < condition[i]) {
                    return false;
                }
            }
            return true;
        }

        public void add(char c) {
            if (c == 'A') {
                store[0] += 1;
            } else if (c == 'C') {
                store[1] += 1;
            } else if (c == 'G') {
                store[2] += 1;
            } else if (c == 'T') {
                store[3] += 1;
            }
        }

        public void replace(char oldDna, char newDna) {
            if (oldDna == 'A') {
                store[0] -= 1;
            } else if (oldDna == 'C') {
                store[1] -= 1;
            } else if (oldDna == 'G') {
                store[2] -= 1;
            } else if (oldDna == 'T') {
                store[3] -= 1;
            }

            add(newDna);
        }
    }

}
