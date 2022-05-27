class Solution {
	static int answer = 0;
	public int solution(String s) {
		int len = s.length();
		answer = len;

		for(int i = 1; i<=len/2; i++){
			cut(i, s);
		}

		return answer;
	}

	static void cut(int len, String s){
		String compareA = s.substring(0, len);
		String makeS = "";
		int comp = 0;
		int same = 1;

		for(int i = len; i<=s.length()-len; i+=len){
			if(compareA.equals(s.substring(i, i+len))){
				same++;
			}
			else{
				if(same>1){
					makeS += same+"";
				}
				makeS+=compareA;
				compareA = s.substring(i, i+len);
				same = 1;
			}
		}

		if(same>1){
			makeS += same+"";
		}
		makeS += compareA;

		int div = s.length() % len;

		answer = Math.min(answer, makeS.length()+div);
	}
}