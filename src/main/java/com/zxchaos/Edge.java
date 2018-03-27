package com.zxchaos;

/**
 * 加权无向图边的数据类型
 * 
 * @author zhangxin
 */
public class Edge implements Comparable<Edge> {
	private int v;
	private int w;
	private double weight;

	public Edge(int v, int w, double weight) {
		this.v = v;
		this.w = w;
		this.weight = weight;
	}

	/**
	 * @return 获取其中一个顶点
	 */
	public int either() {
		return v;
	}

	/**
	 * 
	 * @param v
	 * @return 根据一个顶点v, 获取另一个顶点
	 */
	public int other(int v) {
		if (this.v == v) {
			return this.w;
		} else if (this.w == v) {
			return this.v;
		}
		throw new RuntimeException("v 不在这条边");
	}
	
	 public double getWeight() {
		return weight;
	}

	@Override
	public int compareTo(Edge o) {
		double res = this.weight - o.weight;
		return res > 0 ? 1 : res < 0 ? -1 : 0;
	}
	
	public String toString(){
		return this.v+"-"+this.w+" "+this.weight;
	}

}
