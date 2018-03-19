package com.zxchaos;

/**
 * 插入排序
 * @author zhangxin
 */
public class Insertion {
	
	/**
	 * 将数组a排序
	 * @param a
	 */
	public static void sort(Comparable[] a){
		for(int i = 1; i < a.length; i++){
			for(int j = i; j > 0 && less(a[j], a[j-1]); j--){
				swap(a, j, j-1);
			}
		}
	}

	/**
	 * 交换数组a中索引i,j的值
	 * @param a
	 * @param i
	 * @param j
	 */
	public static void swap(Comparable[]a, int i, int j ){
		Comparable t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	/**
	 * 
	 * @param ai
	 * @param aj
	 * @return ai 是否 小于 aj
	 */
	public static boolean less(Comparable ai, Comparable aj){
		int res = ai.compareTo(aj);
		return res < 0;
	}
	
	public static void main(String[] args){
		Integer[] a = new Integer[]{9,5,6,7,11,1,13,8};
		Insertion.sort(a);
		for(Integer i : a){
			System.out.println(i);
		}
	}
}
