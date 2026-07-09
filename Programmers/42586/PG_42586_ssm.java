import java.util.ArrayList;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        //총 배포가능한 일 수가 정확이 몇개가 나올지 모르기에 가변 리스트 할당
        ArrayList<Integer> list = new ArrayList<>();
        
        //작업 완료까지 필요한 일 수 계산할 배열
        int[] days = new int[progresses.length];
        
        for(int i =0 ; i< progresses.length;i++){
            //left: 기능의 개발까지 얼마나 남았는지
            int left = 100 - progresses[i];
            //daysleft: 기능 완료까지 몇일 걸리는가
            // 남은 작업량 / 하루 작업 가능량
            int daysleft = left / speeds[i];
            //만약 남은 작업량이 더 있다면
            if(left % speeds[i] > 0){
                //하루 더 추가
                daysleft += 1;
            }
            
            days[i] = daysleft;
        }
        
        // 무조건 첫번째 작업이 끝나야 다음작업도 배포가능하므로 기준을 첫번째 작업으로 잡음
        //현재 배열에서 가리키는 위치(1부터 시작)
        int n = 1;
        //이때까지 필요한 가장 큰 일차
        int maxday = days[0];
        //한번에 배포 가능한 작업의 수
        int num =1;
        
        while(n <= progresses.length){
            //만약 마지막 기능이었다면
            if(n == progresses.length){
                //마지막으로 한번에 배포 가능한 작업 저장하기
                list.add(num);
                break;
            }
            //만약 마지막 기능이 아니고 남은 일 수가 최대 일 수 보다 적다면
            if(days[n] <= maxday){
                //한번에 배포가능한 기능 +1
                num += 1;
            }
            //만약 남은 일수가 최대 일수보다 많다면 앞의 작업과 함깨 배포가 불가능하므로
            else{

                list.add(num);
                num = 1;
                //최대 필요 일수 업데이트
                maxday = days[n];
            }  
            n += 1;
        }
        //제출을 위한 answer 배열
        int[] answer = new int[list.size()];
    for(int i =0; i< list.size(); i++){
        answer[i] = list.get(i);
    }
        return answer;
    }
}