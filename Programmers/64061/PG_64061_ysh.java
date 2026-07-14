import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int cnt = 0;
        
        Stack<Integer> basket = new Stack<>();
        int N = board[0].length;  // 격자 크기 N
        
        // 크레인 이동
        for(int m=0; m<moves.length; m++){
            // 크레인이 내려갈 열
            int col = moves[m] - 1;  // 인덱스라 1 빼주기
            
            // 크레인 내려가기
            for(int r=0 ; r<N; r++){
                // 인형이 없으면 무시
                if(board[r][col] == 0)continue;
                
                // 인형이 있다면 뽑기
                int now = board[r][col];
                board[r][col] = 0;
                
                // 바구니에 인형이 있고, 최상단과 같다면 터지기
                if(!basket.isEmpty() && now == basket.peek()) {
                    cnt += 2;
                    basket.pop();
                }
                // 다르면 쌓기
                else {
                    basket.add(now);
                }
                
                break;
            }
            
        }
        
        return cnt;
    }
}