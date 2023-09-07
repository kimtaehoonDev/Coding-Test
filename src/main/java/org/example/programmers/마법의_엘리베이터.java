package org.example.programmers;

// 실패 / 3시간 -> 아이디어 참조 후 풀이 성공

// 총 3개의 풀이를 만들었다

// 첫 풀이 : 0에서 시작해 1,10,100,.....,1억, +1 카운트
// for문 한 번 돌 때마다 += 1 ~ 1억까지 18번의 bfs를 수행 -> 시간복잡도 해결 못해서 이 풀이 아니라고 생각해 풀이를 뒤집어엎음

// 두 번째 풀이 : 자릿수별로 0을 만들면 된다는 아이디어를 얻음
// 16이라고 하면 20 or 10을 만들어야 하는데 숫자를 더 적게 누를 수 있는 20을 만드는 방식
// -> 이 풀이가 정답인데 5일때 처리를 빼먹음


// 세 번째 풀이 : 어차피 자릿수당 한 번씩의 클릭이 일어남
// 그러면 16이라 하면 (20,4번클릭), (10,6번클릭) 이런 식으로 나올건데
// 층수는 1억까지 있음.
// -1억 == 10의 9승 버튼을 눌러서 돌아오는 걸 생각할 수 있음
// 각 자리수별로 10번만 확인하면 된다는 아이디어로 문제에 도전했는데 아무튼 실패

// 답안을 참조했는데 2번째 풀이로 접근할 수 있는걸 확인하고, 다시 2번째 풀이로 품
// 숫자가 5일 때 처리를 신경써야함
// 숫자가 5라면 그 다음 자리수를 확인해야함. 0을 만들기 위해 - 버튼을 누르나 + 버튼을 누르는게 똑같은 횟수
// 예를 들어 15라고 하면 10,20을 만드는데 둘 다 5번의 클릭

// 그래서 5일 때는 그 다음 자리수가 0,1,2,3,4면 - 버튼을 누르고
// 5,6,7,8,9일때는 + 버튼을 눌러야함

// 근데 나는 0,1,2,3,4,5일때 - 버튼을 누르는거로 실수를 해서 문제에 틀림
// 아쉬운건 분명 0,1,2,3,4일때 -, 5,6,7,8,9일때 +라고 생각을 했는데 실수를 함
// 오류를 볼 생각을 하지 않고 바로 풀이를 바꿔버려 다시 먼 길을 가게 되었다는 점이 아쉬움
public class 마법의_엘리베이터 {

    class Solution {
        public int solution(int storey) {
            int answer = 0;
            int pow = 1;
            while(storey != 0) {
                int value = storey % 10;
                if (value < 5) {
                    answer += value;
                    storey -= value;
                } else if (value > 5) {
                    answer += (10 - value);
                    storey += (10 - value);
                } else { // value == 5
                    // 2555 라면
                    int temp = (storey / 10) % 10;
                    if (temp < 5) {
                        // 빼기연산
                        answer += value;
                        storey -= value;
                    } else {
                        // 더하기연산
                        answer += value;
                        storey += value;
                    }

                }
                storey /= 10;
            }

            return answer;
        }
    }

}







// 첫 번쨰 풀이
// class Solution {
//     static Map<Integer, Integer> dp; // 기껏해야 10억까지나 만들어질듯
//     public int solution(int storey) {
//         dp = new HashMap<>();

//         Queue<Integer> queue = new LinkedList<>();
//         queue.add(0);
//         dp.put(0,0); // 0층에서 출발해서 0 도착하려면 0번 버튼 누름 됨

//         int now = -1;
//         while(!queue.isEmpty()) {
//             now = queue.poll();

//             for(int c=0; c<9; c++) {
//                 int buttonValue = (int) Math.pow(10, c);
//                 if (canGo(now + buttonValue)) {
//                     queue.add(now+buttonValue);
//                     dp.put(now + buttonValue, dp.get(now) + 1);
//                     if (now + buttonValue == storey) {
//                         return dp.get(now) + 1;
//                     }
//                 }
//                 if (canGo(now - buttonValue)) {
//                     queue.add(now - buttonValue);
//                     dp.put(now - buttonValue, dp.get(now) + 1);
//                     if (now - buttonValue == storey) {
//                         return dp.get(now) + 1;
//                     }
//                 }
//             }
//         }

//         // 불가능
//         return -1;
//     }

//     public boolean canGo(int stair) {
//         if (stair < 0) {
//             return false;
//         }

//         return !dp.containsKey(stair);
//     }
// }


// 3번째ㅔ 풀이 : ConcurrentModificationException 아마 for문 도는 와중에 for문 건들여서 그런듯

// class Solution {
//     // key를 만들기 위해서는 value번을 클릭해야 한다
//     static Map<Integer, Integer> dp = new HashMap<>();

//     public int solution(int storey) {
//         int digit = getDigits(storey);

//         dp.put(storey, 0);
//         dp.put(0, 2100000000);
//         // hash에는 234라면 1, 230이라면 2를 key로 갖는다
//         Map<Integer, List<Integer>> hash = new HashMap<>();
//         for(int i=0; i< 11; i++) {
//             hash.put(i, new ArrayList<>());
//         }
//         // storey를 hash에 넣는다
//         hash.get(0).add(storey);

//         // 자릿수만큼 반복할겨
//         for(int i=0; i< digit+2; i++) {
//             List<Integer> keys = hash.get(i);
//             for(Integer key: keys) {
//                 int click = makeClickCnt(key, i+1); // 50이면 5, 16이면 6, 200이면 2
//                 System.out.println(click+"***");
//                 int newKey = key - click * (int) Math.pow(10,i);
//                 System.out.println(newKey + "만들기 위해 클릭 횟수 (기존 = "+ key + ") (i = "+ i + ") : " + (dp.get(key) + click));
//                 if (newKey == 0) {
//                     dp.put(0, Math.min(dp.get(0), dp.get(key) + click));
//                 }

//                 if (newKey > 0) {
//                     //newKey가 0이 몇 개 있는지 검사한다
//                     if (dp.containsKey(newKey)) {
//                         dp.put(newKey, Math.min(dp.get(newKey), dp.get(key) + click));
//                     } else {
//                         dp.put(newKey, dp.get(key) + click);
//                         System.out.println(countZeroCnt(newKey) + " " + newKey);
//                         hash.get(countZeroCnt(newKey)).add(newKey);
//                     }
//                 }

//                 newKey = key + (int) Math.pow(10,i-1) * (10 - click);
//                 System.out.println(newKey + "만들기 위해 클릭 횟수 :(기존 = "+ key + ")bbb "  + (dp.get(key) + (10 - click)));
//                 if (dp.containsKey(newKey)) {
//                     dp.put(newKey, Math.min(dp.get(newKey), dp.get(key) + (10-click)));
//                 } else {
//                     dp.put(newKey, dp.get(key) + (10-click));
//                     hash.get(countZeroCnt(newKey)).add(newKey);
//                 }
//             }
//         }


//         return dp.get(0);
//     }

//     public int countZeroCnt(int num) {
//         int idx = 0;
//         while(true) {
//             if (num % (int)Math.pow(10,idx+1) == 0) {
//                 num /= (int)Math.pow(10,idx+1);
//                 idx++;
//                 continue;
//             }
//             return idx;
//         }
//     }

//     public int makeClickCnt(int key, int i) {
//             int num = (key - (key / (int) Math.pow(10,i)) * (int) Math.pow(10,i));
//             while(true) {
//                 if (num / 10 == 0) {
//                     return num;
//                 }
//                 num /= 10;
//             }
//     }

//     public int getDigits(int value) {
//         int cnt = 0;
//         while(value != 0) {
//             value /= 10;
//             cnt++;
//         }
//         return cnt;
//     }

// }

