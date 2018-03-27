package com.zxchaos;

/**
 * 加权无向图, 最小生成树MST Prim算法的 eager版
 */
public class Prim {

	private double[] distTo;// 顶点与树的距离
	private boolean[] marked;
	private Edge[] edgeTo;// 到达顶点mst的边
	private IndexMinPQ<Double> pq;

	public Prim(EdgeWeightedGraph ewg) {
		distTo = new double[ewg.getVs()];
		for(int v = 0; v < distTo.length; v++){
			distTo[v] = Double.POSITIVE_INFINITY;
		}
		distTo[0] = 0.0;
		marked = new boolean[ewg.getVs()];
		pq = new IndexMinPQ<>(ewg.getVs());
		edgeTo = new Edge[ewg.getVs()];
		distTo[0] = 0.0;
		pq.insert(0, 0.0);
		while(!pq.isEmpty()){
			int v = pq.delMin();
			marked[v] = true;
			for(Edge e : ewg.adj(v)){
				int w = e.other(v);
				if (marked[w]) {
					continue;
				}
				if (e.getWeight() < distTo[w]) {
					distTo[w] = e.getWeight();
					edgeTo[w] = e;
					if (pq.contains(w)) {
						pq.change(w, e.getWeight());
					} else {
						pq.insert(w, e.getWeight());
					}
				}
			}
		}
	}

	public Iterable<Edge> mst(){
		Queue<Edge> q = new Queue<>();
		for(int v = 0; v < edgeTo.length; v++){
			if (edgeTo[v]!=null) {
				System.out.println(edgeTo[v]);
				q.enqueue(edgeTo[v]);
			}
		}
		return q;
	}
	
	public static void main(String[] args){
		EdgeWeightedGraph ewg = new EdgeWeightedGraph(8);
		ewg.addEdge(new Edge(4, 5, 0.35));
		ewg.addEdge(new Edge(4, 7, 0.37));
		ewg.addEdge(new Edge(5, 7, 0.28));
		ewg.addEdge(new Edge(0, 7, 0.16));
		ewg.addEdge(new Edge(1, 5, 0.32));
		ewg.addEdge(new Edge(0, 4, 0.38));
		ewg.addEdge(new Edge(2, 3, 0.17));
		ewg.addEdge(new Edge(1, 7, 0.19));
		ewg.addEdge(new Edge(0, 2, 0.26));
		ewg.addEdge(new Edge(1, 2, 0.36));
		ewg.addEdge(new Edge(1, 3, 0.29));
		ewg.addEdge(new Edge(2, 7, 0.34));
		ewg.addEdge(new Edge(6, 2, 0.40));
		ewg.addEdge(new Edge(3, 6, 0.52));
		ewg.addEdge(new Edge(6, 0, 0.58));
		ewg.addEdge(new Edge(6, 4, 0.93));
		Prim p = new Prim(ewg);
		Iterable<Edge> q = p.mst();
		System.out.println("========");
		for(Edge e : q){
			System.out.println(e);
		}
	}
}
