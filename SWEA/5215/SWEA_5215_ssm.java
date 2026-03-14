//시간 : 153 ms
// 메모리 : 26,496 kb


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA_5215_ssm {
	static int N, L;
	static int[] taste;
	static int[] Kal;
	static int result;
	static boolean[] choice;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int TC = Integer.parseInt(br.readLine());
		for (int t = 1; t <= TC; t++) {
			result = 0;
			sb.append("#").append(t).append(" ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			taste = new int[N];
			Kal = new int[N];
			choice = new boolean[N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				taste[i] = Integer.parseInt(st.nextToken());
				Kal[i] = Integer.parseInt(st.nextToken());

			}
			Back(0, 0, 0);
			sb.append(result).append("\n");
		}
		System.out.println(sb.toString());
	}

	static void Back(int idx, int score, int K) {
		if (idx == N) {
			if (K <= L) {
				result = Math.max(result, score);
			}
			return;
		}

		choice[idx] = true;
		Back(idx + 1, score + taste[idx], K + Kal[idx]);
		choice[idx] = false;
		Back(idx + 1, score, K);

	}

}
