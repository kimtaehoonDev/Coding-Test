package org.example.leetcode;

// https://velog.io/@iamtaehoon/Valid-Anagram-%EB%A6%AC%ED%8A%B8%EC%BD%94%EB%93%9C-6enunq0y
class p242 {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] alpha = new int[26];
        char[] chars = s.toCharArray();
        for(char each: chars) {
            alpha[(int) each - 'a'] += 1;
        }

        char[] charsAboutT = t.toCharArray();
        boolean answer = true;
        for(char each: charsAboutT) {
            if (alpha[(int) each - 'a'] == 0) {
                answer = false;
                break;
            }
            alpha[(int) each - 'a'] -= 1;
        }

        return answer;
    }
}
