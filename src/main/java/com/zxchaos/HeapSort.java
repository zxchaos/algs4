package com.zxchaos;

/**
 * 堆排序, 使用最大二叉堆实现排序
 * @author zhangxin
 *
 */
public class HeapSort extends Sort{

	public static  void sort(Comparable[] a){
		//构造最大二叉堆
		int n = a.length;
		for(int k = n/2; k >= 1; k--){
			sink(a, k, n);
		}
		System.out.print("构造二叉堆完成");
		printArray(a);
		while(n > 1){
			swap(a, 1, n--);
			System.out.print("n="+n+",swap后:");
			printArray(a);
			sink(a, 1, n);//使剩余堆有序
			System.out.print("n="+n+",sink后:");
			printArray(a);
			
		}
	}
	
	/**
	 * k, 表示位置, n表示个数, 均不表示索引
	 * @param a
	 * @param k
	 * @param n 
	 */
	private static void sink(Comparable[] a, int k, int n) {
		while(2*k <= n){
			int temp = 2*k;
			if (2*k < n && less(a[2*k-1], a[2*k+1-1])) {
				temp = 2*k+1;
			}
			if (!less(a[k-1], a[temp-1])) {
				break;
			}
			swap(a, k, temp);
			k = temp;
		}
	}
	
	public static void swap(Comparable[] a, int i , int j){
		Comparable temp = a[i-1];
		a[i-1] = a[j-1];
		a[j-1] = temp;
	}
	
	
	public static void main(String[] args){
//		Comparable[] a = new Comparable[]{33,71, 78, 80, 62, 64, 73, 3, 67};
		Comparable[] a = randomArray();
		printArray(a);
		sort(a);
		System.out.println("排序完成");
		printArray(a);
	}
}
