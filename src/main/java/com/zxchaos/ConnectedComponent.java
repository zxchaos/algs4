package com.zxchaos;

/**
 * 无向图中的连通分量
 * @author zhangxin
 *
 */
public class ConnectedComponent {
	/**
	 * 图中连通分量数量
	 */
	private int count;
	/**
	 * 各顶点对应的连通分量编号
	 */
	private int ids[];
	
	private boolean marked[];
	public ConnectedComponent(Graph g){
		ids = new int[g.getVs()];
		marked = new boolean[g.getVs()];
		
		for(int i = 0; i< g.getVs(); i++){
			if (!marked[i]) {
				count++;
				dfs(g, i);
			}
		}
	}
	
	private void dfs(Graph g, int v){
		ids[v] = count;
		marked[v] = true;
		for(int w : g.adj(v)){
			if (!marked[w]) {
				dfs(g, w);
			}
		}
	}
	
	public boolean connected(int v, int w){
		return ids[v] == ids[w];
	}
	
	public static void main(String[] args){
		Graph g = new Graph(13);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(0, 5);
		g.addEdge(0, 6);
		g.addEdge(3, 4);
		g.addEdge(3, 5);
		g.addEdge(4, 6);
		g.addEdge(4, 5);
		g.addEdge(7, 8);
		g.addEdge(9, 10);
		g.addEdge(9, 11);
		g.addEdge(9, 12);
		g.addEdge(11, 12);
		ConnectedComponent cc = new ConnectedComponent(g);
		System.out.println("1,7 "+cc.connected(1, 7));
		System.out.println("1,3 "+cc.connected(1, 3));
		System.out.println("10,12 "+cc.connected(10, 12));
		System.out.println("4,8 "+cc.connected(4, 8));
	}
	
}
