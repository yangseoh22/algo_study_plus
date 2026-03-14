import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2564_ssm {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st= new StringTokenizer(br.readLine());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int result =0;
		
		int storeNum = Integer.parseInt(br.readLine());
		int[] store = new int[storeNum+1];
		for(int i =0; i<storeNum+1;i++) {
			st = new StringTokenizer(br.readLine());
			int dir = Integer.parseInt(st.nextToken());
			int len = Integer.parseInt(st.nextToken());
			if(dir == 1) {
				store[i] = len;
			}
			else if(dir == 2) {
				store[i] = x + y + (x-len);
			}
			else if(dir == 3) {
				store[i] = x*2 + y + (y-len);
				
			}
			else if(dir == 4) {
				store[i] = x + len;
				
			}
			
		}
		int totalLen = x*2 + y*2;
		for(int i =0; i<storeNum;i++) {
			int a = Math.abs(store[storeNum] - store[i]);
			if( a < totalLen -a) {
				result += a;
			}
			else {
				result += totalLen-a;
			}
		}
		System.out.println(result);
		
		
	}

}
