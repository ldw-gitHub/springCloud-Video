/**
 * 
 * @date 2019年8月21日
 */
package com.itcast.dw.test.exam;

import java.util.ArrayList;

import com.itcast.dw.test.model.ListNode;
import com.itcast.dw.test.model.TreeNode;

/**
 * 剑指office1-4
 * @author liudawei
 */

public class Day2 {
	
	public static void main(String[] args) {
		/*int target = 15;
		//int[][] arr = {{1,2,8,9},{2,4,9,12},{4,7,10,13},{6,8,11,15}};
		int[][] arr = {{}};
	    boolean find = Find(target,arr);
	    System.out.println(find);*/
		
		/*StringBuffer s = new StringBuffer("We Are Happy"); 
		System.out.println(replaceSpace(s));*/
		
	/*	ListNode l = new ListNode(1);
		l.next = new ListNode(2);
		ArrayList<Integer> printListFromTailToHead = printListFromTailToHead(l);
		printListFromTailToHead.stream().forEach(e -> System.out.println(e));*/
		
		int [] pre = {1,2,4,7,3,5,6,8};
		int [] in = {4,7,2,1,5,3,8,6};
		reConstructBinaryTree(pre,in);
	}
	
	/**
	 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
	 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
	 * 一句话功能简述
	 * @param pre
	 * @param in
	 * @return 
	 * @date 2019年8月22日
	 * @author liudawei
	 */
    public static TreeNode reConstructBinaryTree(int [] pre,int [] in) {
    	return reConstructBinaryTree(pre,0,pre.length - 1,in,0,in.length - 1);//0，7
    }
    
    public static TreeNode reConstructBinaryTree(int [] pre,int startPre,int endPre,int [] in,int startIn,int endIn) {
    	if(startPre > endPre || startIn > endIn){
    		return null;
    	}
    	TreeNode root = new TreeNode(pre[startPre]);//1
    	
    	for(int i = startIn;i <= endIn;i++){//0，7
    		if (in[i] == pre[startPre]) {//当中序遍历等于根节点得值时3
				root.left = reConstructBinaryTree(pre, startPre + 1, startPre + i - startIn, in, startIn, i - 1);
				root.right = reConstructBinaryTree(pre, i - startIn + startPre + 1, endPre, in, i + 1, endIn);
				break;
			}
    	}
    	
    	return root;
    }
    
    
    

	/**
	 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
	 * 请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
	 * 一句话功能简述
	 * @param target
	 * @param array
	 * @return 
	 * @date 2019年8月21日
	 * @author liudawei
	 */
	public static boolean Find(int target,int [][] array){
		if(array.length == 1 && array[0].length < 1){
			return false;
		}
		int size = array[0].length - 1;
		for(int i = 0 ;i < array.length;i++){
			if(array[i][size] >= target){
				for(int j = 0;j < size + 1;j++){
					if(array[i][j] == target){
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 * 请实现一个函数，将一个字符串中的每个空格替换成“%20”。例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
	 * @param str
	 * @return 
	 * @date 2019年8月22日
	 * @author liudawei
	 */
    public static String replaceSpace(StringBuffer str) {
    	if(str == null || str.length() == 0){
    		return "";
    	}
    	
    	StringBuffer sb = new StringBuffer();
    	for(int i = 0;i < str.length();i++){
    		char charAt = str.charAt(i);
    		if(charAt == ' '){
    			sb.append("%20");
    		}else{
    			sb.append(charAt);
    		}
    	}
    	
    	return sb.toString();
    }
    
    /**
     * 输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
     * 一句话功能简述
     * @param listNode
     * @return 
     * @date 2019年8月21日
     * @author liudawei
     */
    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
    	ArrayList<Integer> arr = new ArrayList<Integer>();
    	ArrayList<Integer> rt = new ArrayList<Integer>();
    	while(listNode!=null){
            arr.add(listNode.val);
            listNode = listNode.next;
        }
    	
    	int size = arr.size();
    	if(size > 0){
    		for(int i = size - 1;i >= 0 ;i-- ){
    			rt.add(arr.get(i));
    		}
    	}
        return rt;
    }
/*    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
    	Stack<Integer> stack = new Stack<>();
    	ArrayList<Integer> a = new ArrayList<Integer>();
    	while(listNode != null){
    		stack.add(listNode.val);
    		listNode = listNode.next;
    	}
    	
    	while(!stack.isEmpty()){
    		a.add(stack.pop());
    	}
    	return a;
    }
*/}
