/*
    1. (100 - 현재 진행률)%진행 속도 를 계산해 나눈 몫 또는 몫 + 1 으로 필요한 날의 수를 구함
    2. 앞이 우선순위가 높으므로 앞에서부터 현재 기준(now)를 고정하여
        2-1. 그 다음 날이 now 보다 작거나 같으면 cnt만 증가 -> 현재 배포에 완성된 기능 수
        2-2. 아니라면, cnt를 list에 저장해두고 now 갱신
    3. 마지막에 모은 cnt까지 list에 저장
    4. 결과가 배열이어야 하므로 결과 리스트를 배열로 복사
*/

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
