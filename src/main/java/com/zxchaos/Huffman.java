package com.zxchaos;

import java.io.File;

/**
 * huffman压缩和解压, 仅实现核心算法, 读取写入文件部分未实现
 * @author zhangxin
 *
 */
public abstract class Huffman {

	private static final int R = 256;
	public static class Node implements Comparable<Node> {
		int freq;
		int v;
		Node left;
		Node right;
		public Node(Node left, Node right, int freq){
			this.left = left;
			this.right = right;
			this.freq = freq;
		}
		
		public Node(int freq, int v){
			this.freq = freq;
			this.v = v;
		}
		public boolean isLeaf(){
			return left==null && right == null;
		}
		public int compareTo(Node o){
			if (this.freq > o.freq) {
				return 1;
			} else if (this.freq < o.freq) {
				return -1;
			} else {
				return 0;
			}
		}
	}
	
	public void compress(){
		//统计频率
		int[] freq = new int[R];
		int length = 0;
		byte[] bytes = new byte[2048];
		while((length=readFile(bytes))!=-1){
			for(int i = 0; i < bytes.length; i++){
				freq[0x00ff&bytes[i]]++;
			}
		}
		
		//构建huffman 树
		MinPQ<Node> minPQ = new MinPQ<>(10);
		for(int i = 0; i< R; i++){
			minPQ.insert(new Node(freq[i], i));
		}
		while(minPQ.size() > 1){
			Node right = minPQ.delMin();
			Node left = minPQ.delMin();
			Node p = new Node(left, right, left.freq+right.freq);
			minPQ.insert(p);
		}
		Node root = minPQ.delMin();
		writeTree(root);
		String[] st = new String[R];
		buildCode(root, st, "");
		while((length = readFile(bytes))!=-1){
			for(int i = 0; i< length; i++){
				write2File(st[0x00ff&bytes[i]]);
			}
		}
		
	}
	
	/**
	 * 字节对应编码表
	 * @param node
	 * @param st
	 * @param s
	 */
	public void buildCode(Node node, String[] st, String s){
		if (node.isLeaf()) {
			st[node.v] = s;
			return;
		}
		buildCode(node.left,st, s+"0");
		buildCode(node.right,st, s+"1");
	}
	
	
	/**
	 * huffman树写入文件
	 * @param root
	 */
	public abstract void writeTree(Node root);
	
	public abstract void write2File(String s);
	
	/**
	 * 将文件数据读取到bytes中
	 * @param bytes
	 * @return 读取的字节长度
	 */
	public abstract int readFile(byte[] bytes);
}
