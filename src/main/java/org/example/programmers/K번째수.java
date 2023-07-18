package org.example.programmers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class K번째수 {
    class Solution {
        public int[] solution(int[] array, int[][] commands) {
            List<Integer> answer = new ArrayList<>(commands.length);
            for(int[] command: commands) {
                answer.add(find(command, array));
            }
            return answer.stream().mapToInt(i->i).toArray();
        }

        public int find(int[] command, int[] array) {
            int first = command[0] - 1;
            int second = command[1];
            int idx = command[2] - 1;

            List<Integer> temp = new ArrayList<>();
            for(int i=first; i<second; i++) {
                temp.add(array[i]);
            }
            Collections.sort(temp);
            return temp.get(idx);
        }
    }
}
