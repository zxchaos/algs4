package com.zxchaos;

import java.util.Iterator;

/**
 * 广度优先路径查找. 解决问题: 单点路径.
 * @author zhangxin
 *
 */
public class BreadthFirstPaths {
	private boolean[] marked;
	private int[] edgeTo;
	private int s;//起点
	
	public BreadthFirstPaths(Graph g, int s){
		marked = new boolean[g.getVs()];
		edgeTo = new int[g.getVs()];
		this.s = s;
		bfs(g, s);
	}
	private void bfs(Graph g, int s){
		Queue<Integer> q = new Queue<>();
		q.enqueue(s);
		marked[s] =true;
		while(!q.isEmpty()){
			int v = q.dequeue();
			for(int w : g.adj(v)){
				if (!marked[w]) {
					marked[w] =true;
					q.enqueue(w);
					edgeTo[w] = v;
				}
			}
		}
	}
	
	public boolean hasPathTo(int v){
		return marked[v];
	}
	
	public Iterable<Integer> paths(int v){
		Stack<Integer> stack = new Stack<>();
		int w = edgeTo[v];
		stack.push(v);
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
		BreadthFirstPaths bfp = new BreadthFirstPaths(g, 0);
		Iterable<Integer> it = bfp.paths(4);
		StringBuilder sb = new StringBuilder();
		for(Iterator<Integer> i = it.iterator(); i.hasNext();){
			sb.append(i.next()).append(" ");
		}
		System.out.println(sb);
	}
}
