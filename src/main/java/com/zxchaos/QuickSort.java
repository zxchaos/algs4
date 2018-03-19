package com.zxchaos;

public class QuickSort extends Sort {

	public static void sort(Comparable[] a) {
		sort(a, 0, a.length - 1);
	}

	public static void sort(Comparable[] a, int lo, int hi) {
		if (lo >= hi) {
			return;
		}
		int pi = partition(a, lo, hi);
		System.out.print("pi="+pi+", res:");
		printArray(a);
		sort(a, lo, pi);
		sort(a, pi+1, hi);
	}

	public static int partition(Comparable[] a, int lo, int hi) {

		Comparable p = a[lo];
		int i = lo;
		int j = hi + 1;
		while (true) {
			while (less(a[++i], p)) {
				if (i >= hi) {
					break;
				}
			}

			while (less(p, a[--j]))
				;

			if (i >= j) {
				break;
			}

			swap(a, i, j);
		}
		swap(a, lo, j);
		return j;
	}
	
	public static void main(String[] args){
		Comparable[] a = Sort.randomArray();
		printArray(a);
		sort(a);
		System.out.println("排序后");
		printArray(a);
	}
}
