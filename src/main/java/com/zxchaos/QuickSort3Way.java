package com.zxchaos;

/**
 * 3向快排
 * @author zhangxin
 *
 */
public class QuickSort3Way extends Sort {
	public static void sort(Comparable[] a){
		sort(a, 0, a.length-1);
	}
	
	public static void sort(Comparable[] a, int lo, int hi){
		if (lo >= hi) {
			return;
		}
		Comparable p = a[lo];
		int lt = lo;
		int gt = hi;
		int i = lo + 1;
		while(i <= gt){
			int res = a[i].compareTo(p);
			if (res > 0) {
				swap(a, i, gt--);
			} else if (res < 0) {
				swap(a, i++, lt++);
			} else {
				i++;
			}
		}
		sort(a, lo, lt-1);
		sort(a, gt+1, hi);
	}
	
	public static void main(String[] args){
		Comparable[] a = new Comparable[]{6,5,4,8,11,6,13,6,7,9,10,3};
		sort(a);
		printArray(a);
	}
}
