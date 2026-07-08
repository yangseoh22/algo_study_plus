/*
    1. 일주일 반복(인덱스 i로 몇번째 날인지 알 수 있음)
    2. startday가 6이나 7이 아니면 진행
    3. 직원 수 만큼 진행
        3-1. 최대 인정 시간을 계산
        3-2. 만약 십의자리 수가 6이상이라면, 다음 시로 넘어가야 하므로 확인
        3-2-1. 새로운 시간 생성 -> 100으로 나눈 몫에 1을 더하고 다시 100을 곱 + 일의자리수
        3-3. 최대 인정시간에서 실제 출근 시간을 뺀 값이 양수라면 해당 직원의 성공 날짜 증가
    4. 모든 직원 중 성공 날짜가 5일(주말 제외)인 경우를 카운트
*/

class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        int[] tmp = new int[schedules.length];
        for(int i=0; i<7; i++){  // 일주일 - i번째 날
            if(startday != 6 && startday != 7){
                for(int user=0; user<schedules.length; user++){
                    int goal = schedules[user] + 10;
                    // 다음 시간으로 넘어감
                    if(goal%100 >= 60){
                        goal = (goal/100 + 1) * 100 + goal%10;
                    }
                    int diff = goal - timelogs[user][i];
                    if(diff >= 0) tmp[user]++;
                }
            }
            startday = startday % 7 + 1;
        }
        
        // 평일 모두 만족한 직원의 수
        for(int i=0; i<tmp.length; i++){  
            if(tmp[i] == 5) answer++;
        }
        
        return answer;
    }
}
