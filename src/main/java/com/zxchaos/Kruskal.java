package com.zxchaos;

/**
 * Kruskal 最小生成树算法
 * @author zhangxin
 *
 */
public class Kruskal {
	private MinPQ<Edge> pq;
	private Queue<Edge> mst;
	
	public Kruskal(EdgeWeightedGraph ewg){
		pq = new MinPQ<>(ewg.getVs());
		mst = new Queue<>();
		Iterable<Edge> edges = ewg.edges();
		for(Edge e : edges){
			pq.insert(e);
		}
		
//		Graph g = new Graph(ewg.getVs());
		UnionFind uf = new UnionFind(ewg.getVs());
		while(!pq.isEmpty() && mst.size() < ewg.getVs() - 1){
			Edge e = pq.delMin();
			int v = e.either();
			int w = e.other(v);
			if (uf.connected(v, w)) {
				System.out.println(v+": "+uf.find(v)+", "+w+": "+uf.find(w));
				continue;
			}
			uf.union(v, w);
//			ConnectedComponent cc = new ConnectedComponent(g);
//			if (cc.connected(v, w)) {
//				continue;
//			}
//			g.addEdge(v, w);
			mst.enqueue(e);
		}
	}
	
	public Iterable<Edge> mst(){
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
		Kruskal p = new Kruskal(ewg);
		Iterable<Edge> q = p.mst();
		for(Edge e : q){
			System.out.println(e);
		}
	
	}
}
