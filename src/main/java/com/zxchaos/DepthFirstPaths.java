package com.zxchaos;

import java.util.Iterator;

/**
 * 深度优先路径. 解决问题: 单点路径问题.
 * @author zhangxin
 *
 */
public class DepthFirstPaths {

	private boolean[] marked;
	private int[] edgeTo;
	private int s;
	
	public DepthFirstPaths(Graph g, int s){
		marked = new boolean[g.getVs()];
		edgeTo = new int[g.getVs()];
		this.s = s;
		for(int i = 0; i < g.getVs(); i++){
			edgeTo[i] = -1;
		}
		dfs(g, s);
	}
	
	private void dfs(Graph g, int v){
		marked[v] = true;
		for(int w : g.adj(v)){
			if (!marked[w]) {
				edgeTo[w] = v;
				dfs(g, w);
			}
		}
	}
	
	public boolean marked(int v){
		return marked[v];
	}
	
	public Iterable<Integer> paths(int v){
		Stack<Integer> stack = new Stack<>();
		stack.push(v);
		int w = edgeTo[v];
		for(; w != s; w = edgeTo[w]){
			stack.push(w);
		}
		stack.push(s);
		return stack;
	}
	
	public static void main(String[] args){
		Graph g = new Graph(6);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(0, 5);
		g.addEdge(2, 1);
		g.addEdge(2, 3);
		g.addEdge(2, 4);
		g.addEdge(3, 5);
		g.addEdge(3, 4);
		
		DepthFirstPaths dfp = new DepthFirstPaths(g, 0);
		if (dfp.marked(4)) {
			Iterable<Integer> it = dfp.paths(4);
			StringBuilder sb = new StringBuilder();
			for(Iterator<Integer> i = it.iterator(); i.hasNext(); ){
				sb.append(i.next()).append(" ");
			}
			System.out.println(sb);
		}
		
	}
}
