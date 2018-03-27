package com.zxchaos;

/**
 * 加权有向图最短路径树, Dijkstra算法
 * @author zhangxin
 */
public class DijkstraSPT {
	
	private DirectedEdge[] edgeTo;
	private double[] distTo;
	
	private IndexMinPQ<Double> pq;
	
	private int s;
	
	public DijkstraSPT(EdgeWeightedDigraph ewdg, int s){
		edgeTo = new DirectedEdge[ewdg.getVs()];
		distTo = new double[ewdg.getVs()];
		this.s = s;
		for(int v = 0; v < ewdg.getVs(); v++){
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[s] = 0.0;
		pq = new IndexMinPQ<>(ewdg.getVs());
		pq.insert(s, 0.0);
		while(!pq.isEmpty()){
			relax(ewdg, pq.delMin());
		}
	}
	
	private void relax(EdgeWeightedDigraph ewdg, int v){
		for(DirectedEdge de : ewdg.adj(v)){
			int w = de.to();
			if (distTo[w] > distTo[v]+de.weight()) {
				distTo[w] = distTo[v]+de.weight();
				edgeTo[w] = de;
				if (pq.contains(w)) {
					pq.change(w, distTo[w]);
				} else {
					pq.insert(w, distTo[w]);
				}
			}
		}
	}
	
	public Iterable<DirectedEdge> sp(int v){
		Stack<DirectedEdge> stack = new Stack<>();
		int from = edgeTo[v].from();
		DirectedEdge de = edgeTo[v];
		while(de != null){
			stack.push(de);
			v = de.from();
			de = edgeTo[v];
		}
		return stack;
	}
	
	public static void main(String[] args){
		EdgeWeightedDigraph ewdg = new EdgeWeightedDigraph(8);
		ewdg.addEdge(new DirectedEdge(4, 5, 0.35));
		ewdg.addEdge(new DirectedEdge(5, 4, 0.35));
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
		DijkstraSPT spt = new DijkstraSPT(ewdg, 0);
		Iterable<DirectedEdge> sp = spt.sp(1);
		for(DirectedEdge de : sp){
			System.out.println(de);
		}
	}
}
