package p0313;

/*
 * 시간 : 458ms
 * 메모리  : 60,728kb
 * 전략 : 다익스트라
 * 1. 모든 입력 받기 -  그래프 생성하기 / G-H 간선의 가중치는 따로 기록해두기
 * 2. 시작점, G, H 을 기준으로 다익스트라 실행
 * 3-1. 시작점-G 간의 최단 거리 + G-H의 가중치 + H-목적지 간의 최단거리 = 시작점-목적지 간의 최단거리 라면, G-H 간선을 지나간 것이라 판단
 * 3-2. 시작점-H 간의 최단 거리 + G-H의 가중치 + G-목적지 간의 최단거리 = 시작점-목적지 간의 최단거리 라면, G-H 간선을 지나간 것이라 판단
 * 4. 3에서 G-H 간선을 지났다고 판단된 것들만 출력
 */

import java.io.*;
import java.util.*;

public class BOJ9370 {
	static final int INF = (int) 1e8;
	static int N, M, T, S, G, H;
	static ArrayList<Edge>[] graph;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=TC; t++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());  // 노드 수
			M = Integer.parseInt(st.nextToken());  // 간선 수
			T = Integer.parseInt(st.nextToken());  // 목적지 후보 수
			
			st = new StringTokenizer(br.readLine());
			S = Integer.parseInt(st.nextToken());  // 시작점
			G = Integer.parseInt(st.nextToken());  // 지난 간선의 노드1
			H = Integer.parseInt(st.nextToken());  // 지난 간선의 노드2
			
			graph = new ArrayList[N+1];
			for(int i=1; i<=N; i++) {
				graph[i] = new ArrayList<>();
			}
			
			// 간선
			int tmpD = 0;
			for(int i=0; i<M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());  // 가중치
				
				// 양방향 연결
				graph[a].add(new Edge(b, d));
				graph[b].add(new Edge(a, d));
				
				if((a == G && b == H) || (a == H && b == G)) {
                    tmpD = d;
                }
			}
			
			// 목적지 후보
			int[] goal = new int[T];
			for(int i=0; i<T; i++) {
				goal[i] = Integer.parseInt(br.readLine().trim());
			}
			
			// 시작 -> 무조건 지나간다는 간선을 지나갔는지 확인
			int[] distS = dijk(S);
			int[] distG = dijk(G);
			int[] distH = dijk(H);
			ArrayList<Integer> result = new ArrayList<>();
			for(int g : goal) {
				long path1 = (long)distS[G] + tmpD + distH[g];
				long path2 = (long)distS[H] + tmpD + distG[g];
				
				if(distS[g] != INF && (path1 == distS[g] || path2 == distS[g]))
					result.add(g);
			}
			
			Collections.sort(result);
			for(int r : result) {
				sb.append(r).append(" ");
			}
			sb.append("\n");
		}	
		System.out.println(sb);
	}
	
	private static int[] dijk(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		int[] dist = new int[N+1];
		Arrays.fill(dist, INF);
		
		dist[start] = 0;
		pq.offer(new Edge(start, 0));
		
		while(!pq.isEmpty()) {
			Edge curr = pq.poll();
			
			if(curr.weight != dist[curr.to]) continue;
			
			for(Edge next : graph[curr.to]) {
				int newDist = dist[curr.to] + next.weight;
				if(newDist < dist[next.to]) {
					dist[next.to] = newDist;
					pq.offer(new Edge(next.to, newDist));
				}
			}
		}
		
		return dist;
	}

	public static class Edge implements Comparable<Edge>{
		int to, weight;
		
		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge e) {
			return this.weight - e.weight;
		}
	}
}
