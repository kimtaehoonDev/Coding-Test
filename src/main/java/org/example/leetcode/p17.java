package org.example.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/letter-combinations-of-a-phone-number/
public class p17 {
static char[] inputs;
static List<String> answers;
static Map<Character, Character> keyboards;

public List<String> letterCombinations(String digits) {
    inputs = digits.toCharArray();

    answers = new ArrayList<>();

    keyboards = new HashMap<>();
    keyboards.put('2','a');
    keyboards.put('3','d');
    keyboards.put('4','g');
    keyboards.put('5','j');
    keyboards.put('6','m');
    keyboards.put('7','p');
    keyboards.put('8','t');
    keyboards.put('9','w');

    if (digits.length() == 0) {
    return List.of();
    }
    makeLetter(0, new StringBuilder()); // 현재 위치, char 문자열
    return answers;
    }

public void makeLetter(int now, StringBuilder sb) {

    if (sb.length() == inputs.length) {
    // answer로 문자열을 만들어 answers에 넣는다
    answers.add(sb.toString());
    return;
    }
    // now를 보고
    char click = inputs[now];
    int range = 3;
    if (click == '7' || click == '9') {
    range = 4;
    }
    for(int i=0; i<range; i++) {
    sb.append((char) (keyboards.get(click) + i));
    makeLetter(now + 1, sb);
    sb.deleteCharAt(sb.length() - 1);
    }

    }
}

// 단순한 구현 문제
// 다른 풀이들은 1 -> abc 이런 식으로 저장했는데, 나는 'a' 만 저장하고 'a' + 1 == 'b'라는 원리로 문제를 품

// 다른 풀이를 보니 StringBuilder를 사용. 아하, 왜 StringBuilder 사용할 생각을 못했을까
// StringBuilder 역시 char를 쌓을 수 있음
// 새로 배운 테크닉: StringBuilder의 deleteCharAt(idx) 특정 위치의 char를 빼버릴 수 있다

