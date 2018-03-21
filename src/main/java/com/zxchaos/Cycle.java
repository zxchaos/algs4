package com.zxchaos;

/**
 * 判断连通图中是否有环
 * 
 * @author zhangxin
 */
public class Cycle {

	private boolean[] marked;
	private boolean hasCycle;
	private int edgeTo[];
	private Stack<Integer> stack;
	private int s;

	public Cycle(Graph g, int s) {
		marked = new boolean[g.getVs()];
		edgeTo = new int[g.getVs()];
		stack = new Stack<>();
		this.s = s;
		dfs(g, s, s);

	}

	private void dfs(Graph g, int v, int prev) {
		marked[v] = true;
		for (int w : g.adj(v)) {
			if (!marked[w]) {
				edgeTo[w] = v;
				dfs(g, w, v);
			} else if (w != prev) {
				for (int x = v; x != w; x = edgeTo[x]) {
					stack.push(x);
				}
				stack.push(w);
				stack.push(v);
				hasCycle = true;
			}
			if (hasCycle) {
				return;
			}
		}
	}

	public boolean hasCycle() {
		return hasCycle;
	}

	public Iterable<Integer> cyclePath() {
		return stack;
	}

	public static void main(String[] args) {
		Graph g = new Graph(6);
		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(0, 5);
		g.addEdge(2, 1);
		g.addEdge(2, 3);
		g.addEdge(2, 4);
		g.addEdge(3, 5);
		g.addEdge(3, 4);
		Cycle c = new Cycle(g, 0);
		System.out.println(c.hasCycle);
		StringBuilder sb = new StringBuilder();
		for (int v : c.cyclePath()) {
			sb.append(v).append(" ");
		}
		System.out.println(sb);
	}
}
