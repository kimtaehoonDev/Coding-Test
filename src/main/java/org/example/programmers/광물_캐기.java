package org.example.programmers;

//https://velog.io/@iamtaehoon/%EA%B4%91%EB%AC%BC-%EC%BA%90%EA%B8%B0-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-Java
import java.util.*;

class 광물_캐기 {
    public int solution(int[] picks, String[] minerals) {

        // tools: 비싼 곡괭이부터 차근차근 들어간다
        List<Integer> tools = new ArrayList<>();

        int toolNum = 0;
        for(int cnt: picks) {
            for(int i=0; i<cnt;i++) {
                tools.add(toolNum);
            }
            toolNum++;
        }

        // groups에 각 묶음들을 넣는다
        // 묶음은 5개를 기준으로 하고, 해당 묶음에 존재하는 자원들을 기록한다
        List<Group> groups = new ArrayList<>();
        int groupCnt = (minerals.length + 4)/ 5;
        for(int i=0; i< groupCnt; i++) {

            // 곡괭이 개수만큼만 Group을 채굴할 수 있다
            if (i >= tools.size()) {
                break;
            }

            Group group = new Group();
            for(int j=i*5; j<i*5 + 5; j++) {
                if (minerals.length <= j) {
                    break;
                }
                if (minerals[j].equals("diamond")) {
                    group.diamond++;
                } else if (minerals[j].equals("iron")) {
                    group.iron++;
                } else {
                    group.stone++;
                }
            }
            groups.add(group);
        }

        // groups을 정렬한다. 다이아 많은거 -> 철 많은거 -> 돌 많은거 기준으로
        groups.sort(new Comparator<>() {
            @Override
            public int compare(Group o1, Group o2) {
                // 다이아 비교
                if (o1.diamond != o2.diamond) {
                    return o2.diamond - o1.diamond;
                }
                // 철비교
                if (o1.iron != o2.iron) {
                    return o2.iron - o1.iron;
                }
                return o2.stone - o1.stone;
            }
        });


        int idx = 0;
        int total = 0;
        for(Group group : groups) {
            if (idx >= groups.size()) {
                // 곡괭이 다 씀
                break;
            }
            int tool = tools.get(idx++);
            total += group.worked(tool);
        }

        return total;
    }

    static class Group {
        int diamond = 0;
        int iron = 0;
        int stone = 0;

        public int worked(int tool) {
            if (tool == 0) {
                return diamond + iron + stone;
            }
            if (tool == 1) {
                return diamond * 5 + iron + stone;
            }
            return diamond * 25 + iron * 5 + stone;
        }
    }

}
