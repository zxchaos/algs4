package com.zxchaos;

/**
 * 拓扑排序
 * @author zhangxin
 */
public class TopologicalOrder {
	
	private Stack<Integer> reverseOrder;
	private boolean[] marked;
	
	public TopologicalOrder(Digraph dg){
		DirectedCycle dc = new DirectedCycle(dg);
		
		if (!dc.isDAG()) {
			throw new RuntimeException("Not DAG");
		}
		reverseOrder = new Stack<>();
		marked = new boolean[dg.getVs()];
		for(int v = 0; v < dg.getVs(); v++){
			if (!marked[v]) {
				dfs(dg, v);
			}
		}
	}
	
	private void dfs(Digraph dg, int v){
		marked[v] = true;
		for(int w : dg.adj(v)){
			if (!marked[w]) {
				dfs(dg, w);
			}
		}
		reverseOrder.push(v);
	}
	
	public Iterable<Integer> order(){
		return reverseOrder;
	}
	
	public static void main(String[] args){
		Digraph dg = new Digraph(13);
		dg.addEdge(0, 1);
		dg.addEdge(0, 5);
		dg.addEdge(0, 6);
		dg.addEdge(2, 0);
		dg.addEdge(2, 3);
		dg.addEdge(3, 5);
		dg.addEdge(5, 4);
		dg.addEdge(6, 4);
		dg.addEdge(6, 9);
		dg.addEdge(7, 6);
		dg.addEdge(8, 7);
		dg.addEdge(9, 10);
		dg.addEdge(9, 11);
		dg.addEdge(9, 12);
		dg.addEdge(11, 12);
		TopologicalOrder to = new TopologicalOrder(dg);
		Iterable<Integer> order = to.order();
		for(Integer v : order){
			System.out.println(v);
		}
	}

}
