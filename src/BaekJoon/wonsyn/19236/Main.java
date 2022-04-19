package a0419_bj_19236_청소년상어;

import java.io.*;
import java.util.*;

public class Main {
	static class Shark {
		int r, c, d, sum;
		Shark(int r, int c, int d, int sum) {
			this.r = r; this.c = c; this.d = d; this.sum = sum;
		}
	}
	
	static class Fish implements Comparable<Fish>{
		int r, c, num, d;
		boolean isAlive;

		Fish(int r, int c, int num, int d, boolean isAlive) {
			this.r = r; this.c = c; this.num = num; this.d = d; this.isAlive = isAlive;
		}
		
		@Override
		public int compareTo(Fish o) {
			return Integer.compare(this.num, o.num);
		}
		
		@Override
		public String toString() {
			return num + "";
		}
	}

	static int[][] del = {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};
	static int max;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input_bj_19236.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		ArrayList<Fish> fishList= new ArrayList<>();
		int[][] map = new int[4][4];
		Shark shark = new Shark(0, 0, 0, 0);
		max = Integer.MIN_VALUE;
		
		for(int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < 4; j++) {
				Fish fish = new Fish(i, j, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()) - 1, true);
				fishList.add(fish);
				map[i][j] = fish.num;
			}
		}
		
		Collections.sort(fishList);
		eatFish(map, 0, 0, shark, fishList);
		
		next(map, shark, fishList);
		
		System.out.print(max);
		br.close();
	}
	
	static void next(int[][] map, Shark srk, ArrayList<Fish> list) {
		if(max < srk.sum) {
			max = srk.sum;
		}
		
		fishMove(map, list);
		
		sharkMove(map, srk, list);
	}
	
	static void fishMove(int[][] map, ArrayList<Fish> list) {
		for(Fish f : list) {
			if(!f.isAlive) continue;
			int cnt = 0;
			while(cnt < 8) {
				int r = f.r + del[(f.d + cnt) % 8][0];
				int c = f.c + del[(f.d + cnt) % 8][1];
				if(0 <= r && r < 4 && 0 <= c && c < 4 && map[r][c] != -1) {
					swapFish(f.num, r, c, list, map);
					f.d = (f.d + cnt) % 8;
					break;
				} else {
					cnt++;
				}
			}
		}
	}
	
	static void sharkMove(int[][] map, Shark srk, ArrayList<Fish> list) {
		int cnt = 0;
		while(true) {
			cnt++;
			int r = srk.r + cnt * del[srk.d][0];
			int c = srk.c + cnt * del[srk.d][1];
			
			if(!(0 <= r && r < 4 && 0 <= c && c < 4)) break;
			if(map[r][c] == 0) continue;
			
			int[][] newMap = copy(map);
			ArrayList<Fish> newList = copy(list);
			Shark tempShark = copy(srk);
			eatFish(newMap, r, c, tempShark, newList);
			next(newMap, tempShark, newList);
		}
	}
	
	static void eatFish(int[][] map, int r, int c, Shark srk, ArrayList<Fish> list) {
		Fish fish = list.get(map[r][c] - 1);
		map[srk.r][srk.c] = 0;
		srk.r = r;
		srk.c = c;
		srk.d = fish.d;
		srk.sum += fish.num;
		fish.isAlive = false;
		map[srk.r][srk.c] = -1;
	}
	
	static void swapFish(int num, int r, int c, ArrayList<Fish> list, int[][] map) {
		if(map[r][c] == 0) {
			Fish fish = list.get(num - 1);
			map[fish.r][fish.c] = 0;
			map[r][c] = fish.num;
			fish.r = r; fish.c = c;
		} else {
			Fish f1 = list.get(num - 1);
			Fish f2 = list.get(map[r][c] - 1);
			map[f1.r][f1.c] = f2.num; 
			map[f2.r][f2.c] = f1.num;
			int tempR = f1.r, tempC = f1.c;
			f1.r = f2.r; f1.c = f2.c;
			f2.r = tempR; f2.c = tempC;
		}
	}
	
	static int[][] copy(int[][] map) {
		int[][] newMap = new int[4][4];
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				newMap[i][j] = map[i][j];
		return newMap;
	}
	static ArrayList<Fish> copy(ArrayList<Fish> list) {
		ArrayList<Fish> newList = new ArrayList<>();
		for(Fish f : list) newList.add(new Fish(f.r, f.c, f.num, f.d, f.isAlive));
		return newList;
	}
	static Shark copy(Shark shark) {
		return new Shark(shark.r, shark.c, shark.d, shark.sum);
	}
}
