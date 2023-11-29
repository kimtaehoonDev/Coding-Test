package org.example.leetcode;

// 성공
public class p211 {
    class WordDictionary {
        WordDictionary[] store;
        boolean[] isEnd;

        public WordDictionary() {
            store = new WordDictionary[26]; // 0부터 25, 26개
            isEnd = new boolean[26];
        }

        public void addWord(String word) {

            int idx = word.charAt(0) - 'a';
            if (word.length() == 1) {
                isEnd[idx] = true;
                return;
            }

            if (store[idx] == null) {
                store[idx] = new WordDictionary();
            }
            store[idx].addWord(word.substring(1, word.length()));
        }

        public boolean search(String word) {
            if (word.length() == 0) {
                return true;
            }
            int idx = word.charAt(0) - 'a';
            if (word.length() == 1) {
                if (word.charAt(0) == '.') {
                    for(boolean each : isEnd) {
                        if (each) {
                            return true;
                        }
                    }
                    return false;
                }

                return isEnd[idx];
            }

            if (word.charAt(0) == '.') {
                for(int i=0; i<26; i++) {
                    WordDictionary child = store[i];
                    if (child != null) {
                        boolean result = child.search(word.substring(1,word.length()));
                        if (result) {
                            return true;
                        }
                    }
                }
                return false;
            }

            WordDictionary child = store[idx];
            if (child == null) {
                return false;
            }
            return child.search(word.substring(1,word.length()));
        }
    }

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
}
