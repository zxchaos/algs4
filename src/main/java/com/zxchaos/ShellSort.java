package com.zxchaos;

/**
 * 希尔排序
 * 
 * @author zhangxin
 */
public class ShellSort extends Sort {

	public static  void sort(Comparable[] a) {
		int l = a.length;
		int h = 1;
		while (h < l / 3) {
			h = 3 * h + 1;
		}
		while (h >= 1) {
			for (int i = h; i < l; i++) {
				for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) {
					swap(a, j, j - h);
				}
			}
			h = h / 3;
		}
	}
	
	public static void main(String[] args){
		Integer[] a = new Integer[]{6,13,56,12,10,9,8,4,1};
		sort(a);
		for(Integer r : a){
			System.out.println(r);
		}
	}
}
