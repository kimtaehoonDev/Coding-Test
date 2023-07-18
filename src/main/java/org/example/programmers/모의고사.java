package org.example.programmers;

import java.util.ArrayList;
import java.util.List;

public class 모의고사 {
    class Solution {
        public int[] solution(int[] answers) {
            List<Integer> topStudents = new ArrayList<>();

            int[] first = {1,2,3,4,5};
            int[] second = {2,1,2,3,2,4,2,5};
            int[] third = {3,3,1,1,2,2,4,4,5,5};

            int firstPointer = -1;
            int secondPointer = -1;
            int thirdPointer = -1;

            int firstAnswer = 0;
            int secondAnswer = 0;
            int thirdAnswer = 0;

            for(int answer: answers) {
                firstPointer = (firstPointer + 1) % first.length;
                secondPointer = (secondPointer + 1) % second.length;
                thirdPointer = (thirdPointer + 1) % third.length;

                if (first[firstPointer] == answer)  {
                    firstAnswer += 1;
                }
                if (second[secondPointer] == answer)  {
                    secondAnswer += 1;
                }
                if (third[thirdPointer] == answer)  {
                    thirdAnswer += 1;
                }
            }

            int top = Math.max(firstAnswer, Math.max(secondAnswer, thirdAnswer));
            if (firstAnswer == top) {
                topStudents.add(1);
            }
            if (secondAnswer == top) {
                topStudents.add(2);
            }
            if (thirdAnswer == top) {
                topStudents.add(3);
            }
            return topStudents.stream().mapToInt(i->i).toArray();
        }
    }
}
