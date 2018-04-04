package com.zxchaos;

import java.util.Arrays;

/**
 * 字符串排序, 3向快速排序
 * @author zhangxin
 *
 */
public class Quick3String {
	
	private int charAt(String s, int d){
		if (d < s.length()) {
			return s.charAt(d);
		}
		return -1;
	}
	
	public void sort(String[] a, int lo, int hi, int d){
		if (lo >= hi) {
			return;
		}
		int v = charAt(a[lo], d);
		int lt = lo;
		int gt = hi;
		int i = lo+1;
		while(i <= gt){
			if (charAt(a[i], d) < v) {
				swap(a, i++, lt++);
			}else if (charAt(a[i], d) > v) {
				swap(a, i, gt--);
			} else {
				i++;
			}
		}
		
		sort(a, lo, lt-1, d);
		if (v >= 0) {
			sort(a, lt, gt, d+1);
		}
		sort(a, gt+1, hi, d);
	}
	
	private void swap(String[] a, int i, int j){
		String t = a[i];
		a[i] = a[j];
		a[j] = t;
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
		Quick3String q = new Quick3String();
		q.sort(a, 0, a.length-1, 0);
		Arrays.stream(a).forEach(System.out :: println);
	}
	

}
