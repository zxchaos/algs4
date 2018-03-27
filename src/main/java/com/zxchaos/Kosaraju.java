package com.zxchaos;

/**
 * 有向图中强连通分量, 算法
 * @author zhangxin
 *
 */
public class Kosaraju {
	private int[] ids;
	/**
	 * 强连通分量, 数量
	 */
	private int count;
	private boolean[] marked;
	public Kosaraju(Digraph dg) {
		marked = new boolean[dg.getVs()];
		ids = new int[dg.getVs()];
		Digraph r = dg.reverse();
		Stack<Integer> reverseOrder = getReversePostOrder(r);
		for(Integer v : reverseOrder){
			if (!marked[v]) {
				count++;
				dfs(dg, v);
			}
		}
	}
	private void dfs(Digraph dg, int v){
		marked[v] = true;
		ids[v] = count;
		for(int w : dg.adj(v)){
			if (!marked[w]) {
				dfs(dg, w);
			}
		}
	}
	
	/**
	 * 
	 * @param dg
	 * @return 获取有向图的逆后序
	 */
	private Stack<Integer> getReversePostOrder(Digraph dg){
		DigraphDFS digraphDFS = new DigraphDFS(dg);
		return digraphDFS.getReversePostOrder();
	}
	
	/**
	 * 
	 * @param v
	 * @param w
	 * @return 顶点v和w是否为强连通
	 */
	public boolean isSc(int v, int w){
		return ids[v] == ids[w];
	}
	
	/**
	 * 
	 * @param v
	 * @return 获取v对应的强连通分量的编号
	 */
	public int getScc(int v){
		return ids[v];
	}
	
	public static void main(String[] args){
		Digraph dg = new Digraph(13);
		dg.addEdge(0, 1);
		dg.addEdge(0, 5);
		dg.addEdge(6, 0);
		dg.addEdge(2, 0);
		dg.addEdge(2, 3);
		dg.addEdge(3, 2);
		dg.addEdge(3, 5);
		dg.addEdge(4, 3);
		dg.addEdge(4, 2);
		dg.addEdge(5, 4);
		dg.addEdge(6, 4);
		dg.addEdge(6, 9);
		dg.addEdge(7, 6);
		dg.addEdge(7, 8);
		dg.addEdge(8, 7);
		dg.addEdge(8, 9);
		dg.addEdge(9, 10);
		dg.addEdge(9, 11);
		dg.addEdge(12, 9);
		dg.addEdge(10, 12);
		dg.addEdge(11, 4);
		dg.addEdge(11, 12);
		Kosaraju k = new Kosaraju(dg);
		System.out.println("0,2 strongly cc? "+ k.isSc(0, 2));
		System.out.println("0 scc is "+ k.getScc(0)+", 2 scc is "+ k.getScc(2)+", 5 scc is "+k.getScc(5)+", 1 scc is "+k.getScc(1));
		System.out.println("9,12 strongly cc? "+ k.isSc(9, 12));
		System.out.println("2,8 strongly cc? "+ k.isSc(2, 8));
		System.out.println("2 scc is "+ k.getScc(2)+", 8 scc is "+ k.getScc(8));
		
	}
}
