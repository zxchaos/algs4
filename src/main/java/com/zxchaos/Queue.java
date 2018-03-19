package com.zxchaos;

import java.util.Iterator;

/**
 * FIFO 队列的链表实现
 * @author zhangxin
 *
 */
public class Queue<T> implements Iterable<T> {
	/**
	 * 队列头
	 */
	private Node head;
	/**
	 * 队列尾
	 */
	private Node tail;
	
	private int n;
	
	private class Node {
		Object item;
		Node next;
	}
	/**
	 * 入队
	 * @param item
	 */
	public void enqueue(T item){
		Node newTail = new Node();
		newTail.item = item;
		n++;
		if (head == null) {
			tail = newTail;
			head = tail;
			return;
		}
		tail.next = newTail;
		tail = tail.next;
		
	}
	
	/**
	 * 队头元素出队
	 * @return 队头元素
	 */
	public T dequeue(){
		if (head == null) {
			tail = null;
			return null;
		}
		n--;
		Node oldHead = head;
		head = head.next;
		return (T)oldHead.item;
	}
	
	//--------------- 次要接口-----
	public int size(){
		return n;
	}
	public boolean isEmpty(){
		return head == null;
	}
	
	public Iterator<T> iterator(){
		return new QueueIterator(head);
	}
	
	private class QueueIterator implements Iterator<T>{
		Node current;
		public QueueIterator(Node head){
			this.current = head;
		}
		public boolean hasNext() {
			return current != null;
		}

		public T next() {
			Node res = current;
			current = current.next;
			return (T)res.item;
		}
		
	}
	//-----------------
	
	public static void main(String[] args) {
		Queue<String> queue = new Queue<String>();
		queue.enqueue("nnn");
		queue.enqueue("ttt");
		queue.enqueue("bbb");
		queue.enqueue("aaa");
		
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println(queue.dequeue());
		System.out.println("=====");
		queue.enqueue("hhh");
		queue.enqueue("ggg");
		queue.enqueue("444");
		queue.enqueue("333");
		for(String s : queue){
			System.out.println(s);
		}
	}
}
