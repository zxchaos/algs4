package com.zxchaos;

import java.util.Arrays;

/**
 * 字符串数组, 低有效位排序; 数组中所有字符串等长
 * @author zhangxin
 *
 */
public class LSD {

	/**
	 * 字母表大小
	 */
	private static final int R = 256;
	public static void sort(String[] a){
		int w = a[0].length();
		
		for(int d = w-1; d >=0 ; d--){
			int[] count = new int[R + 1];
			for(int i = 0; i < a.length; i++){
				count[a[i].charAt(d)+1]++; 
			}
			
			for(int i = 0; i < R; i++){// 将频率变成索引
				count[i+1] += count[i];
			}
			String[] aux = new String[a.length];
			for(int i = 0 ; i<a.length; i++){
				aux[count[a[i].charAt(d)]++]=a[i];
			}
			
			for(int i = 0; i<a.length; i++){
				a[i] = aux[i];
			}
		}
		
	}
	
	public static void main(String[] args){
		String[] a = new String[]{
				"afucdeimc",
				"amcdomvji",
				"ailsmcdif",
				"a234bdnis"
		};
		sort(a);
		Arrays.stream(a).forEach(System.out::println);
	}
	
}
