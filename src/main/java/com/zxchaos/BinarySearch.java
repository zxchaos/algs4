package com.zxchaos;

public class BinarySearch {
	/**
	 * 对有序数组进行二分查找
	 * @param a
	 * @param key
	 * @return 返回key所在a中的索引, 没有则返回-1
	 */
	public static int binarySearch(int[] a, int key){
		int lo = 0;
		int hi = a.length-1;
		while(lo <= hi){
			int mid = lo + (hi - lo)/2;
			if (key < a[mid]) {
				hi = mid - 1;
			} else if (key > a[mid]) {
				lo = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}
	
	/**
	 * 递归版本实现
	 * @param a
	 * @param lo
	 * @param hi
	 * @return key 在a中的索引
	 */
	public static int bsr(int[] a , int lo, int hi, int key){
		if (lo > hi) {//递归结束
			return -1;
		}
		int mid = lo + (hi-lo)/2;
		if (key < a[mid]) {
			return bsr(a, lo, mid-1, key);
		} else if (key > a[mid]) {
			return bsr(a, mid+1, hi, key);
		} else {
			return mid;
		}
	}
	
	public static void main(String[] args){
		int[] a = new int[]{1,3,5,6,7,9,11};
		System.out.println(bsr(a, 0, a.length-1, 2));
	}
}
