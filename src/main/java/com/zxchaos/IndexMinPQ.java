package com.zxchaos;

import java.nio.channels.Pipe.SinkChannel;

/**
 * 索引优先队列
 * @author zhangxin
 *
 * @param <K>
 */
public class IndexMinPQ<K extends Comparable<K>> {

	private K[] keys;
	private int[] pq;
	private int[] map;
	private int n;
	
	public IndexMinPQ(int max){
		keys = (K[]) new Comparable[max];
		pq = new int[max];
		map = new int[max];
	}
	
	/**
	 * 插入索引是i的key
	 * @param i
	 * @param key
	 */
	public void insert(int i, K key){
		if (n >= keys.length-1) {
			resize(2 * keys.length);
			
		}
		n++;
		map[i] = n;
		pq[n] = i;
		keys[n] = key;
		swim(n);
	}
	
	/**
	 * 最后一个元素上浮
	 */
	private void swim(int index){
		int pos = index;
		while(pos>1 && less(pos, pos/2)){
			swap(pos, pos/2);
			pos = pos/2;
		}
	}
	
	public int delMin(){
		int index = pq[1];
		swap(1, n--);
		sink(1);
		return index;
	}
	
	public K min(){
		return keys[1];
	}
	
	private void sink(int index){
		int pos = index;
		while(2 * pos <= n){
			int temp = 2 * pos;
			if (2 * pos < n && less(2*pos+1, 2*pos)) {
				temp = 2 * pos + 1;
			}
			if (less(temp, pos)) {
				swap(temp, pos);
			}
			pos = temp;
		}
	}
	
	private void swap(int i, int j){
		K temp = keys[i];
		keys[i] = keys[j];
		keys[j] = temp;
		int t = pq[i];
		pq[i]= pq[j];
		pq[j] = t;
		map[pq[i]]=i;
		map[pq[j]]=j;
	}
	private boolean less(int i, int j){
		int res = keys[i].compareTo(keys[j]);
		return res < 0;
	}
	
	public void change(int index, K key){
		keys[map[index]] = key;
		sink(map[index]);
		swim(map[index]);
	}
	
	public boolean contains(int index){
		return keys[map[index]] != null;
	}
	
	private void resize(int cap){
		K[] tKeys = (K[])new Comparable[cap];
		int[] tpq = new int[cap];
		int[] tmap = new int[cap];
		for(int i = 0; i<=n; i++){
			tKeys[i] = keys[i];
			tpq[i] = pq[i];
			tmap[i] = map[i];
		}
		keys = tKeys;
		pq = tpq;
		map = tmap;
	}
	public boolean isEmpty(){
		return n == 0;
	}
	
	public static void main(String[] args){
		IndexMinPQ<Double> minPQ = new IndexMinPQ<>(10);
		minPQ.insert(1, 8.7);
		minPQ.insert(2, 9.6);
		minPQ.insert(3, 2.5);
		minPQ.insert(4, 7.1);
		minPQ.insert(5, 1.4);
		minPQ.change(2, 0.4);
		System.out.println("min:"+minPQ.min());
		System.out.println(minPQ.delMin());
		minPQ.change(4, 1.3);
		System.out.println(minPQ.delMin());
		System.out.println(minPQ.delMin());
	}
}
