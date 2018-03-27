package com.zxchaos;

/**
 * 加权有向图, 数据类型
 * @author zhangxin
 *
 */
public class EdgeWeightedDigraph {
	private int vs;
	private int es;
	private Queue<DirectedEdge> edges;
	/**
	 * 邻接表数组
	 */
	private Bag<DirectedEdge>[] adjs;
	
	public EdgeWeightedDigraph(int vs){
		this.vs = vs;
		edges = new Queue<>();
		adjs = new Bag[vs];
		for(int v = 0; v < vs; v++){
			adjs[v] = new Bag<>();
		}
	}
	
	public Iterable<DirectedEdge> adj(int v){
		return adjs[v];
	}
	
	public void addEdge(DirectedEdge de){
		int v = de.from();
		adjs[v].add(de);
		es++;
		edges.enqueue(de);
	}
	
	/**
	 * 所有边
	 * @return
	 */
	public Iterable<DirectedEdge> edges(){
		return edges;
	}
	
	/**
	 * 
	 * @return 顶点数目
	 */
	public int getVs(){
		return vs;
	}
}
