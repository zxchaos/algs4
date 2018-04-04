package com.zxchaos;

/**
 * 正则表达式 NFA 匹配算法
 * @author zhangxin
 *
 */
public class ReNFA {

	private String re;
	private Digraph dg;
	
	public ReNFA(String re){
		dg = new Digraph(re.length()+1);
		this.re = re;
		Stack<Integer> stack = new Stack<>();
		for(int i = 0; i < re.length(); i++){
			int lp = i;
			if (re.charAt(i) == '(' || re.charAt(i)=='|') {
				stack.push(i);
			} else if (re.charAt(i)==')') {
				int or = stack.pop();
				if (or == '|') {
					dg.addEdge(or, i);
					lp = stack.pop();
					dg.addEdge(lp, or+1);
				} else {
					lp = or;
				}
			}
			
			if (i < re.length()-1 && re.charAt(i+1)=='*') {
				dg.addEdge(lp, i+1);
				dg.addEdge(i+1, lp);
			}
			
			if (re.charAt(i)=='(' || re.charAt(i)=='*' || re.charAt(i)==')') {
				dg.addEdge(i, i+1);
			}
		}
	}
	
	public boolean recognized(String s){
		DigraphDFS digraphDFS = new DigraphDFS(dg,0);
		Bag<Integer> pc = new Bag<>();
		for(int v = 1; v < dg.getVs(); v++){
			if (digraphDFS.marked(v)) {
				pc.add(v);
			}
		}
		Bag<Integer> matches = new Bag<>();
		for(int i = 0; i < s.length(); i++){
			for(int v : pc){
				if (v == re.length()) {
					continue;
				}
				if (re.charAt(v)==s.charAt(i)) {
					matches.add(v);
				}
			}
			digraphDFS = new DigraphDFS(dg, matches);
			pc = new Bag<>();
			for(int v = 0; v < dg.getVs(); v++){
				if (digraphDFS.marked(v)) {
					pc.add(v);
				}
			}
		}
		for(int v : pc){
			if (v == re.length()) {
				return true;
			}
		}
		return false;
	}
}
