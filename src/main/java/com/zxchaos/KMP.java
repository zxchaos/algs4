package com.zxchaos;

/**
 * 子字符串查找KMP算法
 * @author zhangxin
 */
public class KMP {

	private int[][] dfa;
	private int m;//模式长度
	private static final int R = 256;//字母表长度
	
	public KMP(String pattern){
		m = pattern.length();
		dfa = new int[R][m];
		createDFA(pattern);
	}
	
	private void createDFA(String pattern){
		dfa[pattern.charAt(0)][0] = 1;
		int x = 0;
		for(int j = 1; j < m; j++){
			
			for(int i = 0; i< R; i++){
				dfa[i][j] = dfa[i][x];
			}
			dfa[pattern.charAt(j)][j] = j+1;
			x = dfa[pattern.charAt(j)][x];
		}
	}
	
	public int match(String s){
		int i = 0;
		int j = 1;
		for(; i < s.length()&&j<m; i++){
			j = dfa[s.charAt(i)][j];
		}
		if (j == m) {
			return i - m;
		}
		return -1;
	}
	
	public static void main(String[] args){
		KMP kmp = new KMP("ababac");
		int index = kmp.match("ababababaababaaababac");
		System.out.println(index);
		
	}
}
