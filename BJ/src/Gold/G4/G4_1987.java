package src.Gold.G4;
/**
 * @author Minji Lee
 * @date 2024.02.16
 * @link  https://www.acmicpc.net/problem/1987
 * @keyword_solution  dfs 활용
 * @input 1<= R,C <= 20
 * @output 방문했던 알파벳이 겹치지 않고 갈 수 있는 최대 말의 움직임 수
 * @time_complex  O(N^2)
 * @perf 15968	4172 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class G4_1987 {
	static int R, C;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static List<Character> visited = new ArrayList<>();
	static char board[][];
	static StringTokenizer tokens;
	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	static int result = 0;
	public static void main(String[] args) throws IOException {
		
		tokens = new StringTokenizer(br.readLine());
		R = Integer.parseInt(tokens.nextToken());
		C = Integer.parseInt(tokens.nextToken());
		board = new char[R][C];
		for(int i = 0; i < R; i++) {
			String tmp = br.readLine();
			board[i] = tmp.toCharArray();
		}
		
		dfs(0,0,0);
		System.out.println(result);
	}

	private static void dfs(int r, int c, int cnt) {
		
		cnt++;
		result = Math.max(result,cnt);
		visited.add(board[r][c]);
		
		for(int i = 0; i < 4; i++) {
			int dr = r + dx[i];
			int dc = c + dy[i];
			
			if(dr < 0 || dr >= R || dc < 0|| dc >= C) continue;
			if(visited.contains(board[dr][dc])) continue;
			dfs(dr,dc, cnt);
		}
		visited.remove(visited.size()-1);

	}

}