package com.zxchaos;

/**
 * 加权 有向无环图(DAG) 的最短路径树, 算法
 * @author zhangxin
 *
 */
public class AcyclicSPT {
	private DirectedEdge[] edgeTo;
	private double[] distTo;
	private int s;
	
	private boolean[] marked;
	
	public AcyclicSPT(EdgeWeightedDigraph ewdg, int s){
		//略过是否有环检查
		marked = new boolean[ewdg.getVs()];
		edgeTo = new DirectedEdge[ewdg.getVs()];
		distTo = new double[ewdg.getVs()];
		this.s = s;
		for(int v = 0; v < ewdg.getVs(); v++){
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		Iterable<Integer> topoOrder = getTopologicalOrder(ewdg);
		for(int v : topoOrder){
			relax(ewdg, v);
		}
	}
	
	private void relax(EdgeWeightedDigraph ewdg, int v){
		for(DirectedEdge de : ewdg.adj(v)){
			int to = de.to();
			if (distTo[to] > distTo[v] + de.weight()) {
				distTo[to] = distTo[v] + de.weight();
				edgeTo[to] = de;
			}
		}
	}
	
	/**
	 * 
	 * @param ewdg
	 * @return 获取加权有向图无环图的拓扑排序
	 */
	private Iterable<Integer> getTopologicalOrder(EdgeWeightedDigraph ewdg){
		Stack<Integer> reversePostOrder = new Stack<>();
		dfs(ewdg, s, reversePostOrder);
		return reversePostOrder;
	}
	
	private void dfs(EdgeWeightedDigraph ewdg, int v, Stack<Integer> stack){
		marked[v] = true;
		for(DirectedEdge de : ewdg.adj(v)){
			int to = de.to();
			if (!marked[to]) {
				marked[to] = true;
				dfs(ewdg, to, stack);
			}
		}
		stack.push(v);
	}
	
	public Iterable<DirectedEdge> sp(int v){
		Stack<DirectedEdge> stack = new Stack<>();
		DirectedEdge de = edgeTo[v];
		while(de != null){
			stack.push(de);
			de = edgeTo[de.from()];
		}
		return stack;
	}
	
	
	public static void main(String[] args){

		EdgeWeightedDigraph ewdg = new EdgeWeightedDigraph(8);
		ewdg.addEdge(new DirectedEdge(5, 4, 0.35));
		ewdg.addEdge(new DirectedEdge(4, 7, 0.37));
		ewdg.addEdge(new DirectedEdge(5, 7, 0.28));
		ewdg.addEdge(new DirectedEdge(5, 1, 0.32));
		ewdg.addEdge(new DirectedEdge(4, 0, 0.38));
		ewdg.addEdge(new DirectedEdge(0, 2, 0.26));
		ewdg.addEdge(new DirectedEdge(3, 7, 0.39));
		ewdg.addEdge(new DirectedEdge(1, 3, 0.29));
		ewdg.addEdge(new DirectedEdge(7, 2, 0.34));
		ewdg.addEdge(new DirectedEdge(6, 2, 0.40));
		ewdg.addEdge(new DirectedEdge(3, 6, 0.52));
		ewdg.addEdge(new DirectedEdge(6, 0, 0.58));
		ewdg.addEdge(new DirectedEdge(6, 4, 0.93));
		AcyclicSPT spt = new AcyclicSPT(ewdg, 5);
		Iterable<DirectedEdge> sp = spt.sp(0);
		for(DirectedEdge de : sp){
			System.out.println(de);
		}
	
	}
	
}
