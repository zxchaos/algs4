package com.zxchaos;

/**
 * Dijkstra 双栈求值法, 运算符: +,-,*,/,sqrt
 * 
 * @author zhangxin
 *
 */
public class DijkstraDoubleStack {

	public static  double calc(String exp) {
		double res = 0;
		Stack<Character> ops = new Stack<>();
		Stack<Double> vals = new Stack<>();
		for (int i = 0; i < exp.length(); i++) {
			if (exp.charAt(i) == '+' || exp.charAt(i) == '-' || exp.charAt(i) == '*' || exp.charAt(i) == '/' || exp.charAt(i) == 's') {
				ops.push(exp.charAt(i));
			} else if (exp.charAt(i) == ')') {
				char op = ops.pop();
				Double val = vals.pop();
				switch (op) {
				case '+':
					res = vals.pop()+val;
					break;
				case '-':
					res = vals.pop()-val;
					break;
				case '*':
					res = vals.pop()*val;
					break;
				case '/':
					res = vals.pop()/val;
					break;
				case 's':
					res = Math.sqrt(val);
					break;
				default:
					break;
				}
				vals.push(res);
			} else if(exp.charAt(i)>='0' && exp.charAt(i)<='9'){
				vals.push(Double.valueOf(String.valueOf(exp.charAt(i))));
			}
		}
		return vals.pop();
	}
	
	public static void main(String[] args){
//		String a = "(1+((2+3)*(4*5)))";
		String a = "((1+s(5))/2)";
		System.out.println(calc(a));
	}
}
