package com.zxchaos;

import java.util.Random;

public abstract class Sort {
	/**
	 * 交换数组a中的位置i,j的值
	 * @param a
	 * @param i
	 * @param j
	 */
	public static void swap(Comparable[] a, int i, int j){
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	public static boolean less(Comparable ai, Comparable aj){
		int res = ai.compareTo(aj);
		return res<0;
	}
	
	public static Comparable[] randomArray(){
		Random r = new Random();
		Comparable[] a = new Integer[]{r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100),r.nextInt(100)};
		return a;
	}
	
	public static void printArray(Comparable[] a){
		StringBuilder res = new StringBuilder("[");
		for(Comparable i : a){
			res.append(i).append(" ");
		}
		res.append("]");
		System.out.println(res.toString());
	}
}
