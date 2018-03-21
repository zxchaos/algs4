package com.zxchaos;

/**
 * 无向图
 * @author zhangxin
 *
 */
public class Graph {

	/**
	 * 顶点数
	 */
	private int vs;
	/**
	 * 边数
	 */
	private int es;
	/**
	 * 邻接数组
	 */
	private Bag<Integer> adjs[];
	public Graph(int vs){
		this.vs = vs;
		adjs = new Bag[vs];
		for(int i = 0; i< vs; i++){
			adjs[i] = new Bag<>();
		}
	}
	
	public int getEs(){
		return this.es;
	}
	public int getVs(){
		return this.vs;
	}
	
	/**
	 * 
	 * @param v
	 * @return 与顶点v相邻的顶点集合
	 */
	public Iterable<Integer> adj(int v){
		return adjs[v];
	}
	
	/**
	 * 在顶点v, w间添加一条边
	 * @param v
	 * @param w
	 */
	public void addEdge(int v, int w){
		adjs[v].add(w);
		adjs[w].add(v);
	}
}
