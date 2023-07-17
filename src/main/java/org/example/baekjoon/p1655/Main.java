package org.example.baekjoon.p1655;

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        MyTree tree = new MyTree();

        List<Integer> answers = new ArrayList<>(N);
        for(int i=0; i<N; i++) {
            tree.add(Integer.parseInt(br.readLine()));
            answers.add(tree.findMid(i+1));
        }

        StringBuilder sb = new StringBuilder();
        for(Integer answer: answers) {
            sb.append(answer+"\n");
        }
        System.out.println(sb);
    }


    static class MyTree {
        int[] tree = new int[(int) Math.pow(2,16)];
        int leafFirstIdx = (int) Math.pow(2,15) - 1;

        public MyTree() {

        }

        // 인덱스를 MyTree 내부 배열의 인덱스로 재정의 해서 사용한다
        public void add(int x) {
            int newIdx = x + leafFirstIdx + 10000; // 트리에서 사용하는 인덱스로 재정의

            tree[newIdx] += 1;
            while (newIdx != 0) {
                newIdx = (newIdx + 1) / 2 - 1;
                tree[newIdx] += 1;
            }
        }

        public int findMid(int size) {
            int middle = (size + 1) / 2; // middle번째 숫자를 찾는다(middle + 1 해야 인덱스)

            int start = 1;
            while (true) {
                int left = start * 2;
                int right = left + 1;
                if (middle <= tree[left - 1]) {
                    start = left;
                } else {
                    middle -= tree[left-1];
                    start = right;
                }

                // 만약 리프노드에 진입했다면
                if (start - 1 >= leafFirstIdx) {
                    // start - 1이 의미하는걸 반환한다
                    return start - 1 - leafFirstIdx - 10000;
                }
            }
        }
    }
}

