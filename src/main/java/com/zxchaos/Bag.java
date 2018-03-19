package com.zxchaos;

import java.util.Iterator;

/**
 * bag 背包, 只添加, 顺序无关
 * @author zhangxin
 *
 */
public class Bag<T> implements Iterable<T>{
	
	private Node root;
	
	private int n;
	
	private class Node{
		Object item;
		Node next;
	}
	
	/**
	 * 向bag添加元素
	 * @param item
	 */
	public void add(T item){
		Node node = new Node();
		node.item = item;
		node.next = root;
		root = node;
		n++;
	}
	
	public boolean isEmpty(){
		return root == null;
	}
	public int size(){
		return n;
	}
	
	public Iterator<T> iterator(){
		return new BagIterator(root);
	}
	
	private class BagIterator implements Iterator<T>{
		private Node current;
		public BagIterator(Node root){
			current = root;
		}
		
		public T next(){
			T res = (T)current.item;
			current = current.next;
			return res;
		}
		
		public boolean hasNext(){
			return current != null;
		}
		
	}
	
	
	public static void main(String[] args){
		Bag<String> bag = new Bag<>();
		bag.add("555");
		bag.add("bbb");
		bag.add("vvv");
		bag.add("6789");
		bag.add("erfvcd");
		
		for(String s : bag){
			System.out.println(s);
		}
		System.out.println(bag.size());
	}
	
	
}
