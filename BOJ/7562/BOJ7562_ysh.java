package p1027;

/*
 * 시간 : 232ms
 * 메모리 : 76192
 * 전략 : BFS
 * 1. N * N 맵을 만들었다고 가정, 시작점과 도착점 입력 받기
 * 2. 방문 표시 배열 visited를 -1로 초기화
 * 3. 시작점 부터 BFS 진행. 단, visited를 0으로
 * 4. 나이트가 이동 가능한 8방향 탐색을 진행하며, 맵 내의 범위면서 아직
 *    방문하지 않은 곳(-1)이면 지금까지의 visited배열 값+1 한 값을 새로운 visited에 저장
 * 5. visited의 도착점에 저장된 값이 가장 먼저 도착한 거리 값 
 */

import java.util.*;
import java.io.*;

public class BOJ7562_ysh {
	static int N, startX, startY, goalX, goalY;
	static int[][] map;
	static int[][] visited;
	static int[] dx = {-1, -2, -2, -1, 1, 2, 2, 1};
	static int[] dy = {-2, -1, 1, 2, -2, -1, 1, 2};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			st = new StringTokenizer(br.readLine());
			startX = Integer.parseInt(st.nextToken());
			startY = Integer.parseInt(st.nextToken());
		
			st = new StringTokenizer(br.readLine());
			goalX = Integer.parseInt(st.nextToken());
			goalY = Integer.parseInt(st.nextToken());
			
			visited = new int[N][N];
			for(int i=0; i<N; i++) {
				Arrays.fill(visited[i], -1);  // 방문하기 전 -1 초기화
			}
			
			BFS(startX, startY);
			
			int min = visited[goalX][goalY];
			
			sb.append(min).append("\n");
		}
		System.out.println(sb);
	}

	private static void BFS(int sx, int sy) {
		Queue<int[]> q = new LinkedList<>();
		visited[sx][sy] = 0;
		q.offer(new int[] {sx, sy});
		
		while(!q.isEmpty()) {
			int[] now = q.poll();
			int x = now[0];
			int y = now[1];

			if(x==goalX && y==goalY) break;
			
			for(int i=0; i<8; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx>=0 && nx<N && ny>=0 && ny<N) {
					if(visited[nx][ny] == -1) {
						visited[nx][ny] = visited[x][y] + 1;
						q.offer(new int[] {nx, ny});
					}
				}
			}
		}
	
	}

}
