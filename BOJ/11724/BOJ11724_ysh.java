package p0401;

/*
 * 시간 : 564ms
 * 메모리 : 14,5112kb
 * 전략 : DFS
 * 1. 양방향 그래프 생성
 * 2. 아직 방문하지 않은 노드에 대해 dfs진행
 * 3. 방문했다면 return / 안했다면 방문 표시 및 연결 노드에 대해 재귀 호출
 */


import java.util.*;
import java.io.*;

public class BOJ11724 {
	static ArrayList<Integer>[] graph;
	static int N, M, cnt=0;
	static boolean[] visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			graph[s].add(e);
			graph[e].add(s);
		}
		
		visited = new boolean[N+1];
		
		for(int i=1; i<=N; i++) {
			if(!visited[i]) {
				DFS(i);
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}
	
	private static void DFS(int node) {
		if(visited[node]) return;
		visited[node] = true;
		
		for(int n : graph[node]) {
			if(!visited[n]) DFS(n);
		}
	}
	
}
