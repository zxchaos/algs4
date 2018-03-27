package com.zxchaos;

/**
 * 加权路径 union find
 * 
 * @author zhangxin
 *
 */
public class UnionFind {
	private int id[];
	private int sz[];
	
	public UnionFind(int vs){
		id=new int[vs];
		sz = new int[vs];
		for(int v = 0; v < vs; v++){
			id[v] = v;
			sz[v] = 1;
		}
	}

	/**
	 * 
	 * @param i
	 * @return 找到顶点i的连通分量编号
	 */
	public int find(int i) {
		while (id[i] != i) {
			i = id[i];
		}
		return i;
	}
	
	public boolean connected(int v, int w){
		return find(v)==find(w);
	}

	/**
	 * 将 两个顶点所在连通分量合并
	 * 
	 * @param i
	 * @param j
	 */
	public void union(int i, int j) {
		int ri = find(i);
		int rj = find(j);
		if (ri == rj) {
			return;
		}

		if (sz[ri] > sz[rj]) {
			id[rj] = id[ri];
			sz[ri] += sz[rj];
		} else {
			id[ri] = id[rj];
			sz[j] += sz[i];
		}
	}
	
	public static void main(String[] args){
		UnionFind uf = new UnionFind(10);
		uf.union(4, 3);
		uf.union(3, 8);
		uf.union(6, 5);
		uf.union(9, 4);
		uf.union(2, 1);
		System.out.println("8: "+uf.find(8)+", 9: "+uf.find(9));
		uf.union(8, 9);
		uf.union(5, 0);
		uf.union(7, 2);
		uf.union(6, 1);
		System.out.println("1: "+uf.find(1)+", 0: "+uf.find(0));
		uf.union(1, 0);
		System.out.println("6: "+uf.find(6)+", 7: "+uf.find(7));
		uf.union(6, 7);
	}
}
