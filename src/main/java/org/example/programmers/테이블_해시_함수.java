package org.example.programmers;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// 실패 : 1시간 이상 고민 -> 답안 참조 후 틀린 부분 수정

// 실패 원인
// 아이디어는 제대로 잘 떠올렸다.
// 하지만 data의 원소의 길이가 3이라고 착각해버렸다 (1 <= x <= 500)
// bitwise XOR 라는 단어가 해석이 안되어서 다른 문제들을 먼저 풀었다
// 이후에 다시 돌아와서 문제를 푸는 과정에서 문제를 다 이해했다는 착각에 대충 읽었는데 그게 실패 요인.

// 아 그리고 비트연산자 ^를 몰라서 두 숫자의 XOR 연산을 하는 로직을 만들었었는데, 비트연산자 관련해서 학습이 필요할 듯 싶음
public class 테이블_해시_함수 {
    class Solution {
        public int solution(int[][] data, int col, int row_begin, int row_end) {
            col--;
            row_begin--;

            List<Node> nodes = new ArrayList<>();
            for(int[] each: data) {
                nodes.add(new Node(each));
            }

            final int finalCol = col;
            nodes.sort(new Comparator<>() {
                @Override
                public int compare(Node n1, Node n2) {
                    if (n1.data[finalCol] != n2.data[finalCol]) {
                        return n1.data[finalCol] - n2.data[finalCol];
                    }
                    return n2.data[0] - n1.data[0];
                }
            });


            // 2개에 대한 Bit 연산
            int prev = calc(nodes.get(row_begin), row_begin + 1);
            for(int i=row_begin +1; i<row_end ;i++) {

                int num = calc(nodes.get(i), i+1);
                prev = num ^ prev;
                // 이걸 XOR 연산을 하라고?
            }

            return prev;
        }


        static int calc(Node node, int i) {
            int answer = 0;
            for(int each: node.data) {
                answer += each % i;
            }
            return answer;
        }


        static class Node {
            final int[] data;

            public Node(int[] data) {
                this.data = data;
            }
        }
    }


}
