package com.zxchaos;

/**
 * 无向图深度优先搜索, 给定图中顶点s, 判断图中某一顶点与其是否存在路径, 两点之间是否连通
 * @author zhangxin
 */
public class GraphDFS {
	
	private boolean[] marked;
	public GraphDFS(Graph g, int s){
		marked = new boolean[g.getVs()];
		dfs(g, s);
	}
	private void dfs(Graph g, int v){
		marked[v] = true;
		for(int w : g.adj(v)){
			if (!marked[w]) {
				dfs(g, w);
			}
		}
	}
	
	/**
	 * 
	 * @param v
	 * @return v是否从s可达, s到v是否可达
	 */
	public boolean marked(int v){
		return marked[v];
	}
}
