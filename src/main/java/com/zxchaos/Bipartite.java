package com.zxchaos;

/**
 * 二分图的判断
 * @author zhangxin
 *
 */
public class Bipartite {
	private boolean color[];
	private boolean marked[];
	private boolean isBipartite;
	
	public Bipartite(Graph g){
		color = new boolean[g.getVs()];
		marked = new boolean[g.getVs()];
		isBipartite = true;
		for(int i = 0; i< g.getVs(); i++){
			if (!marked[i]) {
				dfs(g, i);
			}
		}
	}
	
	private void dfs(Graph g, int v){
		marked[v] = true;
		for(int w: g.adj(v)){
			if(!marked[w]){
				color[w] = !color[v];
				dfs(g, w);
			} else if (color[w] == color[v]) {
				isBipartite = false;
			}
			if (!isBipartite) {
				return;
			}
		}
	}
	
	public boolean isBipartite(){
		return isBipartite;
	}
	public static void main(String[] args){
		Graph g = new Graph(3);
		g.addEdge(0, 1);
		g.addEdge(1, 2);
		g.addEdge(0, 2);
		
		Bipartite bp = new Bipartite(g);
		System.out.println(bp.isBipartite);
	}
}
