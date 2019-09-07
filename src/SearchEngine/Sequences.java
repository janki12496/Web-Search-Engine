package SearchEngine;

import java.util.Random;

public class Sequences {

	public static int editDistance(String word1, String word2) {
		int len1 = word1.length();
		int len2 = word2.length();
	 
		// len1+1, len2+1, because finally return dp[len1][len2]
		int[][] dp = new int[len1 + 1][len2 + 1];
	 
		for (int i = 0; i <= len1; i++) {
			dp[i][0] = i;
		}
	 
		for (int j = 0; j <= len2; j++) {
			dp[0][j] = j;
		}
	 
		//iterate though, and check last char
		for (int i = 0; i < len1; i++) {
			char c1 = word1.charAt(i);
			for (int j = 0; j < len2; j++) {
				char c2 = word2.charAt(j);
	 
				//if last two chars equal
				if (c1 == c2) {
					//update dp value for +1 length
					dp[i + 1][j + 1] = dp[i][j];
				} else {
					int replace = dp[i][j] + 1;
					int insert = dp[i][j + 1] + 1;
					int delete = dp[i + 1][j] + 1;
	 
					int min = replace > insert ? insert : replace;
					min = delete > min ? min : delete;
					dp[i + 1][j + 1] = min;
				}
			}
		}
	 
		return dp[len1][len2];
	}
	
	public static void main(String[] args) {
		long totaltime=0;
        Random r = new Random( );
		String s1r = "";
		String s2r = "";
		int len = 100;
       
		for ( int c =0; c<1000;c++){
			s1r=s2r="";
        for( int j = 0; j < len; j++ ){
            s1r += (char) ( 'a' + r.nextInt(26));
            s2r += (char) ( 'a' + r.nextInt(26));
        }
        long t1= System.nanoTime();
		int dr = editDistance(s1r, s2r);
		long t2 = System.nanoTime();
		totaltime += t2-t1;  // totaltime = totaltime + (t2-t1);

	}//1000 loop
		System.out.println("avg dist for 1000 random pairs of string with len "+ len +" is "+totaltime/1000);

}//main
}//class