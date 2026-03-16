//시간 : 168ms
//메모리 : 46680KB
//**전략** : 
//
//1. 시작점과 끝점을 입력받는다
//2. 시작점에서 시작하여 8방 탐색 하여 나이트가 갈 수 있는 모든 길을 queue에 저장
//3. queue에는 x좌표, y좌표, 현재 index(몇번 움직였는가)를 저장한다
//4. 만약 꺼낸 위치가 도착점이라면 index 저장 후 BFS 탐색 종료
//5. 결과출력
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_7562_ssm {
	static int N;
	static int[] end;
	static Queue<int[]> queue = new ArrayDeque<>();
	static int result =0;
	static int[] dx = {-2,-2,-1,1,2,2,1,-1}, dy = {-1,1,2,2,1,-1,-2,-2};
	static boolean[][] visited;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		
		for(int t =1;t<=TC;t++) {
			result =0;
			queue = new ArrayDeque<>();
			end = new int[3];
			N = Integer.parseInt(br.readLine());
			visited = new boolean[N][N];
			st = new StringTokenizer(br.readLine());
			int Kx = Integer.parseInt(st.nextToken());
			int Ky = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			end[0] = Integer.parseInt(st.nextToken());
			end[1] = Integer.parseInt(st.nextToken());
			queue.add(new int[] {Kx,Ky,0});
			visited[Kx][Ky] = true;
			BFS();
			sb.append(result).append("\n");
		}
		System.out.println(sb.toString());
	}
	static void BFS() {
		while(!queue.isEmpty()) {
			int[] xy = queue.poll();
			int x = xy[0];
			int y = xy[1];
			int idx = xy[2];
			if(x == end[0] && y == end[1]) {
				result = idx;
				return;
			}
			for(int i =0;i<8;i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx >= 0 && nx <N && ny >= 0 && ny < N && visited[nx][ny] == false) {
					visited[nx][ny] = true;
					queue.add(new int[] {nx,ny,idx+1});
				}
			}
			
		}
		
	}

}
