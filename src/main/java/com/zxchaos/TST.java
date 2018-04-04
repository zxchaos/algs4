package com.zxchaos;

/**
 * 三向单词查找树
 * @author zhangxin
 *
 */
public class TST<V> {

	private Node root;
	private class Node {
		Object v;
		char c;
		Node left;
		Node mid;
		Node right;
	}
	
	public V get(String key){
		Node node = get(key, root, 0);
		if (node == null) {
			return null;
		}
		return (V)node.v;
	}
	
	private Node get(String key, Node node, int d){
		if (node == null) {
			return null;
		}
		if (d == key.length()-1) {
			if (node.v != null) {
				return node;
			}
			return null;
		}
		char c = key.charAt(d);
		if (c > node.c) {
			return get(key, node.right, d);
		} else if (c < node.c) {
			return get(key, node.left, d);
		} else {
			return get(key, node.mid, d+1);
		}
	}
	
	public void put(String key, V v){
		root = put(key, v, root, 0);
	}
	
	private Node put(String key, V v, Node node, int d){
		if (node == null) {
			node = new Node();
			node.c = key.charAt(d);
		}
		char c = key.charAt(d);
		if (d == key.length() - 1) {
			node.v = v;
			return node;
		}
		
		if (c > node.c) {
			node.right = put(key, v, node.right, d);
		} else if (c < node.c) {
			node.left = put(key, v, node.left, d);
		} else {
			node.mid = put(key, v, node.mid, d+1);
		}
		return node;
	}
	
	public String longestPrefixKey(String s){
		int length = search(s, root, 0,0);
		return s.substring(0,length);
	}
	
	private int search(String s, Node node, int d, int length){
		if (node == null) {
			return length;
		}
		
		char c = s.charAt(d);
		if (c > node.c) {
			return search(s, node.right, d, length);
		} else if (c < node.c) {
			return search(s, node.left, d, length);
		} else {
			if (node.v != null) {
				length = d+1;
			}
			if (d == s.length()-1) {
				return length;
			} else {
				return search(s, node.mid, d+1, length);
			}
		}
	}
	
	
	
	public static void main(String[] args){
		TST<Integer> tst = new TST<>();
		tst.put("she", 98);
		tst.put("sells", 45);
		tst.put("the", 23);
		tst.put("shells", 67);
		tst.put("on", 46);
		tst.put("the", 18);
		tst.put("shore", 91);
		
		System.out.println(tst.get("the"));
		System.out.println(tst.get("shells"));
		System.out.println(tst.get("on"));
		System.out.println(tst.get("sea"));
		
		System.out.println("======");
		System.out.println(tst.longestPrefixKey("shell"));
		System.out.println(tst.longestPrefixKey("one"));
		System.out.println(tst.longestPrefixKey("shore"));
		System.out.println(tst.longestPrefixKey("shelnidd"));
		
	}
}
