package com.zxchaos;

/**
 * LZW 压缩算法, 只实现核心算法逻辑.
 * @author zhangxin
 *
 */
public abstract class LZW {

	private static final int W = 12;
	private static final int R = 256;//结束标记
	private static final int size = (int)Math.pow(2, 12);
	public void compress(){
		TST<Integer> tst = new TST<>();
		for(int i = 0; i<R; i++){
			tst.put(String.valueOf((char)i), i);
		}
		int code = R+1;
		byte[] bytes = new byte[2048];
		int length = 0;
		while((length = readFile(bytes))!=-1){
			String s = "";
			for(int i = 0; i< length; i++){
				s += String.valueOf((char)(0x00ff&bytes[i]));
			}
			while(s.length() > 0){
				String m = tst.longestPrefixKey(s);
				write2File(tst.get(m));
				if (code < size) {
					tst.put(s.substring(0, m.length()+1), code++);
				}
				s = s.substring(m.length());
			}
		}
		write2File(R);
	}
	
	public void expand(){
		String[] st = new String[size];
		int i = 0;
		for(; i<R; i++){
			st[i] = String.valueOf((char)i);
		}
		st[R] = " ";
		int code = readW();
		String val = st[code];
		while(code != R){
			byte[] bytes = new byte[val.length()];
			for(int k = 0; k<bytes.length;k++){
				bytes[k] = (byte)val.charAt(k);
			}
			write2File(bytes);
			code = readW();
			String s = st[code];
			if (code == i) {
				s = val+val.charAt(0);
			}
			if (i < size) {
				st[i++] = val + s.charAt(0);
			}
			val = s;
		}
	}
	
	public abstract int readFile(byte[] bytes);
	
	public abstract int readW();
	
	public abstract void write2File(int c);
	
	public abstract void write2File(byte[] bytes);
}
