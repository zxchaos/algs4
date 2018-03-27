package com.zxchaos;

/**
 * Bellman-Ford 最短路径树算法
 * @author zhangxin
 */
public class BellmanFordSPT {
	private Queue<Integer> queue;
	private boolean[] onQ;
	private int count;
	private int s;
	private DirectedEdge[] edgeTo;
	private double[] distTo;
	
	
	private boolean hasNegativeCycle;//是否有负权重环
	
	private Iterable<DirectedEdge> cycle;
	
	public BellmanFordSPT(EdgeWeightedDigraph ewdg, int s){
		edgeTo = new DirectedEdge[ewdg.getVs()];
		distTo = new double[ewdg.getVs()];
		
		for(int v = 0; v < ewdg.getVs(); v++){
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		onQ = new boolean[ewdg.getVs()];
		queue = new Queue<>();
		queue.enqueue(s);
		while(!queue.isEmpty() && !hasNegativeCycle){
			int v = queue.dequeue();
			onQ[v] = false;
			relax(ewdg, v);
		}
	}
	
	private void relax(EdgeWeightedDigraph ewdg, int v){
		for(DirectedEdge de : ewdg.adj(v)){
			int w = de.to();
			if (distTo[w] > distTo[v] + de.weight()) {
				distTo[w] = distTo[v] + de.weight();
				edgeTo[w] = de;
				queue.enqueue(w);
				onQ[w]=true;
			}
			if (count++ % ewdg.getVs() == 0) {
				findNegativeCycle();
			}
		}
	}
	
	private void findNegativeCycle(){
		EdgeWeightedDigraph negEwdg = new EdgeWeightedDigraph(edgeTo.length);
		for(int v = 0; v < negEwdg.getVs(); v++){
			if (edgeTo[v] != null) {
				negEwdg.addEdge(edgeTo[v]);
			}
		}
		EdgeWeightedDigraphCycle finder = new EdgeWeightedDigraphCycle(negEwdg);
		cycle = finder.cycle();
		if (cycle != null) {
			hasNegativeCycle = true;
		}
	}
	
	
	
	public Iterable<DirectedEdge> sp(int v){
		Stack<DirectedEdge> stack = new Stack<>();
		while(edgeTo[v] != null){
			stack.push(edgeTo[v]);
			v = edgeTo[v].from();
		}
		return stack;
	}
	
	public static void main(String[] args){
		EdgeWeightedDigraph ewdg = new EdgeWeightedDigraph(8);
		ewdg.addEdge(new DirectedEdge(4, 5, 0.35));
		ewdg.addEdge(new DirectedEdge(5, 4, -0.66));
		ewdg.addEdge(new DirectedEdge(4, 7, 0.37));
		ewdg.addEdge(new DirectedEdge(5, 7, 0.28));
		ewdg.addEdge(new DirectedEdge(7, 5, 0.28));
		ewdg.addEdge(new DirectedEdge(5, 1, 0.32));
		ewdg.addEdge(new DirectedEdge(0, 4, 0.38));
		ewdg.addEdge(new DirectedEdge(0, 2, 0.26));
		ewdg.addEdge(new DirectedEdge(7, 3, 0.39));
		ewdg.addEdge(new DirectedEdge(1, 3, 0.29));
		ewdg.addEdge(new DirectedEdge(2, 7, 0.34));
		ewdg.addEdge(new DirectedEdge(6, 2, 0.40));
		ewdg.addEdge(new DirectedEdge(3, 6, 0.52));
		ewdg.addEdge(new DirectedEdge(6, 0, 0.58));
		ewdg.addEdge(new DirectedEdge(6, 4, 0.93));
		BellmanFordSPT spt = new BellmanFordSPT(ewdg, 0);
		if (spt.hasNegativeCycle) {
			Iterable<DirectedEdge> cycle = spt.cycle;
			System.out.println("has cycle");
			for(DirectedEdge v : cycle){
				System.out.println(v);
			}
		} else {
			Iterable<DirectedEdge> sp = spt.sp(6);
			for(DirectedEdge de : sp){
				System.out.println(de);
			}
		}
		
	}
}
