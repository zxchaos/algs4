package com.zxchaos;

/**
 * 最小生成树, prim算法 懒汉版
 * @author zhangxin
 */
public class LazyPrim {

	private MinPQ<Edge> pq;
	private Queue<Edge> mst;
	private boolean[] marked;
	
	public LazyPrim(EdgeWeightedGraph ewg){
		pq = new MinPQ<>(ewg.getEs());
		mst = new Queue<>();
		marked = new boolean[ewg.getVs()];
			visit(ewg,0);
			while(!pq.isEmpty()){
				Edge edge = pq.delMin();
				int v = edge.either();
				int w = edge.other(v);
				if (marked[v] && marked[w]) {
					continue;
				}
				mst.enqueue(edge);
				if (!marked[v]) {
					visit(ewg,v);
				}
				if (!marked[w]) {
					visit(ewg,w);
				}
			}
	}

	private void visit(EdgeWeightedGraph ewg, int v) {
		marked[v] = true;
		Iterable<Edge> edges = ewg.adj(v);
		for(Edge ed : edges){
			pq.insert(ed);
		}
	}
	
	public Queue<Edge> getMst(){
		return mst;
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
		LazyPrim lp = new LazyPrim(ewg);
		Queue<Edge> q = lp.getMst();
		for(Edge e : q){
			System.out.println(e);
		}
	}
	
}
