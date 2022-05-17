class Solution_오답 {
	static int answer = 0;
	public int solution(String s) {
		int len = s.length();
		answer = len;

		for(int i = 1; i<=len; i++){
			cut(i, s);
		}

		return answer;
	}

	static void cut(int len, String s){
		String compareA = "";
		String compareB = "";
		String makeS = "";
		int idx = 0;
		int same = 1;
		for(int j = 0; j<len; j++){
			compareA += s.charAt(idx++);
		}
		if(s.length()/len>=2){
			while(idx<=s.length()-len){
				compareB = "";
				for(int j = 0; j<len; j++){
					compareB += s.charAt(idx++);
				}
				// System.out.println(compareB);
				if(compareA.equals(compareB)){
					if(idx<s.length()){
						same++;
					}else{
						same++;
						// if(same>10) same %= same;
						makeS += same+"";
					}
				}else{
					if(same!=1){
						// if(same>10) same %= same;
						makeS += same + compareA;
						same = 1;
						compareA = "";
					}else if(same == 1){
						makeS += compareA;
						compareA = "";
					}
					compareA = compareB;
				}
			}

			makeS += compareB;

			for(int i = idx; i<s.length(); i++){
				makeS += s.charAt(i);
			}
		}

		else{
			makeS = s;
		}

		// System.out.println(len + " : " +makeS);

		if(makeS.length()<answer) answer = makeS.length();

	}
}