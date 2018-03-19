package com.zxchaos;
/**
 * FILO 栈链表实现
 * @author zhangxin
 */

import java.util.Iterator;

public class Stack<T> implements Iterable<T>{

	/**
	 * 栈顶节点
	 */
	private Node top;
	/**
	 * FILO栈的大小
	 */
	private int n;
	
	private class Node{
		Object item;
		Node next;
	}
	
	/**
	 * 压栈
	 * @param item
	 */
	public void push(T item){
		Node newTop = new Node();
		newTop.item = item;
		newTop.next = top;
		top = newTop;
		n++;
	}
	
	/**
	 * 弹栈
	 * @return 栈顶元素
	 */
	public T pop(){
		if (top == null) {
			return null;
		}
		T res = (T)top.item;
		top = top.next;
		n--;
		return res;
	}
	
	public boolean isEmpty(){
		return top == null;
	}
	
	public int size(){
		return n;
	}
	
	public Iterator<T> iterator(){
		return new StackIterator(top);
	}
	
	private  class StackIterator implements Iterator<T>{
		Node current;
		
		public StackIterator(Node top){
			current = top;
		}

		public boolean hasNext() {
			return current != null;
		}

		public T next() {
			T res = (T)current.item;
			current = current.next;
			return res;
		}
		
	}
	
	
	public static void main(String[] args){
		Stack<String> stack = new Stack<>();
		stack.push("555");
		stack.push("aaa");
		stack.push("590");
		stack.push("nnn");
		
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		
		stack.push("999");
		stack.push("ggg");
		stack.push("eee");
		stack.push("234");
		stack.push("cdet");
		
		for(String s : stack){
			System.out.println("pop---"+s);
		}
		
		
	}
	
}
