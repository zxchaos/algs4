package com.zxchaos;

/**
 * 有向环检测
 * @author zhangxin
 */
public class DirectedCycle {
	
	private boolean[] marked;
	private Stack<Integer> cycle;
	private int[] edgeTo;
	private boolean[] onStack;
	public DirectedCycle(Digraph dg){
		marked = new boolean[dg.getVs()];
		edgeTo = new int[dg.getVs()];
		onStack = new boolean[dg.getVs()];
		for(int v = 0; v < dg.getVs(); v++){
			if (!marked[v]) {
				dfs(dg, v);
			}
		}
	}
	private void dfs(Digraph dg, int v){
		marked[v] = true;
		onStack[v] = true;
		for(int w : dg.adj(v)){
			if (cycle != null) {
				return;
			}
			if (!marked[w]) {
				edgeTo[w] = v;
				dfs(dg, w);
			} else if(onStack[w]) {
				cycle = new Stack<>();
				for(int x = v; x != w && x != -1; x = edgeTo[x]){
					System.out.println("x="+x);
					cycle.push(x);
				}
				cycle.push(w);
				cycle.push(v);
			}
			
		}
		onStack[v] = false;
	}
	
	public boolean isDAG(){
		return cycle == null;
	}
	
	public Iterable<Integer> cyclePath(){
		return cycle;
	}
	
	public static void main(String[] args){
		Digraph dg = new Digraph(13);
		dg.addEdge(0, 1);
		dg.addEdge(2, 0);
		dg.addEdge(0, 5);
		dg.addEdge(3, 2);
		dg.addEdge(2, 3);
		dg.addEdge(5, 4);
		dg.addEdge(4, 3);
		dg.addEdge(4, 2);
		dg.addEdge(3, 5);
		dg.addEdge(6, 0);
		dg.addEdge(6, 4);
		dg.addEdge(6, 9);
		dg.addEdge(7, 6);
		dg.addEdge(7, 8);
		dg.addEdge(8, 7);
		dg.addEdge(8, 9);
		dg.addEdge(9, 10);
		dg.addEdge(9, 11);
		dg.addEdge(11, 12);
		dg.addEdge(12, 9);
		dg.addEdge(10, 12);
		DirectedCycle dc = new DirectedCycle(dg);
		System.out.println(dc.isDAG());
		for(Integer v : dc.cyclePath()){
			System.out.println(v);
		}
	}
}
