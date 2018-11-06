package com.itcast.dw.test;

public class Reversal {
	
	public static void main(String[] args) {
		/**
		 * 将Hello World 反转输出为 olleH dlroW,不要使用库函数
		 */
		String str = "Hello World";
		char[] strs = str.toCharArray();
		int strsLength = strs.length;
		char[] rts = new char[strsLength];
		
		int wordLength = 0;
		for(int i = 1;i <= strsLength;i++){
			if(strs[i-1] == ' ' || i == strsLength){
				int n = i - wordLength - 1;
				System.out.println(n);
				for(int j = 1; j <= wordLength;j++){
					rts[n] = strs[i-j-1];
					n++;
				}
				rts[i-1] = ' ';
				wordLength = 0;
			}else{
				wordLength++;
			}
		}
		
		StringBuffer sb = new StringBuffer();
		for(char a:rts){
			sb.append(Character.toString(a));
		}
		
		System.out.println(sb);
	}

}
