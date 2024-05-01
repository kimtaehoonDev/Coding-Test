package org.example.baekjoon.p20006;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;

// 1회 실패 후 성공
// 아이디어는 맞음. 또 문법 몰라서 틀림
// PriorityQueue는 내부적으로 느슨한 정렬 상태를 유지함. 그렇기 때문에 for문 등을 통해 정렬된 상태를 순회할 수 없음
public class Main {

    static int P;
    static int LIMIT;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        P = Integer.parseInt(st.nextToken());
        LIMIT = Integer.parseInt(st.nextToken()); // 방에 들어갈 인구 수 제한
        List<Room> rooms = new ArrayList<>();
        for (int i = 0; i < P; i++) { // 300번, 방 300번
            st = new StringTokenizer(br.readLine());
            int level = Integer.parseInt(st.nextToken());
            String nick = st.nextToken();
            Person person = new Person(level, nick);
            boolean canEnter = false;
            Room target = null;
            for (Room room : rooms) {
                if (room.canEnter(person)) {
                    canEnter = true;
                    target = room;
                    break;
                }
            }
            if (canEnter) {
                // 방에 넣는다.
                target.enter(person);
            } else {
                // 입장 불가 -> 새로운 방을 만든다.
                rooms.add(new Room(person));
            }
        }

//        System.out.println(rooms);
        StringJoiner sj = new StringJoiner("\n");
        for (Room room : rooms) {
            if (room.isFull()) {
                sj.add("Started!");
            } else {
                sj.add("Waiting!");
            }
            room.participants.sort((p1, p2) -> p1.nick.compareTo(p2.nick));
            for (Person person : room.participants) {
                sj.add(person.level + " " + person.nick);
            }
        }
        System.out.println(sj);
    }

    static class Room {

        int minLimit;
        int maxLimit;

        List<Person> participants;

        public Room(Person leader) {
            this.minLimit = leader.level - 10;
            this.maxLimit = leader.level + 10;
            participants = new ArrayList<>();
            participants.add(leader);
        }

        public boolean isFull() {
            return participants.size() >= LIMIT;
        }

        public boolean canEnter(Person person) {
            if (isFull()) {
                return false;
            }
            return person.level >= minLimit && maxLimit >= person.level;
        }

        public void enter(Person person) {
            participants.add(person);
        }

        public String toString() {
            return String.format("[%d~%d] participants : %s", minLimit, maxLimit, participants);
        }
    }

    static class Person {

        int level;
        String nick;

        public Person(int level, String nick) {
            this.level = level;
            this.nick = nick;
        }

        public String toString() {
            return String.format("[%d %s]", level, nick);
        }
    }
}
