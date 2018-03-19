package com.zxchaos;

/**
 * 最小优先队列
 * @author zhangxin
 *
 */
public class MinPQ<T extends Comparable> extends Sort{
	private T[] a;
	/**
	 * 堆大小
	 */
	private int n;
	public MinPQ(int max){
		a = (T[])new Comparable[max + 1];
	}
	
	public void insert(T item){
		a[++n] = item;
		swim();
	}
	
	public T delMin(){
		T min = a[1];
		swap(a, 1, n--);
		a[n+1] = null;
		sink();
		return min;
	}
	
	public int size(){
		return n;
	}
	
	public boolean isEmpty(){
		return n == 0;
	}
	
	/**
	 * 上浮最后一个元素, 使堆有序化
	 */
	private void swim(){
		int k = n;
		while(k > 1 && less(a[k], a[k/2])){
			swap(a, k, k/2);
			k = k/2;
		}
	}
	/**
	 * 下沉第一个元素, 使堆有序化
	 */
	private void sink(){
		int k = 1;
		while(2*k < n){
			int temp ;
			if (less(a[2*k], a[2*k+1])) {
				temp = 2*k;
			} else {
				temp = 2*k+1;
			}
			if (!less(a[temp], a[k])) {
				break;
			}
			swap(a, k, temp);
			k = temp;
		}
	}
	
	public static void main(String[] args){
		MinPQ<Integer> minPQ = new MinPQ<>(6);
		minPQ.insert(10);
		minPQ.insert(9);
		minPQ.insert(6);
		minPQ.insert(7);
		minPQ.insert(3);
		minPQ.insert(1);
		while(!minPQ.isEmpty()){
			System.out.println(minPQ.delMin());
		}
		
	}
}
