package org.example.programmers;

import java.util.HashMap;
import java.util.Map;

// 성공/12분
public class 로또의_최고_순위와_최저_순위 {
    class Solution {
        public int[] solution(int[] lottos, int[] win_nums) {
            Map<Integer, Boolean> hash = new HashMap<>();
            for(int winNum: win_nums) {
                hash.put(winNum, true);
            }

            int removed = 0;
            int incorrect = 0;
            for(int lotto: lottos) {
                if (lotto == 0) {
                    removed++;
                    continue;
                }
                if(!hash.containsKey(lotto)) {
                    incorrect++;
                }
            }
            // System.out.println(removed + " " + incorrect);
            int min = calcRank(lottos.length - (incorrect + removed));
            int max = calcRank(lottos.length - incorrect);

            return new int[] {max, min};
        }

        public static int calcRank(int correct){
            // System.out.println("입력값" + correct);
            if (correct < 2) {
                return 6;
            }
            int rank = 7 - correct;
            if (rank < 1) {
                rank = 1;
            }
            return rank;
        }
    }
}
