package org.example.baekjoon.p19583;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] times = br.readLine().split(" ");
        int start = convertTimestamp(times[0]);
        int end = convertTimestamp(times[1]);
        int streamEnd = convertTimestamp(times[2]);
//        System.out.println(start);
//        System.out.println(end);
//        System.out.println(streamEnd);

        Set<String> inRecord = new HashSet<>();
        Set<String> outRecord = new HashSet<>();
        String input;
        while ((input = br.readLine()) != null) {
            String[] timeAndName = input.split(" ");
            int timestamp = convertTimestamp(timeAndName[0]);
            if (timestamp <= start) {
                // 입장기록 추가
                inRecord.add(timeAndName[1]);
            } else if (end <= timestamp && timestamp <= streamEnd) {
                // 퇴장기록 추가
                outRecord.add(timeAndName[1]);
            }
        }
        int cnt = 0;
        for(String name : inRecord) {
            if (outRecord.contains(name)) {
                cnt++;
            }
        }
        System.out.println(cnt);
//        System.out.println(inRecord);
//        System.out.println(outRecord);
    }

    static int convertTimestamp(String time) {
        String[] hourAndMinute = time.split(":");
        int hour = Integer.parseInt(hourAndMinute[0]);
        int minute = Integer.parseInt(hourAndMinute[1]);
        return hour * 60 + minute;
    }

}
