package com.zxchaos;

public class MaxPQ<T extends Comparable<T>> extends Sort {

	private T[] a;
	private static final int defaultLength = 10;
	private int n;// 二叉堆大小

	public MaxPQ() {
		this(defaultLength);
	}

	public MaxPQ(int length) {
		a = (T[]) new Comparable[length + 1];
	}

	public boolean isEmpty(){
		return n == 0;
	}
	
	public int size(){
		return n;
	}
	public T delMax() {
		if (isEmpty()) {
			return null;
		}
		T max = a[1];
		swap(a, 1, n--);
		a[n + 1] = null;
		sink();
		if (n < a.length/2) {
			resize(a.length/2);
		}
		return max;
	}

	/**
	 * 将第一个元素下沉
	 */
	private void sink() {
		int i = 1;
		while (2 * i < n) {
			int t = 2 * i;
			if (less(a[t], a[t + 1])) {
				t = t + 1;
			}
			if (less(a[i], a[t])) {
				swap(a, i, t);
				i = t;
			} else {
				break;
			}
		}
	}

	public void add(T t) {
		if (n == a.length-1) {
			resize(a.length*2);
		}
		a[++n] = t;
		swim();
	}

	/**
	 * 将堆的最后一个元素上浮
	 */
	private void swim() {
		int i = n;
		while (i > 1) {
			int t = i / 2;
			if (less(a[t], a[i])) {
				swap(a, t, i);
				i = t;
			} else {
				break;
			}

		}
	}
	
	private void resize(int cap){
		T[] temp = (T[])new Comparable[cap];
		for(int i = 0; i <= n; i++){
			temp[i] = a[i];
		}
		a = temp;
	}

	
	public static void main(String[] args){
		MaxPQ<Integer> a = new MaxPQ<>(10);
		a.add(1);
		a.add(3);
		a.add(6);
		a.add(9);
		System.out.println(a.delMax());
		System.out.println(a.delMax());
		System.out.println(a.delMax());
		System.out.println(a.delMax());
		System.out.println(a.isEmpty());
	}
}
