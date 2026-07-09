import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> list = new ArrayList<>();
        int[] days = new int[progresses.length];  // 남은 진행 소요 시간
        
        for(int i=0; i<progresses.length; i++){
            int remain = (100 - progresses[i]);
            days[i] = (remain % speeds[i] != 0) ?remain/speeds[i] + 1 : remain/speeds[i];
        }
        
        int idx = 1;  // 기준 인덱스
        int now = days[0];
        int cnt = 1;
        for(int i=1; i<progresses.length; i++ ){
            if(now >= days[i]){
                cnt++;  // 기능 수 증가
            } else{
                list.add(cnt);  // 현재 누적된 기능 배포
                now = days[i];  // 새로운 기준
                cnt = 1;
            }
        }
        list.add(cnt);
        
        int[] answer = new int[list.size()];
        for(int i=0; i<list.size(); i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}