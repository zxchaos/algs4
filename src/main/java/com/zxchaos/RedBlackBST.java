package com.zxchaos;

import java.awt.RenderingHints.Key;

/**
 * 红黑二叉查找树
 * @author zhangxin
 */
public class RedBlackBST<K extends Comparable<K>, V> {
	private Node root;
	private class Node {
		K key;
		V value;
		Node left;
		Node right;
		boolean color;// true 表示 red, blase 表示black
		int n;// 以该节点为根的树中节点个数
		
		public Node(K key, V value, int n, boolean color){
			this.key = key;
			this.value = value;
			this.color = color;
			this.n = n;
		}
	}
	
	public void put(K key, V value){
		root = put(root, key, value);
		root.color = false;
	}
	
	private Node put(Node node, K key, V value){
		if (node == null) {
			return new Node(key, value, 1, true);
		}
		int res = node.key.compareTo(key);
		if(res > 0){
			node.left = put(node.left, key, value);
		} else if (res < 0) {
			node.right = put(node.right, key, value);
		} else {
			node.value = value;
		}
		node.n = size(node.left) + size(node.right) +1;
		if (!isRed(node.left)&&isRed(node.right)) {
			node = rotateLeft(node);
		}
		if (isRed(node.left) && isRed(node.left.left)) {
			node = rotateRight(node);
		}
		if (isRed(node.left) && isRed(node.right)) {
			flipColor(node);
		}
		return node;
	}
	
	/**
	 * 删除最小节点
	 * @param node
	 * @return
	 */
	private Node deleteMin(Node node){
		if (node == null) {
			return null;
		} 
		if (node.left == null) {
			return null;
		}
		if (!isRed(node.left) && !isRed(node.left.left)) {
			node = moveRedLeft(node);
		}
		node.left = deleteMin(node.left);
		return balance(node);
	}
	
	public void delete(K key){
		if (root == null) {
			return;
		}
		
		if (!isRed(root.left) && !isRed(root.right)) {
			root.color = true;
		}
		root = delete(root, key);
		if (root != null) {
			root.color = false;
		}
	}
	
	/**
	 * 删除key对应节点
	 * @param node
	 * @param key
	 * @return
	 */
	private Node delete(Node node, K key){
		if (node == null) {
			return null;
		}
		int res = node.key.compareTo(key);
		if (res > 0) {
			if (!isRed(node.left) && !isRed(node.left.left)) {
				node = moveRedLeft(node);
			}
			node.left = delete(node.left, key);
		} else {
			if (isRed(node.left)) {
				node = rotateRight(node);
			}
			if (node.key.compareTo(key) == 0 && node.right==null) {
				return null;
			}
			if (!isRed(node.right) && !isRed(node.right.left) ) {
				node = moveRedRight(node);
			}
			if (node.key.compareTo(key) == 0) {
				Node rightMinNode = min(node.right);
				node.key = rightMinNode.key;
				node.value = rightMinNode.value;
				node.right = deleteMin(node.right);
			} else {
				node.right = delete(node.right, key);
			}
		}
		return balance(node);
	}
	
	/**
	 * 
	 * @param node
	 * @return
	 */
	private Node moveRedLeft(Node node){
		// 调用该方法前提: node为红, node.left 黑, node.left.left黑
		flipColor(node);
		if (isRed(node.right.left)) {
			node.right = rotateRight(node.right);
			node = rotateLeft(node);
			flipColor(node);
		}
		return node;
	}
	
	private Node moveRedRight(Node node){
		// 前提: node 红, node.right 黑, node.right.left 黑
		flipColor(node);
		if (isRed(node.left.left)) {
			node = rotateRight(node);
			flipColor(node);
		}
		return node;
	}
	
	/**
	 * 平衡以node为根的子树
	 * @param node
	 * @return 平衡后的节点
	 */
	private Node balance(Node node){
		if (!isRed(node.left) && isRed(node.right)) {
			node = rotateLeft(node);
		}
		if (isRed(node.left) && isRed(node.left.left)) {
			node = rotateRight(node);
		}
		
		if (isRed(node.left) && isRed(node.right)) {
			flipColor(node);
		}
		node.n = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	/**
	 * 将根节点 左旋
	 * @param node
	 * @return 左旋后的树根节点
	 */
	private Node rotateLeft(Node node){
		Node rightNode = node.right;
		node.right = rightNode.left;
		rightNode.left = node;
		rightNode.color = node.color;
		node.color = true;
		rightNode.n = node.n;
		node.n = size(node.right) + size(node.left)+1;
		return rightNode;
	}
	
	private int size(Node node){
		if (node == null) {
			return 0;
		}
		return size(node.right)+size(node.left)+1;
	}
	
	/**
	 * 将根节点 右旋
	 * @param node
	 * @return 右旋后的树根节点
	 */
	private Node rotateRight(Node node){
		Node leftNode = node.left;
		node.left = leftNode.right;
		leftNode.right = node;
		leftNode.color = node.color;
		node.color = true;
		leftNode.n = node.n;
		node.n = size(node.left)+size(node.right)+1;
		return leftNode;
	}
	
	private boolean isRed(Node node){
		if (node == null) {
			return false;
		}
		return node.color;
	}
	private void flipColor(Node node){
		node.color = !node.color;
		node.left.color = !node.left.color;
		node.right.color = !node.right.color;
	}
	
	/**
	 * 
	 * @param node
	 * @return 以node为根的子树中的最小节点
	 */
	private Node min(Node node){
		if (node == null) {
			return null;
		}
		if (node.left == null) {
			return node;
		}
		return min(node.left);
	}
}
