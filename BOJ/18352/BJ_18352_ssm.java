//**시간**: 1708ms
//**메모리** : 350324 KB
//**전략** : dijkstra
//
//1. 최소거리를 저장할 배열 dist를 최대값으로 초기화 & 최소거리를 기준으로 정렬할 priority queue에 {시작점 , 0}(+ idist[시작점] = 0) 으로 초기화
//2. 다익스트라 함수 내에서 만약 해당 정점에 대해 저장된 최소 거리가 현재 꺼낸 최소거리보다 작다면 continue
//3. 현재 정점에 연결되어있는 정점들 중에 현재값 +1 이 dist[현재정점] 보다작다면 최솟값 갱신 후, 해당 정점번호와 거리값 pq 저장
//4. 모든 정점에 대해 최소 거리가 목표 거리와 같다면 list 에 add
//5. 만약 list가 비어있다면 -1 출력 / 아니라면 한줄에 하나씩 정점 출력
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_18352 {
	static PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
	static List<Integer>[] link;
	static int[] dist;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int X = Integer.parseInt(st.nextToken());
		link = new ArrayList[N + 1];
		dist = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			link[i] = new ArrayList<>();
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			link[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken()));
		}
		for (int i = 1; i <= N; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		dist[X] = 0;
		queue.add(new int[] { X, 0 });
		Dijk();
		List<Integer> result = new ArrayList<>();
		for (int i = 1; i <= N; i++) {
			if (dist[i] == K) {
				result.add(i);
			}
		}
		if (result.isEmpty()) {
			System.out.println("-1");
			return;
		}
//		Collections.sort(result);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}

	}

	static void Dijk() {
		while (!queue.isEmpty()) {
			int[] num = queue.poll();
			if(dist[num[0]] < num[1]) continue;

			for (int n : link[num[0]]) {
				if (dist[n] > num[1] + 1) {
					dist[n] = num[1] + 1;
					queue.add(new int[] { n, num[1] + 1 });

				}
			}
		}
	}

}
