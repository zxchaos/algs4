package com.zxchaos;

import java.util.Random;

public class MergeSort extends Sort{
	public static void sort(Comparable[] a) {
		sort(a, 0, a.length-1);
	}
	
	public static void sort(Comparable[] a, int lo, int hi){
		if (lo >= hi) {//结束递归
			return;
		}
		int mid = lo + (hi - lo)/2;
		sort(a, lo, mid);
		sort(a, mid+1, hi);
		merge(a, lo, mid, hi);
	}
	/**
	 * 两个有序部分合并
	 * @param a
	 * @param lo
	 * @param mid
	 * @param hi
	 */
	public static void merge(Comparable[] a, int lo, int mid, int hi){
		int i = lo;
		int j = mid + 1;
		Comparable[] aux = new Comparable[a.length];
		for(int k = lo; k <= hi; k++){
			aux[k] = a[k];
		}
		//通过辅助数组aux 将a 数组变为有序数组
		for(int k = lo; k <= hi; k++){
			if (i > mid) {
				a[k] = aux[j++];
			} else if (j > hi) {
				a[k] = aux[i++];
			} else if (less(aux[i], aux[j])) {
				a[k] = aux[i++];
			} else {
				a[k] = aux[j++];
			}
		}
	}
	
	public static void main(String[] args){
		Random r = new Random();
		Integer[] a = new Integer[]{r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100)};
		System.out.println("排序前");
		for(Integer i : a){
			System.out.println(i);
		}
		sort(a);
		System.out.println("排序后");
		for(Integer i : a){
			System.out.println(i);
		}
	}
}
