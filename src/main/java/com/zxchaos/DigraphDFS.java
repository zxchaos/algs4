package com.zxchaos;

import java.util.Iterator;

/**
 * 有向图可达性
 * @author zhangxin
 *
 */
public class DigraphDFS {
	private boolean marked[];
	/**
	 * 逆后序
	 */
	private Stack<Integer> reversePostOrder;
	
	public DigraphDFS(Digraph dg, int s){
		marked = new boolean[dg.getVs()];
		dfs(dg, s);
	}
	
	public DigraphDFS(Digraph dg, Iterable<Integer> sources){
		marked = new boolean[dg.getVs()];
		for(Integer v : sources){
			if (!marked[v]) {
				dfs(dg, v);
			}
		}
	}
	
	public DigraphDFS(Digraph dg){
		marked = new boolean[dg.getVs()];
		reversePostOrder = new Stack<>();
		for(int v = 0; v < dg.getVs(); v++){
			if (!marked[v]) {
				dfs(dg, v);
			}
		}
	}
	
	private void dfs(Digraph dg, int v){
		marked[v] = true;
		for(int w : dg.adj(v)){
			if (!marked[w]) {
				dfs(dg, w);
			}
		}
		reversePostOrder.push(v);
	}
	
	/**
	 * 
	 * @return 获取有向图逆后序
	 */
	public Stack<Integer> getReversePostOrder(){
		return reversePostOrder;
	}
	
	public boolean marked(int v){
		return marked[v];
	}
}
