class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        //직원의 수
        int n = schedules.length;
        
        //출근 희망 시각에 늦지않고 출근했는지 여부
        // (true = 지각한적이 있음 / false = 지각을 한 번도 하지 않음)
        boolean[] people = new boolean[n];
        
        //출근 최대 인정 시간 구하기
        for(int i = 0; i< n; i++ ){
            int time = schedules[i];
            time += 10;
            //시간에서 분만 빼내기
            int min = time % 100;
            
            //만약 분이 60을 넘어간다면
            if(min / 60 == 1){
                //시간 - 분
                time -= min;
                //분을 0~59로 만들기
                min = min %60;
                //시간 = (시 +1 )+ 분
                time = time + 100 + min;
            }
            //출근희망 시각을 출근 인정시각으로 덮어쓰기
            schedules[i] = time;
            
        }
        //
        for(int i =0; i<7; i++ ){
            //만약 현재 요일이 토/일이라면 
            if(startday == 6 || startday == 7){
                startday += 1;
                //만약 주말이 끝났다면
                if(startday > 7){
                    startday %= 7;
                }
                //건너뛰기
                continue;
            }
            for(int j =0; j<n;j++   ){
                //만약 출근한 시각이 출근인정 시각을 넘어갔다면
                if(timelogs[j][i] - schedules[j] > 0){
                    // 지각 표시
                    people[j] = true;
                }
                
            }
            startday += 1;
        }
        // 출근을 잘한 직원 찾기
        for(int i = 0; i< n; i++ ){
            //만약 단 한번도 지각하지 않은 직원이라면
            if(people[i] == false) {
                //정답 + 1
                answer += 1;
            }

        }
        
        return answer;
    }
    
    
}