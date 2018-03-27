package com.zxchaos;

/**
 * 查找加权有向图中的环
 * @author zhangxin
 *
 */
public class EdgeWeightedDigraphCycle {

	private boolean[] marked;
	private boolean[] onStack;
	private Stack<DirectedEdge> cycle;
	private DirectedEdge[] edgeTo;
	public EdgeWeightedDigraphCycle(EdgeWeightedDigraph negEwdg){
		marked = new boolean[negEwdg.getVs()];
		onStack = new boolean[negEwdg.getVs()];
		edgeTo = new DirectedEdge[negEwdg.getVs()];
		for(int v = 0; v < negEwdg.getVs(); v++){
			if (!marked[v]) {
				dfs(negEwdg, v);
			}
		}
	}
	private void dfs(EdgeWeightedDigraph negEwdg, int v){
		marked[v] = true;
		onStack[v] = true;
		for(DirectedEdge de : negEwdg.adj(v)){
			if (cycle != null) {
				return;
			}
			int w = de.to();
			if (!marked[w]) {
				edgeTo[w] = de;
				dfs(negEwdg, w);
			} else if (onStack[w]) {
				cycle = new Stack<>();
				int from = de.from();
				while(from != w){
					cycle.push(de);
					de =  edgeTo[from];
					from = de.from();
				}
				cycle.push(de);
			}
		}
		onStack[v] = false;
	}
	
	public Iterable<DirectedEdge> cycle(){
		return cycle;
	}
}
