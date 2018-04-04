package com.zxchaos;

/**
 * R向单词查找树
 * @author zhangxin
 */
public class TrieST<V> {

	Node root;
	private static final int R = 256;
	private static class Node{
		Object v;
		Node[] next;
		public Node(){
			next = new Node[R];
		}
	}
	
	public void put(String key, V v){
		root = put(key, v, root, 0);
	}
	
	private Node put(String key, V v, Node node, int d){
		if (node == null) {
			node = new Node();
		}
		if (d == key.length()) {
			node.v = v;
			return node;
		}
		char c = key.charAt(d);
		node.next[c] = put(key, v, node.next[c], d+1);
		return node;
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
		if (d == key.length()) {
			return node;
		}
		char c = key.charAt(d);
		return get(key, node.next[c], d+1);
	}
	
	public static void main(String[] args){
		TrieST<Integer> trie = new TrieST<>();
		trie.put("she", 45);
		trie.put("sells", 21);
		trie.put("the", 61);
		trie.put("seashells", 67);
		trie.put("on", 90);
//		trie.put("the", 67);
		trie.put("shore", 10);
		
		System.out.println(trie.get("on"));
		System.out.println(trie.get("one"));
		System.out.println(trie.get("shell"));
		System.out.println(trie.get("sea"));
		System.out.println(trie.get("the"));
	}
	
}
