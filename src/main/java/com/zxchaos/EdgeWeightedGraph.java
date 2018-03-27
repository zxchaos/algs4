package com.zxchaos;

/**
 * 加权无向图
 * @author zhangxin
 */
public class EdgeWeightedGraph {
	
	private int vs;
	private int es;
	private Bag<Edge>[] adj;
	private Queue<Edge> edges;
	
	public EdgeWeightedGraph(int vs){
		this.vs = vs;
		adj = new Bag[vs];
		for(int v = 0; v < vs; v++){
			adj[v] = new Bag<>();
		}
		edges = new Queue<>();
	}
	
	public void addEdge(Edge edge){
		int v = edge.either();
		int w = edge.other(v);
		adj[v].add(edge);
		adj[w].add(edge);
		edges.enqueue(edge);
		es++;
	}
	
	public int getVs(){
		return vs;
	}
	public int getEs(){
		return es;
	}
	public Iterable<Edge> edges(){
		return this.edges;
	}
	/**
	 * 
	 * @param v
	 * @return 返回v连接的边
	 */
	public Iterable<Edge> adj(int v){
		return adj[v];
	}
}
