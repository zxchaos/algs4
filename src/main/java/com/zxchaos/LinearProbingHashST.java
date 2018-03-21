package com.zxchaos;

/**
 * 基于线性探测的hash符号表
 * @author zhangxin
 *
 */
public class LinearProbingHashST<K, V> {

	/**
	 * 符号表数组大小
	 */
	private int m;
	/**
	 * 符号表元素个数
	 */
	private int n;
	
	private K[] keys;
	private V[] values;
	
	public LinearProbingHashST(int m){
		keys = (K[])new Object[m];
		values = (V[])new Object[m];
		this.m = m;
	}
	
	public void put(K key, V value){
		if (n > m/2) {
			resize(m * 2);
		}
		
		int pos = (key.hashCode() & 0x7fffffff) % m;
		while(keys[pos] != null){
			if (keys[pos].equals(key)) {
				values[pos] = value;
				return;
			}
			pos = (pos + 1)%m;
		}
		keys[pos] = key;
		values[pos] = value;
		n++;
	}
	
	public void delete(K key){
		if (!contains(key)) {
			return;
		}
		int pos = (key.hashCode() & 0x7fffffff ) % m;
		while(!keys[pos].equals(key)){
			pos = (pos + 1) % m;
		}
		keys[pos] = null;
		values[pos] = null;
		pos = (pos+1)%m;//下一组键簇起始位置
		
		//重新放置下一列键簇
		while(keys[pos] != null){
			K keyRedo = keys[pos];
			V valueRedo = values[pos];
			keys[pos] = null;
			values[pos] = null;
			put(keyRedo, valueRedo);
			pos = (pos + 1)%m;
		}
		n--;
		if (n > 0 && n < m/8) {
			resize(m/2);
		}
	}
	
	public boolean contains(K key){
		return get(key) == null? false:true;
	}
	
	public V get(K key){
		int pos = (key.hashCode() & 0x7fffffff) % m;
		while(keys[pos] != null){
			if (keys[pos].equals(key)) {
				return values[pos];
			}
			pos = (pos + 1) % m;
		}
		return null;
	}
	
	private void resize(int cap){
		LinearProbingHashST<K, V> temp = new LinearProbingHashST<>(cap);
		for(int i = 0; i < m; i++){
			if (keys[i]  != null) {
				temp.put(keys[i], values[i]);
			}
		}
		this.keys = temp.keys;
		this.values = temp.values;
		this.m = cap; 
	}
	
}
