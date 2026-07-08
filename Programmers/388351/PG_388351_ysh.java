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