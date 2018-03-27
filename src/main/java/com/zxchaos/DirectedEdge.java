package com.zxchaos;

/**
 * 加权有向图的有向边数据类型
 * 
 * @author zhangxin
 *
 */
public class DirectedEdge implements Comparable<DirectedEdge> {
	private int v;
	private int w;
	private double weight;
	
	public DirectedEdge(int v, int w, double weight){
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	public int compareTo(DirectedEdge de) {
		double res = this.weight - de.weight;
		return res > 0 ? 1 : res < 0 ? -1 : 0;
	}
	
	public int from(){
		return v;
	}
	public int to(){
		return w;
	}
	
	public double weight(){
		return this.weight;
	}
	
	public String toString(){
		return v+"->"+w+"("+weight+")";
	}
}
