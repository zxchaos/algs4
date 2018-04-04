package com.zxchaos;

import java.util.Arrays;

/**
 * 字符串数组最高有效位排序, 数组中的各字符串长度不固定
 * @author zhangxin
 *
 */
public class MSD {

	private static final int R = 256;
	
	
	public void sort(String[] a){
		sort(a, 0, a.length-1, 0);
	}
	
	
	private int charAt(String s, int d){
		if (d < s.length()) {
			return s.charAt(d);
		}
		return -1;
	}
	/**
	 * 
	 * @param a
	 * @param lo 考察数组上界
	 * @param hi 考察数组下界
	 * @param d 按每个字符串的第d位排序
	 */
	public void sort(String[] a, int lo, int hi, int d){
		if (lo >= hi) {
			return;
		}
		int[] count = new int[R+2];
		for(int i = lo; i <= hi; i++){//计算频率
			count[charAt(a[i], d)+2]++;
		}
		
		// 将频率转化为索引
		for(int i = 0; i < R+1; i++){
			count[i+1] += count[i];
		}
		
		String[] aux = new String[a.length];
		for(int i = lo; i <= hi; i++){//使用辅助素组排序
			aux[count[charAt(a[i], d)+1]++] = a[i];
		}
		
		for(int i = lo; i <= hi; i++){//使用有序的aux数组覆盖a数组对应区间
			a[i] = aux[i-lo];
		}
		
		for(int i = 0; i < R+1; i++){
			sort(a, lo+count[i], lo + count[i+1]-1, d+1);
		}
	}
	
	public static void main(String[] args){
		String[] a = new String[]{
				"she",
				"sells",
				"the",
				"seashell",
				"on",
				"the",
				"shore"
		};
		MSD msd = new MSD();
		msd.sort(a);
		
		Arrays.stream(a).forEach(System.out :: println);
	}
}
