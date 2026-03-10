package s0310;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;



public class BOJ4386 {
	static class Edge implements Comparable<Edge>{
		int s, e;
		double w;
		public Edge(int s, int e, double w) {
			this.s = s;
			this.e = e;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.w, o.w);
		}
	}
	
	static double[][] stars;
	static ArrayList<Edge> edges;
	static int[] uf;  //
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		
		stars = new double[N][3];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			stars[i][0] = Double.parseDouble(st.nextToken());
			stars[i][1] = Double.parseDouble(st.nextToken());
		}
		
		edges = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                double dist = Math.sqrt(Math.pow(stars[i][0] - stars[j][0], 2) + Math.pow(stars[i][1] - stars[j][1], 2));
                edges.add(new Edge(i, j, dist));
            }
        }
		
		// 서로소 집합 생성
		uf = new int[N];
		for(int i=0; i<N; i++) {
			uf[i] = i;
		}
		
		Collections.sort(edges);
		
		int cnt = 0;
		double result = 0;
		for(Edge ed : edges) {
			if(union(ed.s, ed.e)) {  //사이클이 발생하지 않는다면,
				cnt++;  // 연결
				result += ed.w;  // 가중치 누적
				if(cnt == N-1) break;
			}
		}
		
		System.out.printf("%.2f", result);
	}

	private static boolean union(int i, int j) {
		int ns = find(i);
		int ne = find(j);
		if(ns==ne) return false;
		uf[ns] = ne;
		return true;
	}

	private static int find(int x) {
		if(uf[x]==x) return x;
		int nx = find(uf[x]);
		uf[x] = nx;
		return nx;
	}

}
