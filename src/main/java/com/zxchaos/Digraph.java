package com.zxchaos;

/**
 * 有向图
 * @author zhangxin
 */
public class Digraph {

	private int vs;
	private int es;
	private Bag<Integer>[] adj;
	public Digraph(int vs){
		this.vs = vs;
		adj = new Bag[vs];
		for(int i = 0; i<vs; i++){
			adj[i] = new Bag<>();
		}
	}
	public int getVs(){
		return this.vs; 
	}
	/**
	 * 添加一条由v指向w的边
	 * @param v
	 * @param w
	 */
	public void addEdge(int v, int w){
		adj[v].add(w);
		es++;
	}
	
	/**
	 * 有向图中由v指出的边集合
	 * @param v
	 * @return
	 */
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	public Digraph reverse(){
		Digraph r = new Digraph(vs);
		for(int v = 0; v < vs; v++){
			for(int w : adj(v)){
				r.addEdge(w, v);
			}
		}
		return r;
	}
}
