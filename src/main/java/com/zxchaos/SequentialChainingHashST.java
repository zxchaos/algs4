package com.zxchaos;

/**
 * 基于链表的顺序hash符号表
 * @author zhangxin
 *
 */
public class SequentialChainingHashST<K, V> {
	private SequentialSearchST<K, V>[] st;
	private int m;
	public SequentialChainingHashST(int m){
		st = new SequentialSearchST[m];
		this.m = m;
		for(int i = 0; i < m; i++){
			st[i] = new SequentialSearchST<>();
		}
	}
	
	public void put(K key, V value){
		int pos = (key.hashCode()&0x7fffffff) % m;
		st[pos].put(key, value);
	}
	
	public V get(K key){
		int pos = (key.hashCode() & 0x7fffffff) % m;
		return st[pos].get(key);
	}
	
	
}
