package p0320;

/* 시간 : 328ms
 * 메모리 :69716kb
 * 전략 : 구현
 * 1. (0, 0)에서 시작해서 숫자를 줄여주며 넣어주는 방식
 * 2. 현재 위치에 숫자를 넣고, 현재 바라보는 방향으로 위치를 갱신
 * 3. 아래 -> 우 -> 위 -> 좌 순서로 회전할 수 있게 배열 생성
 * 4. 만약, 갱신된 위치가 좌표를 벗어나거나 0(초기화상태)이 아닌 경우 회전
 * 5. 현재 넣은 숫자가 1이면 종료
 * 6. 완성된 맵과 그 맵에서 입력 받은 숫자의 좌표(인덱스 + 1 형태)를 출력
 */

import java.io.*;
import java.util.*;

public class BOJ1913 {
	// 남 -> 동 -> 북 -> 서 이동
	static int[] dr = {1, 0, -1, 0}; 
	static int[] dc = {0, 1, 0, -1}; 
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		int num = Integer.parseInt(br.readLine());
		
		int[][]	map = new int[N][N];
		
		int snail = N*N;
		int nowX = 0;
		int nowY = 0;
		int dir = 0;  // 남쪽
		while(true) {
			map[nowX][nowY] = snail;
			if(snail == 1) break;
			
			nowX += dr[dir];
			nowY += dc[dir];
			
			// 맵을 벗어나거나 이미 채워진 곳을 만나면 회전
			if(nowX<0 || nowY<0 || nowX>=N || nowY>=N || map[nowX][nowY] != 0) {
				nowX -= dr[dir];
				nowY -= dc[dir];
				dir = (dir + 1)%4;
				nowX += dr[dir];
				nowY += dc[dir];
			}
		}
		
		// 좌표 찾기
		int numX = -1;
		int numY = -1;
		for(int r=0; r<N; r++) {
			for(int c=0; c<N; c++) {
				sb.append(map[r][c]).append(" ");
				if(map[r][c] == num) {
					numX = r+1;
					numY = c+1;
				}
			}
			sb.append("\n");
		}
		
		sb.append(numX).append(" ").append(numY);
		System.out.println(sb);
	}

}
