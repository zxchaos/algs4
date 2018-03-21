package com.zxchaos;

/**
 * 顺序查找符号表, 基于链表实现
 * @author zhangxin
 *
 */
public class SequentialSearchST<K, V> {
	private Node head;
	private class Node {
		K key;
		Object value;
		Node next;
		public Node(K key, V value){
			this.key = key;
			this.value = value;
		}
	}
	
	public void put(K key, V value){
		Node node = head;
		while(node != null){
			if (node.key.equals(key)) {
				node.value = value;
				return;
			} else {
				node = node.next;
			}
		}
		node = new Node(key,value);
		node.next = head;
		head = node;
	}
	
	public V get(K key){
		Node node = head;
		while(node != null){
			if (node.key.equals(key)) {
				return (V)node.value;
			}
		}
		return null;
	}
}
